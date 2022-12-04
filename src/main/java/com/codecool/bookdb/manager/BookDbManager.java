package com.codecool.bookdb.manager;

import com.codecool.bookdb.model.AuthorDao;
import com.codecool.bookdb.model.AuthorDaoJdbc;
import com.codecool.bookdb.model.BookDao;
import com.codecool.bookdb.model.BookDaoJdbc;
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

    public void run() {
        try {
            setup();
        } catch (SQLException throwables) {
            System.err.println("Could not connect to the database.");
            return;
        }
        mainMenu();
    }

    private void setup() throws SQLException {
        DataSource dataSource = connect();
        authorDao = new AuthorDaoJdbc(dataSource);
        bookDao = new BookDaoJdbc(dataSource, authorDao);
    }

    private void mainMenu() {
        boolean running = true;
        while (running) {
            ui.printTitle("Main Menu");
            ui.printOption('a', "Authors");
            ui.printOption('b', "Books");
            ui.printOption('q', "Quit");
            switch (ui.choice("abq")) {
                case 'a' -> new AuthorManager(ui, authorDao).run();
                case 'b' -> new BookManager(ui, bookDao, authorDao).run();
                case 'q' -> running = false;
            }
        }

    }
}
