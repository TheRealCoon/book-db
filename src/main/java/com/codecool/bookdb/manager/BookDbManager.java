package com.codecool.bookdb.manager;

import com.codecool.bookdb.model.AuthorDao;
import com.codecool.bookdb.model.BookDao;
import com.codecool.bookdb.view.UserInterface;
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;

public class BookDbManager {

    UserInterface ui;
    AuthorDao authorDao;
    BookDao bookDao;

    public BookDbManager(UserInterface ui) {
        this.ui = ui;
    }
    public DataSource connect() throws SQLException {
        var dataSource = new PGSimpleDataSource();
        dataSource.setServerName("localhost");
        dataSource.setPortNumber(5432);
        dataSource.setUser(System.getenv("USER"));
        dataSource.setPassword(System.getenv("PASS"));
        dataSource.setDatabaseName("books");
        return dataSource;
    }
}
