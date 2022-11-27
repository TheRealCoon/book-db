package com.codecool.bookdb.manager;

import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;

public class BookDbManager {
    public DataSource connect() {
        var dataSource = new PGSimpleDataSource();
        dataSource.setServerName("localhost");
        dataSource.setPortNumber(5432);
        dataSource.setUser(System.getenv("USER"));
        dataSource.setPassword(System.getenv("PASS"));
        dataSource.setDatabaseName("books");
        return dataSource;
    }
}
