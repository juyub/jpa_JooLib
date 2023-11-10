package jpa_JooLib.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jpa_JooLib.entity.User;

@Controller
@RequestMapping("/")
public class PageController {

//	@RequestMapping("index.html")
//	public String index_html() {
//		return "main/index";
//	}
	
	@GetMapping("index")
	public String index() {
		return "main/index";
	}
	
	@RequestMapping("addUserPage")
	public String addUserPage() {
		return "user/addUser";
	}
	
	@RequestMapping("loginPage")
	public String loginPage() {
		return "user/login";
	}
		
	@RequestMapping("UserPage")
	public String UserPage(HttpSession session, Model model) {
		User user = (User) session.getAttribute("login");
		model.addAttribute("user", user);
		return "user/getUserNo";
	}
	
	@RequestMapping("movieMain")
	public String movieMain() {
		return "movie/movieMain";
	}
	
	@RequestMapping("movieDetail")
	public String movieDetail(@RequestParam("id") String id, Model model) {
	    model.addAttribute("id", id);
	    return "movie/movieDetail";
	}
	
	@RequestMapping("addBoardPage")
	public String addBoardPage(HttpSession session, Model model) {
		User user = (User) session.getAttribute("login");
		model.addAttribute("login", user);
		return "board/addBoard";
	}
	
}
