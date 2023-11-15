package jpa_JooLib.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jpa_JooLib.entity.Board;
import jpa_JooLib.entity.Book;
import jpa_JooLib.entity.Comment;
import jpa_JooLib.service.BookService;

@Controller
@RequestMapping("/")
public class BookController {

	@Autowired
	private BookService bookService;

	@RequestMapping("bookList")
	public String bookList(Model model) {
		List<Book> bookList = bookService.getBooks();
		model.addAttribute("bookList", bookList);
		return "book/bookList";
	}
	
    @RequestMapping("getBook")
    public String getBook(@RequestParam("bookNo") int bookNo, Model model) {
        Book book = bookService.read(bookNo);
        model.addAttribute("book", book);
        return "book/getBook";
    }
	
	@RequestMapping("addBook")
	public String addBook(@ModelAttribute("book") Book book) {
		bookService.saveBook(book);
		return "redirect:/bookList";
	}

//	@RequestMapping("updateBook")
//	public String updateBook(@RequestParam("bookno") int bookno,
//			Model model) {
//		model.addAttribute("bookno", bookService.getBook(bookno));
//		return "join";
//	}
//
//	@RequestMapping("deleteBook")
//	public String deleteBook(@RequestParam("bookno") int bookno) {
//		bookService.deleteBook(bookno);
//		return "redirect:/bookList";
//	}
	
}
