package com.ahsan.board01.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ahsan.board01.domain.Board;
import com.ahsan.board01.persistence.BoardDAO;

@Controller
public class HomeController {
	
	@Autowired
	private BoardDAO boardDAO;
	
	@RequestMapping(value = {"/", "index"}, method = RequestMethod.GET)
	public String index(@RequestParam(value = "page", required = false ) Integer page, Model model) {
		if (page == null) page = 1;
		
		model.addAttribute("curPage", page);
		model.addAttribute("maxPage", new Integer(boardDAO.getMaxPage()));
		model.addAttribute("boards", boardDAO.getBoardByPage(page.intValue()));
		
		return "index";
	}
	
	@RequestMapping(value = "view", method = RequestMethod.GET)
	public String view(@RequestParam(value = "no") Integer no, 
					   @RequestParam(value = "page", required = false) Integer page, 
					   Model model) {
		if (page == null) page = 1;
		
		Board board = boardDAO.getBoardByNo(no.intValue());
		boardDAO.raiseLookUpCount(no.intValue()); // 조회수 1 증가
		board.setNotice(board.getNotice().replace("\r\n", "<br />")); // 줄 바꿈 처리
	
		model.addAttribute("curPage", page);
		model.addAttribute("board", board);

		return "view";
	}
	
	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String add(@RequestParam(value = "page", required = false) Integer page,
					  Model model) {
		if (page == null) page = 1;
		model.addAttribute("curPage", page);
		return "add";
	}
	
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public String save(@RequestParam(value = "page") Integer page,
					   @RequestParam(value = "title") String title,
					   @RequestParam(value = "password") String password,
					   @RequestParam(value = "notice") String notice,
					   @RequestParam(value = "writer") String writer) {
		
		Board board = new Board();
		board.setTitle(title);
		board.setPassword(password);
		board.setNotice(notice);
		board.setWriter(writer);
		
		boardDAO.addBoard(board);
		
		return "redirect:/index?page=1";
	}
	
	@RequestMapping(value = "check", method = RequestMethod.GET)
	public String check(@RequestParam(value = "no") Integer no,
						@RequestParam(value = "page") Integer page,
						@RequestParam(value = "target") String target,
						 Model model) {
		model.addAttribute("no", no);
		model.addAttribute("curPage", page);
		model.addAttribute("target", target);
		return "check";
	}
	
	@RequestMapping(value = "remove", method = RequestMethod.POST)
	public String remove(@RequestParam(value = "no") Integer no,
						 @RequestParam(value = "page") Integer page,
						 @RequestParam(value = "password") String password,
						 Model model) {
		if(boardDAO.checkPassword(no.intValue(), password)) {
			boardDAO.removeBoard(no.intValue());
			return "redirect:/index?page=" + page.intValue();
		} else {
			model.addAttribute("no", no);
			model.addAttribute("curPage", page);
			model.addAttribute("target", "remove");
			return "fail";
		}
	}
	
	@RequestMapping(value = "edit", method = RequestMethod.POST)
	public String edit(@RequestParam(value = "no") Integer no,
					   @RequestParam(value = "page") Integer page,
					   @RequestParam(value = "password") String password,
					   Model model) {
		model.addAttribute("no", no);
		model.addAttribute("curPage", page);
		
		if(boardDAO.checkPassword(no.intValue(), password)) {
			model.addAttribute("board", boardDAO.getBoardByNo(no.intValue()));
			return "edit";
		} else {
			model.addAttribute("target", "edit");
			return "fail";
		}
	}
	
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(@RequestParam(value = "page") Integer page,
						 @RequestParam(value = "no") Integer no,
					     @RequestParam(value = "title") String title,
					     @RequestParam(value = "password") String password,
					     @RequestParam(value = "notice") String notice,
					     @RequestParam(value = "writer") String writer) {
		
		Board board = new Board();
		board.setNo(no.intValue());
		board.setTitle(title);
		board.setPassword(password);
		board.setNotice(notice);
		board.setWriter(writer);
		
		boardDAO.updateBoard(board);
		
		return "redirect:/index?page=" + page.intValue();
	}
	
}
