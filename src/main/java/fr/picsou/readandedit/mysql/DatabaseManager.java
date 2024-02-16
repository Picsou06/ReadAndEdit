package fr.picsou.readandedit.mysql;

import org.bukkit.Bukkit;

import java.sql.*;
import java.util.UUID;

public class DatabaseManager {

    private String urlBase;
    private String host;
    private String database;
    private String userName;
    private String password;

    private int port;
    private static Connection connection;

    public DatabaseManager(String host, Integer port, String database, String userName, String password) {
        this.host = host;
        this.port = port;
        this.userName = userName;
        this.password = password;
        this.database = database;
    }
    public static Connection getConnection() {
        return connection;
    }

    public void connection() {
        if (!isOnline()) {
            try {
                connection = DriverManager.getConnection("jdbc:mysql://" + this.host + ":" + this.port + "/" +this.database, this.userName, this.password);
                Bukkit.getConsoleSender().sendMessage("[§eReadAndEdit§6] §aON");
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                PreparedStatement preparedStatement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS Book (Id INT(11) AUTO_INCREMENT PRIMARY KEY, UUID TEXT COLLATE utf8mb4_general_ci, Book TEXT COLLATE utf8mb4_general_ci);");
                preparedStatement.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public boolean hasbook(UUID uuid){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT UUID FROM Book WHERE UUID = ?");
            preparedStatement.setString(1, uuid.toString());
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){
                return false;
            }
            return true;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    public void editbook(UUID uuid, String book){
        if (!hasbook(uuid)){
            try {
                PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Book SET Book = ? WHERE UUID = ?");
                preparedStatement.setString(1, book);
                preparedStatement.setString(2, uuid.toString());
                preparedStatement.executeUpdate();

            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        else{
            try {
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Book (UUID, Book) VALUES (?, ?)");
                preparedStatement.setString(1, uuid.toString());
                preparedStatement.setString(2, book);
                preparedStatement.execute();

            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }
    public String getbook(UUID uuid){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT Book FROM Book WHERE UUID = ?");
            preparedStatement.setString(1, uuid.toString());
            ResultSet resultSet = preparedStatement.executeQuery();

            String book = null;
            if(resultSet.next()) {
                return resultSet.getString("Book");
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    public void removeBook(UUID uuid){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Book WHERE UUID = ?");
            preparedStatement.setString(1, uuid.toString());
            preparedStatement.execute();
            preparedStatement.close();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }


    public void deconnexion() {
        if (isOnline())
            try {
                connection.close();
                Bukkit.getConsoleSender().sendMessage("[§eReadAndEdit§6] §cOFF");
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

    public boolean isOnline() {
        try {
            if (connection == null || connection.isClosed())
                return false;
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}