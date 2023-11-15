package jpa_JooLib.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "jpa_borrow")
public class Borrow {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_jpa_borrow_borrowNo")
    @SequenceGenerator(name = "seq_jpa_borrow_borrowNo", sequenceName = "seq_jpa_borrow_borrowNo", allocationSize = 1)
	private int borrowNo;

	@ManyToOne
	@JoinColumn(name = "userNo")
	private User user;

	@ManyToOne
	@JoinColumn(name = "bookNo")
	private Book book;

	private Date borrowdate;
	private Date duedate;
	private Date returndate;

	public int getBorrowNo() {
		return borrowNo;
	}
	public void setBorrowNo(int borrowNo) {
		this.borrowNo = borrowNo;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public Date getBorrowdate() {
		return borrowdate;
	}
	public void setBorrowdate(Date borrowdate) {
		this.borrowdate = borrowdate;
	}
	public Date getDuedate() {
		return duedate;
	}
	public void setDuedate(Date duedate) {
		this.duedate = duedate;
	}
	public Date getReturndate() {
		return returndate;
	}
	public void setReturndate(Date returndate) {
		this.returndate = returndate;
	}
	
}
