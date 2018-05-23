package com.kirichenko.utils;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;


public abstract class AbstractController<E, K> {

    private Connection connection = null;
    private Statement statement = null;

   // @Singleton
    public AbstractController() throws ClassNotFoundException, SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        connection = (Connection) DriverManager
                .getConnection("jdbc:mysql://localhost:3306/atg?" + "user=root&password=8926024");

        statement = (Statement) connection.createStatement();

    }

    public abstract List<E> getAll();
    public abstract E update(E entity);
    public abstract E getEntityById(K id);
    public abstract boolean delete(K id);
    public abstract boolean create(E entity);

    public Statement getPrepareStatement() {
        return statement;
    }

    public void closePrepareStatement(Statement ps) {
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}