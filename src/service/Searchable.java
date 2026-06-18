package service;

import java.util.List;

import model.Book;

public interface Searchable {
    List<Book> searchByTitle(String kw);
    List<Book> searchByAuthor(String kw);

    static String normalizeKeyword(String str) {
        if (str == null) {
            return "";
        }
        return str.trim().toLowerCase();
    }
}
