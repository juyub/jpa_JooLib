package jpa_JooLib.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jpa_JooLib.entity.Book;
import jpa_JooLib.entity.Borrow;
import jpa_JooLib.entity.User;
import jpa_JooLib.repository.BookRepository;
import jpa_JooLib.repository.BorrowRepository;
import jpa_JooLib.repository.UserRepository;

@Service
public class BorrowService {
	
    @Autowired
    private BorrowRepository borrowRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BookRepository bookRepository;

    public Borrow borrowBook(int userNo, int bookNo) {
        User user = userRepository.findById(userNo).orElseThrow(() -> new RuntimeException("User not found"));
        Book book = bookRepository.findById(bookNo).orElseThrow(() -> new RuntimeException("Book not found"));

        // �뿩 ������ å�� ���ų� ����ڰ� �� �̻� å�� �뿩�� �� ���� ��� ���ܸ� �߻���ŵ�ϴ�.
        if (book.getAvailablen() <= 0 || user.getBorrown() <= 0) {
            throw new RuntimeException("Cannot borrow book");
        }

        Borrow borrow = new Borrow();
        borrow.setUser(user);
        borrow.setBook(book);
        borrow.setBorrowdate(new Date());
        // �뿩 �Ⱓ�� �����մϴ�. ���� ���, 14�� �ķ� �����մϴ�.
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DATE, 14);
        borrow.setDuedate(c.getTime());

        // å�� �뿩 ���� �Ǽ��� ������� �뿩 ���� �Ǽ��� �����ϰ� �����մϴ�.
        book.setAvailablen(book.getAvailablen() - 1);
        user.setBorrown(user.getBorrown() - 1);
        userRepository.save(user);
        bookRepository.save(book);

        return borrowRepository.save(borrow);
    }

    public Borrow returnBook(int borrowNo) {
        Borrow borrow = borrowRepository.findById(borrowNo).orElseThrow(() -> new RuntimeException("Borrow not found"));
        borrow.setReturndate(new Date());

        // å�� �뿩 ���� �Ǽ��� ������� �뿩 ���� �Ǽ��� �����ϰ� �����մϴ�.
        Book book = borrow.getBook();
        User user = borrow.getUser();
        book.setAvailablen(book.getAvailablen() + 1);
        user.setBorrown(user.getBorrown() + 1);
        userRepository.save(user);
        bookRepository.save(book);

        return borrowRepository.save(borrow);
    }
	
    public List<Borrow> findAll() {
        return borrowRepository.findAll();
    }
    
    public List<Borrow> findAllByUser(int userNo) {
        User user = new User();
        user.setUserNo(userNo);
        return borrowRepository.findByUser(user);
    }
    
}
