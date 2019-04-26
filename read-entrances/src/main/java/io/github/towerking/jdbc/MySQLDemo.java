package io.github.towerking.jdbc;

import java.sql.*;

public class MySQLDemo {

    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/test";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "123456";


    /**
     * 创建表的 SQL 脚本
     *
     * CREATE TABLE `websites` (
     *   `id` int(11) NOT NULL AUTO_INCREMENT,
     *   `name` char(20) NOT NULL DEFAULT '' COMMENT '站点名称',
     *   `url` varchar(255) NOT NULL DEFAULT '',
     *   `alexa` int(11) NOT NULL DEFAULT '0' COMMENT 'Alexa 排名',
     *   `country` char(10) NOT NULL DEFAULT '' COMMENT '国家',
     *   PRIMARY KEY (`id`)
     * ) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
     *
     * */
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        // simpleQuery();

        // System.out.println(updateWebSites(1, "GOOGLE"));
        paramQuery(1);

        /*int id = insertWebSites("BAIDU", "http://www.baidu.com", 5200, "CN");
        System.out.println(id);
        paramQuery(id);*/

        // System.out.println(deleteWebSites("BAIDU"));
    }

    private static void simpleQuery() throws ClassNotFoundException, SQLException {
        Connection connection;
        Statement statement;

        Class.forName(JDBC_DRIVER);

        connection = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
        statement = connection.createStatement();
        String sql = "select id, `name`, url, alexa, country from websites";

        ResultSet resultSet = statement.executeQuery(sql);
        outputResult(resultSet);
        statement.close();
        connection.close();
    }


    private static void paramQuery(int id) throws ClassNotFoundException, SQLException {
        Connection connection = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
        PreparedStatement preparedStatement;
        Class.forName(JDBC_DRIVER);

        String sql = "select id, `name`, url, alexa, country from websites where id = ?";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
        ResultSet resultSet = preparedStatement.getResultSet();
        outputResult(resultSet);
        preparedStatement.close();
        connection.close();
    }

    private static int updateWebSites(int id, String name) throws SQLException, ClassNotFoundException {
        Connection connection = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
        PreparedStatement preparedStatement;
        Class.forName(JDBC_DRIVER);

        String updateSql = "update websites set `name` = ? where id = ?";
        preparedStatement = connection.prepareStatement(updateSql);
        preparedStatement.setString(1, name);
        preparedStatement.setInt(2, id);
        int result = preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();
        return result;
    }

    private static int insertWebSites(String name, String url, int alexa, String country) throws SQLException {
        Connection connection = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
        String insertSql = "insert websites (name, url, alexa, country) values (?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, url);
        preparedStatement.setInt(3, alexa);
        preparedStatement.setString(4, country);
        preparedStatement.executeUpdate();

        ResultSet key = preparedStatement.getGeneratedKeys();
        key.next();
        int id = key.getInt(1);
        System.out.println("自增长 ID: " + id);

        preparedStatement.close();
        connection.close();
        return id;
    }

    private static int deleteWebSites(String name) throws SQLException {
        Connection connection = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
        String deleteSql = "delete from websites where name = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(deleteSql);
        preparedStatement.setString(1, name);
        int result = preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();
        return result;
    }

    private static void outputResult(ResultSet resultSet) throws SQLException {
        if (resultSet == null) {
            System.out.println("没有查询到数据.");
            return;
        }

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String url = resultSet.getString("url");

            System.out.println(String.format("ID: %s, name: %s, url: %s", id, name, url));
        }

        resultSet.close();
    }



}
