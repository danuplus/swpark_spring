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
			boardDAO.raiseReadCount(no.intValue()); // ��ȸ�� 1 ����
		}
		model.addAttribute("page", page);	// ������� ���� �� ��, ǥ�� �� ������ ��ȣ
		Board board = boardDAO.getBoardByNo(no.intValue());
		board.setContent(board.getContent().replace("\r\n", "<br />"));
		model.addAttribute("board", board);	// ȭ�鿡 ǥ���� ������ �Խù�
		model.addAttribute("replies", boardDAO.getRepliesByRef(no.intValue()));	// ������ �Խù��� ���Ե� ���� ��� 
		model.addAttribute("reply", new Reply());	// ���� ������ �� ��ü
		return "view";
	}
	
	@RequestMapping(value = "new", method = RequestMethod.GET)
	public String readyBeforeAddBoard(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page, 
			Model model) {
		model.addAttribute("title", "�� �� ����");
		model.addAttribute("job", "new");
		model.addAttribute("page", page);	// ������� ���� �� ��, ǥ�� �� ������ ��ȣ
		model.addAttribute("board", new Board());
		return "edit";
	}
	
	@RequestMapping(value = "new", method = RequestMethod.POST)
	public String AddBoard(Board board) {
		// �Ϸù�ȣ �ο�
		int no = boardDAO.getBoardSequenceNo();
		board.setNo(no);
		board.setRead_cnt(0);
		board.setReply_cnt(0);
		board.setGroup_no(no); 			// �� ���� �׷� ��ȣ�� �Ϸù�ȣ�� ����
		board.setSequence_in_group(0);	// �׷� �� ������ 0
		board.setIndent_in_group(0);	// �׷� �� �鿩���� ������ 0
		board.setRef_no(0);				// �� ���� �θ� ���� ����
	
		boardDAO.addBoard(board);
		return "redirect:index?page=1";	// ù �������� �̵�
	}
	
	@RequestMapping(value = "answer", method = RequestMethod.GET)
	public String readyBeforeAddAnswer(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
			@RequestParam(value = "no") Integer no,
			Model model) {
		model.addAttribute("title", "�� �� ����");
		model.addAttribute("job", "answer");
		model.addAttribute("page", page);	// ������� ���� �� ��, ǥ�� �� ������ ��ȣ
		model.addAttribute("no", no);		// ����Ͽ� ������ ǥ�� �� �� ���
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
		// �Ϸù�ȣ �ο�
		int seq_no = boardDAO.getBoardSequenceNo();
		board.setNo(seq_no);
		board.setRead_cnt(0);
		board.setReply_cnt(0);
		
		Board parent = boardDAO.getBoardByNo(no.intValue());	// �θ� ����  �׷� ��ȣ�� ������ ��ȣ�� �˾ƿ´�.
		
		board.setGroup_no(parent.getGroup_no());
		board.setSequence_in_group(parent.getSequence_in_group() + 1);
		board.setIndent_in_group(parent.getIndent_in_group() + 1);
		board.setRef_no(no.intValue());	// �θ� ���� ��ȣ�� ����
		
		boardDAO.adjustBoardOrder(parent.getGroup_no(), parent.getSequence_in_group()); // �ڽ� ���� ���� ����
		boardDAO.addBoard(board);
		return "redirect:index?page=" + page.intValue();	// ���� �������� �̵�
	}
	
	@RequestMapping(value = "add_reply", method = RequestMethod.POST)
	public String addReply(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page, Integer no,
			Reply reply) {
		boardDAO.raiseReplyCount(no.intValue());	// �Խ� ���� ���� ���� 1 ������Ŵ
		reply.setRef_no(no);	// ������ �����ϴ� �� ��ȣ�� ����
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
			boardDAO.removeBoard(no.intValue()); // �� ����
			boardDAO.adjustBoardReference(no.intValue()); // ���� �� ���� �����ϴ� �ڽı۵��� ���۷����� -1�� ����
			boardDAO.removeRepliesForReference(no.intValue()); // ���� �� �ۿ� ���� ��� ������ ����
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
			boardDAO.removeReply(rno.intValue());		// ���� ����
			boardDAO.lowerReplyCount(no.intValue());	// ������ �����ϴ� ���� ���� ������ ���ҽ�Ų��.
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
		model.addAttribute("title", "�� ����");
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