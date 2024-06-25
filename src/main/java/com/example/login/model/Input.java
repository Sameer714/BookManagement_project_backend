package com.example.login.model;

import java.util.Date;

public class Input {

	
    public String bookName;
    public String authorName;
    public Date launchDate;
    public String aboutBook;
    
    public String getAboutBook() {
		return aboutBook;
	}

	public void setAboutBook(String aboutBook) {
		this.aboutBook = aboutBook;
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

    public Date getLaunchDate() {
        return launchDate;
    }

    public void setLaunchDate(Date launchDate) {
        this.launchDate = launchDate;
    }

    public long getLaunchDateEpoch() {
        return launchDate.getTime(); // Returns the number of milliseconds since January 1, 1970, 00:00:00 UTC
    }

    public Input(String bookName, String authorName, Date launchDate , String aboutBook) {
        super();
        this.aboutBook = aboutBook;
        this.bookName = bookName;
        this.authorName = authorName;
        this.launchDate = launchDate;
    }
}
