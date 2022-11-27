package com.codecool.bookdb.model;

import javax.sql.DataSource;
import java.util.List;

public class AuthorDaoJdbc implements AuthorDao{
    private final DataSource dataSource;
    public AuthorDaoJdbc(DataSource dataSource) {
    this.dataSource = dataSource;
    }

    @Override
    public void add(Author author) {

    }

    @Override
    public void update(Author author) {

    }

    @Override
    public Author get(int id) {
        return null;
    }

    @Override
    public List<Author> getAll() {
        return null;
    }
}
