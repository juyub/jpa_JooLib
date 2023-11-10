package jpa_JooLib.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jpa_JooLib.entity.User;
import jpa_JooLib.service.UserService;

@Controller
@RequestMapping("/")
public class UserController {

//	private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;
	
	@ModelAttribute("user")
	public User createUser() {
		User user = new User();
		user.setJoindate(new Date());
		user.setBorrown(3);
		user.setRole("user");
		return user;
	}

	@RequestMapping("/addUser")
	public String join(@ModelAttribute("user") User user) {
		userService.saveUser(user);
		return "redirect:/index";
	}
	
	@RequestMapping("/login")
	public String login(@RequestParam("id") String userid, @RequestParam("password") String password, HttpSession session) {
	    User user = userService.login(userid, password);
	    if (user != null) {
	        session.setAttribute("login", user);
	        return "redirect:/index";
	    } else {
	        return "redirect:/login?error";
	    }
	}

	@RequestMapping("/logout")
	public String logout(HttpSession session) {
	    session.invalidate();
	    return "redirect:/index";
	}

	
	@RequestMapping("/userList")
	public String userList(Model model) {
		List<User> users = userService.getUsers();
		model.addAttribute("users", users);
		return "views/userList";
	}

	@RequestMapping("/updateUser")
	public String updateUser(@ModelAttribute User updatedUser, Model model) {
		userService.updateUser(updatedUser.getUserno(), updatedUser);
				
		Optional<User> updatedUserOptional = userService.getUser(updatedUser.getUserno());
	    if(updatedUserOptional.isPresent()) {
	        User user = updatedUserOptional.get();
	        model.addAttribute("user", user);
	    } else {
	        // 업데이트된 사용자 정보를 가져오지 못한 경우 처리
	    }
	    return "user/getUserNo";
	}

	@RequestMapping("/deleteUser")
	public String deleteUser(@RequestParam("userno") int userno, HttpSession session) {
		userService.deleteUser(userno);
		session.invalidate();
		return "redirect:/index";
	}

//	@GetMapping("/joinPage")
//	public String joinPage(Model model) {
//		User user = new User();
//		model.addAttribute("user", user);
//		return "join";
//	}
	
	
	
	
}
