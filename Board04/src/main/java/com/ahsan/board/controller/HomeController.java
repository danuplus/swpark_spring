package com.ahsan.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ahsan.board.dao.BoardDAO;
import com.ahsan.board.domain.Board;
import com.ahsan.board.domain.Reply;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	//private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	
	@Autowired
	private BoardDAO boardDAO;
	
	@RequestMapping(value = {"/", "index"}, method = RequestMethod.GET)
	public String listBoards(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page, 
			Model model) {
		model.addAttribute("page", page);
		model.addAttribute("maxPage", boardDAO.getMaxPage());
		model.addAttribute("boards", boardDAO.getBoardsByPage(page.intValue()));
		return "index";
	}
	
	@RequestMapping(value = "view", method = RequestMethod.GET)
	public String viewBoardsAndReplies(@RequestParam(value = "no") Integer no, 
			@RequestParam(value = "page", required = false, defaultValue = "1") Integer page, 
			@RequestParam(value = "back", required = false) String back,
			Model model) {
		if(back==null) {
			boardDAO.raiseReadCount(no.intValue()); // 조회수 1 증가
		}
		model.addAttribute("page", page);	// 목록으로 복귀 할 때, 표시 할 페이지 번호
		Board board = boardDAO.getBoardByNo(no.intValue());
		board.setContent(board.getContent().replace("\r\n", "<br />"));
		model.addAttribute("board", board);	// 화면에 표시할 선택한 게시물
		model.addAttribute("replies", boardDAO.getRepliesByRef(no.intValue()));	// 선택한 게시물에 포함된 덧글 목록 
		model.addAttribute("reply", new Reply());	// 폼과 연동할 모델 객체
		return "view";
	}
	
	@RequestMapping(value = "new", method = RequestMethod.GET)
	public String readyBeforeAddBoard(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page, 
			Model model) {
		model.addAttribute("title", "새 글 쓰기");
		model.addAttribute("job", "new");
		model.addAttribute("page", page);	// 목록으로 복귀 할 때, 표시 할 페이지 번호
		model.addAttribute("board", new Board());
		return "edit";
	}
	
	@RequestMapping(value = "new", method = RequestMethod.POST)
	public String AddBoard(Board board) {
		// 일련번호 부여
		int no = boardDAO.getBoardSequenceNo();
		board.setNo(no);
		board.setRead_cnt(0);
		board.setReply_cnt(0);
		board.setGroup_no(no); 			// 새 글은 그룹 번호와 일련번호가 같음
		board.setSequence_in_group(0);	// 그룹 내 순서는 0
		board.setIndent_in_group(0);	// 그룹 내 들여쓰기 갯수는 0
		board.setRef_no(0);				// 새 글은 부모 글이 없음
	
		boardDAO.addBoard(board);
		return "redirect:index?page=1";	// 첫 페이지로 이동
	}
	
	@RequestMapping(value = "answer", method = RequestMethod.GET)
	public String readyBeforeAddAnswer(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
			@RequestParam(value = "no") Integer no,
			Model model) {
		model.addAttribute("title", "답 글 쓰기");
		model.addAttribute("job", "answer");
		model.addAttribute("page", page);	// 목록으로 복귀 할 때, 표시 할 페이지 번호
		model.addAttribute("no", no);		// 취소하여 원글을 표시 할 때 사용
		Board parent = boardDAO.getBoardByNo(no.intValue());
		Board board = new Board();
		board.setTitle("[Re:] " + parent.getTitle());
		model.addAttribute("board", board);
		return "edit";
	}
	
	@RequestMapping(value = "answer", method = RequestMethod.POST)
	public String AddAnswer(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
			@RequestParam(value = "no") Integer no,
			Board board) {
		// 일련번호 부여
		int seq_no = boardDAO.getBoardSequenceNo();
		board.setNo(seq_no);
		board.setRead_cnt(0);
		board.setReply_cnt(0);
		
		Board parent = boardDAO.getBoardByNo(no.intValue());	// 부모 글의  그룹 번호와 시퀀스 번호를 알아온다.
		
		board.setGroup_no(parent.getGroup_no());
		board.setSequence_in_group(parent.getSequence_in_group() + 1);
		board.setIndent_in_group(parent.getIndent_in_group() + 1);
		board.setRef_no(no.intValue());	// 부모 글의 번호를 지정
		
		boardDAO.adjustBoardOrder(parent.getGroup_no(), parent.getSequence_in_group()); // 자식 글의 순서 조정
		boardDAO.addBoard(board);
		return "redirect:index?page=" + page.intValue();	// 직전 페이지로 이동
	}
	
	@RequestMapping(value = "add_reply", method = RequestMethod.POST)
	public String addReply(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page, Integer no,
			Reply reply) {
		boardDAO.raiseReplyCount(no.intValue());	// 게시 글의 리플 수를 1 증가시킴
		reply.setRef_no(no);	// 덧글이 참조하는 글 번호를 지정
		boardDAO.addReply(reply);
		return "redirect:/view?no=" + no.intValue() + "&page=" + page.intValue() + "&back=y";
	}
	
	@RequestMapping(value = "check", method = RequestMethod.GET)
	public String checkPassword(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page, 
			@RequestParam(value = "no") Integer no, 
			@RequestParam(value = "rno", required = false) Integer rno, 
			@RequestParam(value = "job") String job,
			Model model) {
		model.addAttribute("page", page);
		model.addAttribute("no", no);
		if(rno != null) {
			model.addAttribute("rno", rno);
		}
		model.addAttribute("job", job);
		return "check";
	}
	
	@RequestMapping(value = "remove", method = RequestMethod.POST)
	public String removeBoard(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page, 
			@RequestParam(value = "no") Integer no, 
			@RequestParam(value = "password") String password,
			Model model) {
		if(boardDAO.checkBoardPassword(no, password)) {
			boardDAO.removeBoard(no.intValue()); // 글 삭제
			boardDAO.adjustBoardReference(no.intValue()); // 삭제 된 글을 참조하는 자식글들의 레퍼런스를 -1로 변경
			boardDAO.removeRepliesForReference(no.intValue()); // 삭제 된 글에 대한 모든 덧글을 삭제
			return "redirect:/index?page=" + page.intValue();			
		} else {
			model.addAttribute("page", page);
			model.addAttribute("no", no);
			return "fail";
		}
	}
	
	@RequestMapping(value = "remove_reply", method = RequestMethod.POST)
	public String removeBoard(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page, 
			@RequestParam(value = "no") Integer no, 
			@RequestParam(value = "rno") Integer rno,
			@RequestParam(value = "password") String password,
			Model model) {
		if(boardDAO.checkReplyPassword(rno, password)) {
			boardDAO.removeReply(rno.intValue());		// 리플 제거
			boardDAO.lowerReplyCount(no.intValue());	// 리플이 참조하는 글의 리플 갯수를 감소시킨다.
			return "redirect:/index?no=" + no.intValue() + "&page=" + page.intValue() + "&back=y";			
		} else {
			model.addAttribute("page", page);
			model.addAttribute("no", no);
			return "fail";
		}
	}
	
	@RequestMapping(value = "edit", method = RequestMethod.POST)
	public String readyBeforeUpdate(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page, 
			@RequestParam(value = "no") Integer no,
			@RequestParam(value = "password") String password,
			Model model) {
		model.addAttribute("title", "글 편집");
		model.addAttribute("job", "update");
		model.addAttribute("page", page);
		model.addAttribute("no", no);
		
		if(boardDAO.checkBoardPassword(no.intValue(), password)) {
			Board board = boardDAO.getBoardByNo(no.intValue());
			board.setPassword(null);
			model.addAttribute("board", board);
			return "edit";
		} else {
			return "fail";
		}
	}
	
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String readyBeforeUpdate(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page, 
			@RequestParam(value = "no") Integer no,
			Board board) {
		board.setNo(no.intValue());
		boardDAO.modifyBoard(board);
		return "redirect:/view?no=" + no.intValue() + "&page=" + page.intValue() + "&back=y";
	}
}
