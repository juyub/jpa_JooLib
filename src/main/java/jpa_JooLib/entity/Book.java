package jpa_JooLib.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "jpa_book")
public class Book {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	@Column(name = "bookno")
	private int bookno;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "author")
	private String author;
	
	@Column(name = "publisher")
	private String publisher;
	
	@Column(name = "publicationyear")
	private int publicationyear;
	
	@Column(name = "isbn")
	private String isbn;
	
	@Column(name = "category")
	private String category;
	
	@Column(name = "totaln")
	private int totaln;
	
	@Column(name = "availablen")
	private int availablen;
	
	@Column(name = "image")
	private String image;
	
	@Column(name = "description")
	private String description;
	
}
