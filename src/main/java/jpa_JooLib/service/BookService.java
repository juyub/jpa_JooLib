package jpa_JooLib.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import jpa_JooLib.entity.Board;
import jpa_JooLib.entity.Book;
import jpa_JooLib.repository.BookRepository;

@Service
public class BookService {

	private final BookRepository bookRepository;

	public BookService(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}
	
	public List<Book> getBooks() {
		return bookRepository.findAll();
	}

	public Book read(int bookNo) {
		return bookRepository.findById(bookNo).orElseThrow(() -> new IllegalArgumentException("Book not found."));
	}
	
	public void saveBook(Book book) {
		bookRepository.save(book);
	}
	
//	public Optional<Book> getBook(int bookno) {
//		return bookRepository.findById(bookno);
//	}
//
//	public void deleteBook(int bookno) {
//		bookRepository.deleteById(bookno);
//	}

}
