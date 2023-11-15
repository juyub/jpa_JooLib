package jpa_JooLib.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jpa_JooLib.entity.Borrow;
import jpa_JooLib.entity.User;
import jpa_JooLib.service.BorrowService;

@Controller
@RequestMapping("/")
public class BorrowController {

	@Autowired
    private BorrowService borrowService;

	@RequestMapping("borrowBook")
    public String borrowBook(@RequestParam int userNo, @RequestParam int bookNo) {
        borrowService.borrowBook(userNo, bookNo);
        return "redirect:/getBook?bookNo=" + bookNo;  
    }
	
	@RequestMapping("returnBook")
	public String returnBook(@RequestParam int borrowNo) {
	    borrowService.returnBook(borrowNo);
	    return "redirect:/borrowList";
	}
	
	@RequestMapping("borrowList")
    public String borrowList(Model model) {
        List<Borrow> borrowList = borrowService.findAll();
        model.addAttribute("borrowList", borrowList);
        return "book/borrowList";
    }
	
	@RequestMapping("borrowUser")
    public String borrowUser(HttpSession session, Model model) {
		User user = (User) session.getAttribute("login");
        List<Borrow> borrowUser = borrowService.findAllByUser(user.getUserNo());
        model.addAttribute("borrowUser", borrowUser);
        return "book/borrowUser";
    }
    
}
