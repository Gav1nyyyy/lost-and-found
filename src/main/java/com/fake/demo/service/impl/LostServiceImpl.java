package com.fake.demo.service.impl;

import com.fake.demo.bean.entity.Lost;
import com.fake.demo.exception.NoSuchIdException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
public class LostServiceImpl {

    private String JDBC_DRIVER;
    private String DB_URL;
    private String USER = "root";
    private String PASS = "123456";

    public LostServiceImpl(String JDBC_DRIVER, String DB_URL, String USER, String PASS) {
        this.JDBC_DRIVER = JDBC_DRIVER;
        this.DB_URL = DB_URL;
        this.USER = USER;
        this.PASS = PASS;
    }

//    private final DataSource dataSource;

    public void create(Lost lost) {
        lost.setNameID(UUID.randomUUID().toString());
        String sql = "INSERT INTO lost_list (name_id, name, item_desc, lost_time, grade, found_item, id_if_found) VALUES (?, ?, ?, ?, ?, ?, ?)";
        modifyMysql(sql, lost);
    }

    public void update(Lost lost) {
        String sql = "UPDATE lost_list set name = ?, item_desc = ?, lost_time = ?, grade = ?, found_item = ?, id_if_found = ? where name_id = ?";
        modifyMysql(sql, lost);
    }

    public void updateStatus(String id, String itemId){
        Lost lost = fetchById(id);
        lost.setFoundItem(1);
        lost.setIdIfFound(itemId);
        update(lost);
    }

    public void remove(String id) {
        String sql = "DELETE FROM lost_list WHERE name_id = ?";
        Lost lostToDelete = fetchById(id);
        modifyMysql(sql, lostToDelete);
    }

    public Lost fetchById(String id){
        String sql = "SELECT * FROM lost_list WHERE name_id = '" + id + "'";
        return queryMysql(sql);
    }

    public List<Lost> fetchByPage(int size, int page){
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int begin = (page - 1) * size;
        List<Lost> result = new ArrayList<>();
        try {
            Class.forName(JDBC_DRIVER);
            log.info("connecting to database...");
            con = DriverManager.getConnection(DB_URL, USER, PASS);

            String sql = "SELECT * FROM lost_list LIMIT " + size + " OFFSET " + begin;

            // execute sql statement
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery(sql);
            while(rs.next()){
                result.add(getLostObject(rs));
            }
            return result;
        } catch (Exception e){
            log.error("connection error", e);
            return null;
        } finally{
            try{
                if(rs != null){
                    rs.close();
                }
            } catch (SQLException se){
                log.error("rs close error", se);
            }

            try{
                if(stmt != null){
                    stmt.close();
                }
            } catch (SQLException se){
                log.error("stmt close error", se);
            }

            try{
                if(con != null){
                    con.close();
                }
            } catch (SQLException se){
                // fixme
                log.error("con close error", se);
            }
        }
    }

    public Lost getLostObject(ResultSet rs) throws SQLException {
        String nameID = rs.getString("name_id");
        String name = rs.getString("name");
        String itemDescription = rs.getString("item_desc");

        java.sql.Date sqlDate = rs.getDate("lost_time");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String lostTime = format.format(sqlDate);

        String grade = rs.getString("grade");
        int foundItem = rs.getInt("found_item");
        String idIfFound = rs.getString("id_if_found");

        return new Lost(nameID, name, itemDescription, lostTime, grade, foundItem, idIfFound);
    }

    public void modifyMysql(String sql, Lost lost){

        Connection con = null;
        PreparedStatement stmt = null;
        try {
            Class.forName(JDBC_DRIVER);
            log.info("connecting to database... {}", sql);
            con = DriverManager.getConnection(DB_URL, USER, PASS);

            // execute sql statement
            stmt = con.prepareStatement(sql);

            // check types
            String[] words = sql.split(" ");
            if(words[0].equalsIgnoreCase("INSERT")){
                // name_id, name, item_desc, lost_time, grade, found_item, id_if_found
                stmt.setString(1, lost.getNameID());
                stmt.setString(2, lost.getName());
                stmt.setString(3, lost.getItemDescription());
                stmt.setString(4, lost.getLostTime());
                stmt.setString(5, lost.getGrade());
                stmt.setInt(6, lost.getFoundItem());
                stmt.setString(7, lost.getIdIfFound());
            } else if (words[0].equalsIgnoreCase("UPDATE")){
                // name = ?, item_desc = ?, lost_time = ?, grade = ?, found_item = ?, id_if_found = ?
                stmt.setString(1, lost.getName());
                stmt.setString(2, lost.getItemDescription());
                stmt.setString(3, lost.getLostTime());
                stmt.setString(4, lost.getGrade());
                stmt.setInt(5, lost.getFoundItem());
                stmt.setString(6, lost.getIdIfFound());
                stmt.setString(7, lost.getNameID());
            } else if (words[0].equalsIgnoreCase("DELETE")){
                stmt.setString(1, lost.getNameID());
            }

            // support more operations
            stmt.execute();

        } catch (Exception e){
            log.error("connection error", e);
        } finally{
            try{
                if(stmt != null){
                    stmt.close();
                }
            } catch (SQLException se){
                log.error("stmt close error", se);
            }
            try{
                if(con != null){
                    con.close();
                }
            } catch (SQLException se){
                // fixme
                log.error("con close error", se);
            }
        }
    }

    public Lost queryMysql(String sql) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            Class.forName(JDBC_DRIVER);
            log.info("connecting to database...");
            con = DriverManager.getConnection(DB_URL, USER, PASS);

            // execute sql statement
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery(sql);
            if(rs.next()){
                return getLostObject(rs);
            }else{
                throw new NoSuchIdException();
            }
        } catch (NoSuchIdException e){
            throw e;
        } catch (Exception e){
            log.error("connection error", e);
            return null;
        } finally{
            try{
                if(rs != null){
                    rs.close();
                }
            } catch (SQLException se){
                log.error("rs close error", se);
            }

            try{
                if(stmt != null){
                    stmt.close();
                }
            } catch (SQLException se){
                log.error("stmt close error", se);
            }

            try{
                if(con != null){
                    con.close();
                }
            } catch (SQLException se){
                // fixme
                log.error("con close error", se);
            }
        }
    }
}
