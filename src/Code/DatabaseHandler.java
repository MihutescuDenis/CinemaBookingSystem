package Code;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DatabaseHandler {

    //de adaugat creare automata a bazei de date cinema
    //conturile admin si user predefinire
    // creare tabel users

    private static final String JDBC_Driver = "com.mysql.cj.jdbc.Driver";
    private static final String connStr = "jdbc:mysql://localhost:3306/cinema";

    public static Connection getConnection() throws Exception{
        Class.forName(JDBC_Driver);
        return DriverManager.getConnection(connStr, "root", "");
    }

    public static ObservableList<User> getUserList () throws Exception{
        ObservableList<User> usersList = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String query = "SELECT `id`, `firstName`, `lastName`, `username`, `password`, `email`, `isAdmin` FROM `users`";
        Statement st;
        ResultSet rs;

        try{
            st = conn.createStatement();
            rs = st.executeQuery(query);
            User users;
            while(rs.next()){
                users = new User(rs.getInt("id"), rs.getString("firstName"), rs.getString("lastName"), rs.getString("username"), rs.getString("password"), rs.getString("email"), rs.getBoolean("isAdmin"));
                usersList.add(users);
            }

        }catch(Exception ex){
            ex.printStackTrace();
        }
        return usersList;
    }

    public static void insertUser(String firstName, String lastName, String username, String password, String email) throws Exception {
        String query = "INSERT INTO `users`(`firstName`, `lastName`, `username`, `password`, `email`) VALUES ('" + firstName + "','" + lastName + "','" + username + "','"
                + password  + "','" + email + "')";
        executeQuery(query);
    }

    public static void deleteUser(String id) throws Exception {
        String query = "DELETE FROM users where id =" + id + "";
        executeQuery(query);
    }

    public static void updateUser(String id, String firstName, String lastname, String username, String password, String email) {
        try {
            String query ="UPDATE users SET firstName=?, lastname=?, username=?, password=?, email=? where id=?";
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, firstName);
            ps.setString(2, lastname);
            ps.setString(3, username);
            ps.setString(4, password);
            ps.setString(5, email);
            ps.setString(6, id);
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void executeQuery(String query) throws Exception {
        Connection conn = getConnection ();
        Statement st;
        try {
            st = conn.createStatement();
            st.executeUpdate(query);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    public static void insertMovieRoom(String movieTitle) throws Exception {
        boolean[] arr = new boolean[20];
        for (int i=0;i< arr.length; i++) {
            Random random = new Random();
            arr[i] = random.nextBoolean();
        }
//        cast bool val to int val for database
        int[] seat = new int[20];
        for (int i=0 ; i< arr.length; i++) {
            int intValue;
            if(arr[i]){
                intValue = 1;
            }else {
                intValue = 0;
            }
            seat[i] = intValue;
        }

        String query = "INSERT INTO `rooms` (`movieTitle`, `seat1`, `seat2`, `seat3`, `seat4`, `seat5`, `seat6`, `seat7`, `seat8`, `seat9`, `seat10`, `seat11`, `seat12`, `seat13`, `seat14`, `seat15`, `seat16`, `seat17`, `seat18`, `seat19`, `seat20`) VALUES ('"
                + movieTitle + "','" + seat[0] + "','" + seat[1] + "','" + seat[2]  + "','" + seat[3]  + "','"
                + seat[4]  + "','" + seat[5]  + "','" + seat[6]  + "','" + seat[7]  + "','" + seat[8]  + "','"
                + seat[9]  + "','" + seat[10] + "','" + seat[11] + "','" + seat[12] + "','" + seat[13] + "','"
                + seat[14] + "','" + seat[15] + "','" + seat[16] + "','" + seat[17] + "','" + seat[18] + "','"
                + seat[19] + "')";

        //String query = "INSERT INTO `rooms` (`id`, `movieTitle`, `seat1`, `seat2`, `seat3`, `seat4`, `seat5`, `seat6`, `seat7`, `seat8`, `seat9`, `seat10`, `seat11`, `seat12`, `seat13`, `seat14`, `seat15`, `seat16`, `seat17`, `seat18`, `seat19`, `seat20`) VALUES ('2', 'aaaa', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0')";

        executeQuery(query);
    }

    public static List<Seats> getSeatsList() throws Exception{
        List<Seats> seatsList = new ArrayList<>();
        Connection conn = getConnection();
        String query = "SELECT * FROM `rooms`";
        Statement st;
        ResultSet rs;

        try{
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Seats seats;
            while(rs.next()){
                boolean[] arr = new boolean[20];
                for (int i = 1; i < 21; i++){
                    arr[i-1] = rs.getBoolean("seat" + i);
                }
                seats = new Seats(rs.getInt("id"), rs.getString("movieTitle"),arr);
                seatsList.add(seats);

            }
        }catch (Exception ex){
            ex.printStackTrace();
        }

        return seatsList;
    }

    public static void updateSeats(boolean[] seatArray, int id){
        try {
            String query ="UPDATE rooms SET seat1=?, seat2=?, seat3=?, seat4=?, seat5=?, seat6=?, seat7=?, seat8=?, seat9=?, seat10=?, seat11=?, seat12=?, seat13=?, seat14=?, seat15=?, seat16=?, seat17=?, seat18=?, seat19=?, seat20=? where id=?";
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setBoolean(1, seatArray[0]);
            ps.setBoolean(2, seatArray[1]);
            ps.setBoolean(3, seatArray[2]);
            ps.setBoolean(4, seatArray[3]);
            ps.setBoolean(5, seatArray[4]);
            ps.setBoolean(6, seatArray[5]);
            ps.setBoolean(7, seatArray[6]);
            ps.setBoolean(8, seatArray[7]);
            ps.setBoolean(9, seatArray[8]);
            ps.setBoolean(10, seatArray[9]);
            ps.setBoolean(11, seatArray[10]);
            ps.setBoolean(12, seatArray[11]);
            ps.setBoolean(13, seatArray[12]);
            ps.setBoolean(14, seatArray[13]);
            ps.setBoolean(15, seatArray[14]);
            ps.setBoolean(16, seatArray[15]);
            ps.setBoolean(17, seatArray[16]);
            ps.setBoolean(18, seatArray[17]);
            ps.setBoolean(19, seatArray[18]);
            ps.setBoolean(20, seatArray[19]);
            ps.setInt(21, id);
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*Tabel sql creation and database*/
    public static void createRooms() throws Exception {
        String query = "CREATE TABLE IF NOT EXISTS `cinema`.`rooms` ( `id` INT(10) NULL AUTO_INCREMENT , `movieTitle` VARCHAR(45) NULL , `seat1` BOOLEAN NULL DEFAULT FALSE , `seat2` BOOLEAN NULL DEFAULT FALSE , `seat3` BOOLEAN NULL DEFAULT FALSE , `seat4` BOOLEAN NULL DEFAULT FALSE , `seat5` BOOLEAN NULL DEFAULT FALSE , `seat6` BOOLEAN NULL DEFAULT FALSE , `seat7` BOOLEAN NULL DEFAULT FALSE , `seat8` BOOLEAN NULL DEFAULT FALSE , `seat9` BOOLEAN NULL DEFAULT FALSE , `seat10` BOOLEAN NULL DEFAULT FALSE , `seat11` BOOLEAN NULL DEFAULT FALSE , `seat12` BOOLEAN NULL DEFAULT FALSE , `seat13` BOOLEAN NULL DEFAULT FALSE , `seat14` BOOLEAN NULL DEFAULT FALSE , `seat15` BOOLEAN NULL DEFAULT FALSE , `seat16` BOOLEAN NULL DEFAULT FALSE , `seat17` BOOLEAN NULL DEFAULT FALSE , `seat18` BOOLEAN NULL DEFAULT FALSE , `seat19` BOOLEAN NULL DEFAULT FALSE , `seat20` BOOLEAN NULL DEFAULT FALSE , PRIMARY KEY (`id`)) ENGINE = InnoDB;";
        executeQuery(query);
        String query2 = "ALTER TABLE `cinema`.`rooms` DROP INDEX `UC_rooms`, ADD UNIQUE `UC_rooms` (`movieTitle`) USING BTREE";
        executeQuery(query2);
    }

    }