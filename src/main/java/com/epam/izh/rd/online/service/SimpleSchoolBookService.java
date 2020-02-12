package com.epam.izh.rd.online.service;

import com.epam.izh.rd.online.entity.Author;
import com.epam.izh.rd.online.entity.Book;
import com.epam.izh.rd.online.entity.SchoolBook;
import com.epam.izh.rd.online.repository.BookRepository;


public class SimpleSchoolBookService implements BookService {

  private BookRepository<SchoolBook> schoolBookBookRepository;
  private AuthorService authorService;

  public SimpleSchoolBookService() {
  }

  public SimpleSchoolBookService(BookRepository<SchoolBook> schoolBookBookRepository,
      AuthorService authorService) {
    this.schoolBookBookRepository = schoolBookBookRepository;
    this.authorService = authorService;
  }

  @Override
  public boolean save(Book book) {
    SchoolBook b = (SchoolBook) book;
    if (authorService.findByFullName(b.getAuthorName(), b.getAuthorLastName()) != null) {
      return schoolBookBookRepository.save(b);
    }
    return false;
  }

  @Override
  public Book[] findByName(String name) {
    return schoolBookBookRepository.findByName(name);
  }

  @Override
  public int getNumberOfBooksByName(String name) {
    return schoolBookBookRepository.findByName(name).length;
  }

  @Override
  public boolean removeByName(String name) {
    return schoolBookBookRepository.removeByName(name);
  }

  @Override
  public int count() {
    return schoolBookBookRepository.count();
  }

  @Override
  public Author findAuthorByBookName(String name) {
    SchoolBook[] books = schoolBookBookRepository.findByName(name);
    if (books.length == 0) {
      return null;
    }
    return authorService.findByFullName(books[0].getAuthorName(), books[0].getAuthorLastName());
  }
}
