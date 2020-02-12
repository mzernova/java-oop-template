package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Book;
import com.epam.izh.rd.online.entity.SchoolBook;
import java.util.Arrays;

public class SimpleSchoolBookRepository implements BookRepository {

  private SchoolBook[] schoolBooks = {};

  @Override
  public boolean save(Book book) {
    schoolBooks = Arrays.copyOf(schoolBooks, schoolBooks.length + 1);
    schoolBooks[schoolBooks.length - 1] = (SchoolBook) book;
    return true;
  }

  @Override
  public SchoolBook[] findByName(String name) {
    SchoolBook[] result = {};
    for (SchoolBook book : schoolBooks) {
      if (name.equals(book.getName())) {
        result = Arrays.copyOf(result, result.length + 1);
        result[result.length - 1] = book;
      }
    }
    return result;
  }

  @Override
  public boolean removeByName(String name) {
    SchoolBook[] foundBooks = findByName(name);
    if (foundBooks == null) {
      return false;
    }
    int removed = 0;
    SchoolBook result[] = new SchoolBook[schoolBooks.length - foundBooks.length];
    for (int i = 0; i < result.length; i++) {
      if (name == schoolBooks[i + removed].getName()) {
        removed++;
      }
      result[i] = schoolBooks[i + removed];
    }
    schoolBooks = result;
    return true;
  }

  @Override
  public int count() {
    return schoolBooks.length;
  }
}
