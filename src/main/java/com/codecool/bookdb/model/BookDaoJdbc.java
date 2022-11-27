package com.codecool.bookdb.model;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class BookDaoJdbc implements BookDao {
    private final DataSource dataSource;
    private final AuthorDao authorDao;

    public BookDaoJdbc(DataSource dataSource, AuthorDao authorDao) {
        this.dataSource = dataSource;
        this.authorDao = authorDao;
    }

    @Override
    public void add(Book book) {
        try (Connection con = dataSource.getConnection()) {
            final String SQL = "INSERT INTO book(author_id, title) VALUES(?, ?);";
            PreparedStatement ps = con.prepareStatement(SQL, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setInt(1, book.getAuthor().getId());
            ps.setString(2, book.getTitle());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            book.setId(rs.getInt(1));
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void update(Book book) {
        try (Connection con = dataSource.getConnection()) {
            final String SQL = "UPDATE book SET author_id = ?, title = ? WHERE id = ?;";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1, book.getAuthor().getId());
            ps.setString(2, book.getTitle());
            ps.setInt(3, book.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Book get(int id) {
        Book book = null;
        try (Connection con = dataSource.getConnection()) {
            final String SQL = "SELECT author_id, title FROM book WHERE id = ?;";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                book = new Book(authorDao.get(rs.getInt(1)), rs.getString(2));
                book.setId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return book;
    }

    @Override
    public List<Book> getAll() {
        return null;
    }
}
