package com.codecool.bookdb;

import com.codecool.bookdb.manager.BookDbManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        BookDbManager bookDbManager = new BookDbManager();
        try {
            System.out.println("Connecting to database...");
            Connection con = bookDbManager.connect().getConnection();
            System.out.println("Connection OK");
            final String SQL = "Select * FROM book;";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(SQL);
            while(rs.next()){
                System.out.println(rs.getString(3));
            }
            con.close();
            System.out.println("Connection closed");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
