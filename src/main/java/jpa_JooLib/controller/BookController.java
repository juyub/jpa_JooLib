package jpa_JooLib.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jpa_JooLib.entity.Book;
import jpa_JooLib.service.BookService;

@Controller
@RequestMapping("/")
public class BookController {

	@Autowired
	private BookService bookService;

	@GetMapping("/getBookList")
	public String bookList(Model theModel) {
		List<Book> books = bookService.getBooks();
		theModel.addAttribute("books", books);
		return "book/getBookList";
	}

	@GetMapping("/addBookPage")
	public String addBookPage(Model model) {
		Book book = new Book();
		model.addAttribute("book", book);
		return "join";
	}

//	@ModelAttribute("user")
//	public Book createUser() {
//	    User user = new User();
//	    user.setJoindate(new Date());
//	    user.setBorrown(3);
//	    user.setRole("user");
//	    return user;
//	}
	
	@PostMapping("/addBook")
	public String addBook(@ModelAttribute("book") Book book) {
		bookService.saveBook(book);
		return "redirect:/bookList";
	}

	@GetMapping("/updateBook")
	public String updateBook(@RequestParam("bookno") int bookno,
			Model model) {
		model.addAttribute("bookno", bookService.getBook(bookno));
		return "join";
	}

	@GetMapping("/deleteBook")
	public String deleteBook(@RequestParam("bookno") int bookno) {
		bookService.deleteBook(bookno);
		return "redirect:/bookList";
	}
	
}
