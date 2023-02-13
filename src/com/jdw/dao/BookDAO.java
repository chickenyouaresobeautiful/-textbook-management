package com.jdw.dao;

import com.jdw.pojo.Book;

import java.sql.SQLException;
import java.util.List;

public class BookDAO extends BasicDAO<Book> {
    public void addBook(Book book) throws SQLException {
        update("insert into book values(null,?,?,?,?)", book.getName(), book.getPrice(), book.getStock(), book.getCategory());
    }

    public void deleteBookById(int id) throws SQLException {
        update("delete from book where id = ?", id);
    }

    public void updateBook(Book book) throws SQLException {
        String sql = "update book set name = ?,price = ?,stock = ?,category = ? where id = ?";
        update(sql, book.getName(), book.getPrice(), book.getStock(), book.getCategory(), book.getId());
    }

    public Book selectBookById(int id) throws SQLException {
        String sql = "select * from book where id = ?";
        return queryForOneList(sql, Book.class, id);
    }

    public List<Book> selectBooks() throws SQLException {
        String sql = "select * from book";
        return queryForList(sql, Book.class);
    }

    public Integer queryForPageTotalCount() throws SQLException {
        Number number = (Number) queryForOne("select count(*) from book");
        return number.intValue();
    }

    public List<Book> queryForItems(int begin, int pageSize) throws SQLException {
        String sql = "select * from book limit ? ,?";
        return queryForList(sql, Book.class, begin, pageSize);
    }
}
