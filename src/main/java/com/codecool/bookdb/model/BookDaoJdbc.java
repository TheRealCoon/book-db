package com.codecool.bookdb.model;

import javax.sql.DataSource;
import java.util.List;

public class BookDaoJdbc implements BookDao{
    private final DataSource dataSource;
    public BookDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    @Override
    public void add(Book book) {

    }

    @Override
    public void update(Book book) {

    }

    @Override
    public Book get(int id) {
        return null;
    }

    @Override
    public List<Book> getAll() {
        return null;
    }
}
