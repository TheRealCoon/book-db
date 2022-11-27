package com.codecool.bookdb.model;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorDaoJdbc implements AuthorDao {
    private final DataSource dataSource;

    public AuthorDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(Author author) {
        try (Connection con = dataSource.getConnection()) {
            final String SQL = "INSERT INTO author(first_name, last_name, birth_date) VALUES(?, ?, ?);";
            PreparedStatement ps = con.prepareStatement(SQL, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, author.getFirstName());
            ps.setString(2, author.getLastName());
            ps.setDate(3, author.getBirthDate());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            author.setId(rs.getInt(1));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Author author) {
        try (Connection con = dataSource.getConnection()) {
            final String SQL = "UPDATE author SET first_name = ?,  last_name = ? birth_date = ? WHERE id = ?;";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setString(1, author.getFirstName());
            ps.setString(2, author.getLastName());
            ps.setDate(3, author.getBirthDate());
            ps.setInt(4, author.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Author get(int id) {
        Author author = null;
        try (Connection con = dataSource.getConnection()) {
            final String SQL = "SELECT first_name, last_name, birth_date FROM author WHERE id = ?;";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                author = new Author(rs.getString(1), rs.getString(2), rs.getDate(3));
                author.setId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return author;
    }

    @Override
    public List<Author> getAll() {
        List<Author> authors = new ArrayList<>();
        try (Connection con = dataSource.getConnection()) {
            final String SQL = "SELECT first_name, last_name, birth_date FROM author;";
            PreparedStatement ps = con.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Author author = new Author(rs.getString(2), rs.getString(3), rs.getDate(4));
                author.setId(rs.getInt(1));
                authors.add(author);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return authors;
    }
}
