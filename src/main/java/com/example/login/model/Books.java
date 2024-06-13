package com.example.login.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Books {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name ="Id")
    private long id;

    @Column(name="Book_Name")
    private String bookName;

    @Column(name="Author_Name")
    private String authorName;

    @Column(name="Launch_Date")
    private long launchDate; 
    
    @Column(name="About")
    private String aboutBook;

    public String getAboutBook() {
		return aboutBook;
	}

	public void setAboutBook(String aboutBook) {
		this.aboutBook = aboutBook;
	}

	public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public long getLaunchDate() {
        return launchDate;
    }

    public void setLaunchDate(long launchDate) {
        this.launchDate = launchDate;
    }


    public Books() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Books [id=" + id + ", bookName=" + bookName + ", authorName=" + authorName + ", launchDate="
				+ launchDate + ", aboutBook=" + aboutBook + "]";
	}


    public Books(long id, String bookName, String authorName, long launchDate, String aboutBook) {
		super();
		this.id = id;
		this.bookName = bookName;
		this.authorName = authorName;
		this.launchDate = launchDate;
		this.aboutBook = aboutBook;
	}

	public Books(String bookName, String authorName, long launchDate, String aboutBook){
        super();
		this.aboutBook = aboutBook;
        this.bookName = bookName;
        this.authorName = authorName;
        this.launchDate = launchDate;
    }
}
