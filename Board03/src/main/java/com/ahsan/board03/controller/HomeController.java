package com.ahsan.board03.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ahsan.board03.dao.BoardDAO;
import com.ahsan.board03.domain.Board;
import com.ahsan.board03.domain.Reply;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	//private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private BoardDAO boardDAO;
	
	@RequestMapping(value = {"/", "index"}, method = RequestMethod.GET)
	public String listBoards(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page, 
			Model model) {
		model.addAttribute("page", page);
		model.addAttribute("maxPage", new Integer(boardDAO.getMaxPage()));
		model.addAttribute("boards", boardDAO.getBoardsByPage(page.intValue()));
		return "index";
	}
	
	@RequestMapping(value = "view", method = RequestMethod.GET)
	public String viewBoard(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page, 
			@RequestParam(value = "no") Integer no,
			@RequestParam(value = "b", required = false) String back,
			Model model) {
		if(back == null) {
			boardDAO.raiseLookupCount(no.intValue()); // 조회수를 1 증가시킴
		}
		model.addAttribute("page", page);
		
		Board board = boardDAO.getBoardByNo(no.intValue());
		board.setContent(board.getContent().replace("\r\n", "<br />"));
		model.addAttribute("board", board);
		
		model.addAttribute("replies", boardDAO.getRepliesByReference(no.intValue()));
		return "view";
	}
	
	@RequestMapping(value = "reply", method = RequestMethod.POST)
	public String addReply(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page, 
			@RequestParam(value = "no") Integer no,
			@RequestParam(value = "writer") String writer,
			@RequestParam(value = "password") String password,
			@RequestParam(value = "memo") String memo) {
		boardDAO.addReply(new Reply(null, writer, password, memo, null, no));
		boardDAO.raiseReplyCount(no.intValue());
		return "redirect:/view?page=" + page.intValue() + "&no=" + no.intValue() + "&b=y";
	}
	
	@RequestMapping(value = "check", method = RequestMethod.GET)
	public String checkReplyPassword(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page, 
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
	
	@RequestMapping(value = "del_reply", method = RequestMethod.POST)
	public String deleteReply(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page, 
			@RequestParam(value = "no") Integer no, 
			@RequestParam(value = "rno") Integer rno,
			@RequestParam(value = "password") String password,
			Model model) {
		if(boardDAO.checkPasswordForReply(rno.intValue(), password)) {
			boardDAO.removeReply(rno.intValue());
			boardDAO.lowerReplyCount(no.intValue());
			return "redirect:/view?page=" + page.intValue() + "&no=" + no.intValue() + "&b=y";			
		} else {
			model.addAttribute("no", no);
			model.addAttribute("page", page);
			return "fail";
		}
	}
	
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public String deleteBoard(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page, 
			@RequestParam(value = "no") Integer no, 
			@RequestParam(value = "password") String password,
			Model model) {
		if(boardDAO.checkPassword(no.intValue(), password)) {
			boardDAO.removeReplyForBoard(no.intValue());
			boardDAO.removeBoard(no.intValue());
			return "redirect:/index?page=" + page.intValue();			
		} else {
			model.addAttribute("no", no);
			model.addAttribute("page", page);
			return "fail";
		}
	}
	
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String viewBoardBeforeUpdate(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page, 
			@RequestParam(value = "no") Integer no, 
			@RequestParam(value = "password") String password,
			Model model) {

		model.addAttribute("no", no);
		model.addAttribute("page", page);

		if(boardDAO.checkPassword(no.intValue(), password)) {
			model.addAttribute("board", boardDAO.getBoardByNo(no.intValue()));
			return "edit";			
		} else {
			return "fail";
		}
	}

	@RequestMapping(value = "edit", method = RequestMethod.POST)
	public String updateBoard(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page, 
			@RequestParam(value = "no") Integer no, 
			@RequestParam(value = "writer") String writer,
			@RequestParam(value = "password") String password,
			@RequestParam(value = "title") String title,
			@RequestParam(value = "content") String content,
			Model model) {
		
		boardDAO.modifyBoard(new Board(no.intValue(), title, content, writer,
				password, null, null, null));
		
		return "redirect:/view?no=" + no.intValue() + "&page=" + page.intValue() + "&b=y";
	}
	
	@RequestMapping(value = "write", method = RequestMethod.GET)
	public String viewBoardForInsert(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
			Model model) {
		model.addAttribute("page", page);
		return "add";
	}
	
	@RequestMapping(value = "add", method = RequestMethod.POST)
	public String addBoard(@RequestParam(value = "writer") String writer,
			@RequestParam(value = "password") String password,
			@RequestParam(value = "title") String title,
			@RequestParam(value = "content") String content) {
		
		boardDAO.addBoard(new Board(null, title, content, writer, password, null, null, null));
		return "redirect:/index";
	}
}
