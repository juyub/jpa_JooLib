package jpa_JooLib.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import jpa_JooLib.entity.User;

@Controller
@RequestMapping("/")
public class PageController {

	@RequestMapping("index.html")
	public String index_html() {
		return "main/index";
	}
	
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
		
	@RequestMapping("userPage")
	public String userPage(HttpSession session, Model model) {
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
	
	@RequestMapping("replyPage")
	public String replyPage(HttpSession session, String boardNo, Model model) {
		User user = (User) session.getAttribute("login");
	    model.addAttribute("boardNo", boardNo);
	    model.addAttribute("login", user);
	    return "board/replyBoard";
	}
	
	@RequestMapping("naverBooksPage")
	public String naverBooksPage() {
		return "book/naverBooks";
	}
	
	@RequestMapping(value = "addBookPage", method = RequestMethod.POST)
	public String addBookPage(@RequestParam Map<String,String> params, Model model) {
	    // 'params' 맵에서 폼 데이터에 접근합니다
	    String image = params.get("image");
	    String title = params.get("title");
	    String author = params.get("author");
	    String publisher = params.get("publisher");
	    String publicationyear = params.get("publicationyear");
	    String isbn = params.get("isbn");
	    String description = params.get("description");
	    
	    // 뷰로 전달할 메시지를 모델에 추가합니다.
	    model.addAttribute("message", "도서가 성공적으로 등록되었습니다.");

	    // 도서 등록 페이지를 다시 표시합니다.
	    return "book/addBook";
	}
	
}
