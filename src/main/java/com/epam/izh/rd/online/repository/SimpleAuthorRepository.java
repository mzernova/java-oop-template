package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Author;
import java.util.Arrays;

public class SimpleAuthorRepository implements AuthorRepository {

  private Author[] authors = {};

  @Override
  public boolean save(Author author) {
    if (findByFullName(author.getName(), author.getLastName()) != null) {
      return false;
    }
    authors = Arrays.copyOf(authors, authors.length + 1);
    authors[authors.length - 1] = author;
    return true;
  }

  @Override
  public Author findByFullName(String name, String lastname) {
    for (Author author : authors) {
      if (name.equals(author.getName()) && lastname.equals(author.getLastName())) {
        return author;
      }
    }
    return null;
  }

  @Override
  public boolean remove(Author author) {
    Author foundAuthor = findByFullName(author.getName(), author.getLastName());
    if (foundAuthor == null) {
      return false;
    }
    int isRemoved = 0;
    Author result[] = new Author[authors.length - 1];
    for (int i = 0; i < result.length; i++) {
      if (foundAuthor == authors[i]) {
        isRemoved = 1;
      }
      result[i] = authors[i + isRemoved];
    }
    authors = result;
    return true;
  }

  @Override
  public int count() {
    return authors.length;
  }
}
