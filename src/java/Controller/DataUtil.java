/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.CuaHang;
import Model.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author vancu
 */
public class DataUtil {

    private final String HOSTNAME = "localhost";
    private final String DBNAME = "ordergas_database";
    private final String USERNAME = "ordergas_hdc";
    private final String PASSWORD = "Cuongvp96";
//     private final String HOSTNAME = "node15010-grgoigas.kilatiron.com";
//    private final String DBNAME = "goigas";
//    private final String USERNAME = "vancuong";
//    private final String PASSWORD = "SCYnhv96638";
    private ResultSet rs = null;
    private Connection connection = null;

    public Connection getConnection() throws ClassNotFoundException, SQLException {
        if (connection != null) {
            return connection;
        } else {
            Class.forName("com.mysql.jdbc.Driver");
            String connectionURL = "jdbc:mysql://" + HOSTNAME + ":3306/" + DBNAME + "?useUnicode=true&amp;characterEncoding=utf8&useSSL=false";
//            String connectionURL = "jdbc:mysql://" + HOSTNAME + "/" + DBNAME+"?useUnicode=true&amp;characterEncoding=utf8";

            connection = DriverManager.getConnection(connectionURL, USERNAME,
                    PASSWORD);
            return connection;
        }
    }

    public void closeConnection() throws ClassNotFoundException, SQLException {
        if (getConnection() != null) {
            connection.close();
        }
    }

    public User checkLogin(User user) throws SQLException {
        String sql = "SELECT * FROM user  where user_name=? and password=?";
        PreparedStatement pst;
        pst = connection.prepareStatement(sql);
        pst.setString(1, user.getUser_name());
        pst.setString(2, user.getPassword());
        rs = pst.executeQuery();

        User s = null;
        if (rs.next()) {
            s = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
                    rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9));
        }

        return s;
    }

    public User checkLoginGG(String uid) throws SQLException {
        String sql = "SELECT * FROM user  where googleid=?";
        PreparedStatement pst;
        pst = connection.prepareStatement(sql);
        pst.setString(1, uid);

        rs = pst.executeQuery();

        User s = null;
        if (rs.next()) {
            s = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
                    rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9));
        }

        return s;
    }

    public User checkLoginFB(String uid) throws SQLException {
        String sql = "SELECT * FROM user  where fbid=?";
        PreparedStatement pst;
        pst = connection.prepareStatement(sql);
        pst.setString(1, uid);

        rs = pst.executeQuery();

        User s = null;
        if (rs.next()) {
            s = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
                    rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9));
        }

        return s;
    }

    public ArrayList<CuaHang> getListCHbyId(String id) throws SQLException {
        ArrayList<CuaHang> list = new ArrayList<>();
//        String sql = "SELECT * FROM cuahang  where user_id=?";
  String sql = "SELECT idcuahang,tencuahang,loaigas,giagas,cuahang.sdt,"
                + "chucuahang,diadiem,latlng,linkimg,cuahang.user_id,votescore,"
                + "votenumber,commentnumber,user.user_name FROM cuahang inner join"
                + " user on user.user_id=cuahang.user_id where cuahang.user_id=?";
        PreparedStatement pst;
        pst = connection.prepareStatement(sql);
        pst.setString(1, id);
        rs = pst.executeQuery();
        CuaHang s = null;
        while (rs.next()) {
            s = new CuaHang(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getFloat(4),
                    rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9),
                    rs.getInt(10), rs.getFloat(11), rs.getInt(12), rs.getInt(13),rs.getString(14));
            list.add(s);
        }
        return list;
    }

    public ArrayList<CuaHang> getListCHbyIdBeginEnd(String begin, String numend, String idch) throws SQLException {
        ArrayList<CuaHang> list = new ArrayList<>();
//        String sql = "SELECT * FROM cuahang where user_id='" + idch + "' limit " + begin + "," + numend;
         String sql = "SELECT idcuahang,tencuahang,loaigas,giagas,cuahang.sdt,"
                + "chucuahang,diadiem,latlng,linkimg,cuahang.user_id,votescore,"
                + "votenumber,commentnumber,user.user_name FROM cuahang inner join"
                + " user on user.user_id=cuahang.user_id where user.user_id='" + idch + "' limit " + begin + "," + numend;
        Statement statement;
        statement = connection.createStatement();
        rs = statement.executeQuery(sql);
        while (rs.next()) {
            CuaHang s = new CuaHang(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getFloat(4),
                    rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9),
                    rs.getInt(10), rs.getFloat(11), rs.getInt(12), rs.getInt(13),rs.getString(14));
            list.add(s);
        }
        return list;
    }

    public CuaHang getCHById(String id) throws SQLException {
        String sql = "SELECT * FROM cuahang where idcuahang=?";
        PreparedStatement pst;
        pst = connection.prepareStatement(sql);
        pst.setString(1, id);
        rs = pst.executeQuery();
        CuaHang s = null;
        if (rs.next()) {
            s = new CuaHang(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getFloat(4),
                    rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9),
                    rs.getInt(10), rs.getFloat(11), rs.getInt(12), rs.getInt(13));
        }
        return s;
    }

    public CuaHang getCHByName(String name) throws SQLException {
        ArrayList<CuaHang> list = new ArrayList<>();
        String sql = "SELECT * FROM cuahang where tencuahang=?";
        PreparedStatement pst;
        pst = connection.prepareStatement(sql);
        pst.setString(1, name);
        rs = pst.executeQuery();
        CuaHang s = null;
        if (rs.next()) {
            s = new CuaHang(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getFloat(4),
                    rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9),
                    rs.getInt(10), rs.getFloat(11), rs.getInt(12), rs.getInt(13));
            list.add(s);
        }
        return s;
    }

    public ArrayList<CuaHang> getAllCH() throws SQLException {
        ArrayList<CuaHang> list = new ArrayList<>();
//        String sql = "SELECT * FROM cuahang";
        String sql = "SELECT idcuahang,tencuahang,loaigas,giagas,cuahang.sdt,"
                + "chucuahang,diadiem,latlng,linkimg,cuahang.user_id,votescore,"
                + "votenumber,commentnumber,user.user_name FROM cuahang inner join"
                + " user on user.user_id=cuahang.user_id";
        Statement statement;
        statement = connection.createStatement();

        rs = statement.executeQuery(sql);

        while (rs.next()) {
            CuaHang s = new CuaHang(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getFloat(4),
                    rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9),
                    rs.getInt(10), rs.getFloat(11), rs.getInt(12), rs.getInt(13),rs.getString(14));
            list.add(s);
        }
        return list;
    }

    public ArrayList<CuaHang> getAllCHBeginEnd(String begin, String numend) throws SQLException {
        ArrayList<CuaHang> list = new ArrayList<>();
//        String sql = "SELECT * FROM cuahang limit " + begin + "," + numend;
  String sql = "SELECT idcuahang,tencuahang,loaigas,giagas,cuahang.sdt,"
                + "chucuahang,diadiem,latlng,linkimg,cuahang.user_id,votescore,"
                + "votenumber,commentnumber,user.user_name FROM cuahang inner join"
                + " user on user.user_id=cuahang.user_id limit " + begin + "," + numend;
        Statement statement;
        statement = connection.createStatement();
        rs = statement.executeQuery(sql);
        while (rs.next()) {
            CuaHang s = new CuaHang(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getFloat(4),
                    rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9),
                    rs.getInt(10), rs.getFloat(11), rs.getInt(12), rs.getInt(13),rs.getString(14));
            list.add(s);
        }
        return list;
    }

    public int delCuaHang(String chid) throws SQLException {
        String sql = "delete FROM cuahang  where idcuahang=?";
        PreparedStatement pst;
        pst = connection.prepareStatement(sql);
        pst.setString(1, chid);

        int k = pst.executeUpdate();
        return k;
    }

    public int addCuaHang(CuaHang c) throws SQLException {
        String sql = "insert into cuahang(tencuahang,loaigas,giagas,sdt,chucuahang,"
                + "diadiem,latlng,linkimg,user_id,votescore,votenumber,commentnumber) values(?,?,?,?,?,?,?,?,?,0,0,0)";
        PreparedStatement pst;
        pst = connection.prepareStatement(sql);
        pst.setString(1, c.getTencuahang());
        pst.setString(2, c.getLoaigas());
        pst.setFloat(3, c.getGiagas());
        pst.setString(4, c.getSdt());
        pst.setString(5, c.getChucuahang());
        pst.setString(6, c.getDiadiem());
        pst.setString(7, c.getLatlng());
        pst.setString(8, c.getLinkimg());
        pst.setInt(9, c.getUser_id());
        int k = pst.executeUpdate();
        return k;
    }

    public int editCuaHang(CuaHang c) throws SQLException {
        String sql = "update cuahang set tencuahang=?,loaigas=?,giagas=?,sdt=?,chucuahang=? where idcuahang=? and user_id=?";
        PreparedStatement pst;
        pst = connection.prepareStatement(sql);
        pst.setString(1, c.getTencuahang());
        pst.setString(2, c.getLoaigas());
        pst.setFloat(3, c.getGiagas());
        pst.setString(4, c.getSdt());
        pst.setString(5, c.getChucuahang());
        pst.setInt(6, c.getIdcuahang());
        pst.setInt(7, c.getUser_id());
        int k = pst.executeUpdate();
        return k;
    }

    public int editCuaHangAdmin(CuaHang c) throws SQLException {
        String sql = "update cuahang set tencuahang=?,loaigas=?,giagas=?,sdt=?,chucuahang=? where idcuahang=?";
        PreparedStatement pst;
        pst = connection.prepareStatement(sql);
        pst.setString(1, c.getTencuahang());
        pst.setString(2, c.getLoaigas());
        pst.setFloat(3, c.getGiagas());
        pst.setString(4, c.getSdt());
        pst.setString(5, c.getChucuahang());
        pst.setInt(6, c.getIdcuahang());
        int k = pst.executeUpdate();
        return k;
    }

    public int getCountCH() throws SQLException {
        int count = 0;
        String sql = "SELECT Count(*) FROM cuahang";
        Statement statement;
        statement = connection.createStatement();
        rs = statement.executeQuery(sql);
        rs.next();
        count = rs.getInt(1);
        return count;
    }

    public int getCountCHById(String idUser) throws SQLException {
        int count = 0;
        String sql = "SELECT Count(*) FROM cuahang where user_id='" + idUser + "'";
        Statement statement;
        statement = connection.createStatement();
        rs = statement.executeQuery(sql);
        rs.next();
        count = rs.getInt(1);
        return count;
    }
}
