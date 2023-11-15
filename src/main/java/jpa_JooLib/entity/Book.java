package jpa_JooLib.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "jpa_book")
public class Book {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_jpa_book_bookNo")
    @SequenceGenerator(name = "seq_jpa_book_bookNo", sequenceName = "seq_jpa_book_bookNo", allocationSize = 1)
	private int bookNo;
	
	private String title;
	private String author;
	private String publisher;
	private int publicationyear;
	private String isbn;
	private String category;
	private int totaln;
	private int availablen;
	private String image;
	
	@Column(name = "description", length = 500)
	private String description;

	public int getBookNo() {
		return bookNo;
	}

	public void setBookNo(int bookNo) {
		this.bookNo = bookNo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public int getPublicationyear() {
		return publicationyear;
	}

	public void setPublicationyear(int publicationyear) {
		this.publicationyear = publicationyear;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getTotaln() {
		return totaln;
	}

	public void setTotaln(int totaln) {
		this.totaln = totaln;
	}

	public int getAvailablen() {
		return availablen;
	}

	public void setAvailablen(int availablen) {
		this.availablen = availablen;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
	    if (description.length() > 500) {
	        this.description = description.substring(0, 500);
	    } else {
	        this.description = description;
	    }
	}
	
}
