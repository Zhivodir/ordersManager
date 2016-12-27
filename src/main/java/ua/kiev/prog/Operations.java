package ua.kiev.prog;

import com.mysql.jdbc.Statement;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * Created by User on 21.12.2016.
 */
public class Operations {
    private Scanner sc;

    public Operations() {
    }

    public void addClient(){
        System.out.print("Enter a name of client: ");
        String name = sc.nextLine();
        try(PreparedStatement ps = dbConnect.getConnection().prepareStatement("INSERT INTO clients (name) VALUES (?)")){
            ps.setString(1, name);
            ps.executeUpdate();
        }catch(SQLException e){e.printStackTrace();}
    }

    public void addGood(){
        System.out.print("Enter a name of good: ");
        String name = sc.nextLine();
        System.out.print("Enter price of good: ");
        double price = Double.parseDouble(sc.nextLine());
        try(PreparedStatement ps = dbConnect.getConnection().prepareStatement("INSERT INTO goods (name, price) VALUES (?, ?)")){
            ps.setString(1, name);
            ps.setDouble(2, price);
            ps.executeUpdate();
        }catch(SQLException e){e.printStackTrace();}
    }

    public void addOrder(){
        StringBuilder query = new StringBuilder();
        query.append("INSERT INTO orders (id_client, date) VALUES");
        int id_order = 0;
        List<Integer> goods = new ArrayList<>();
        System.out.print("Enter the client for order: ");
        int id_client = Integer.parseInt(sc.nextLine());
        try(PreparedStatement ps = dbConnect.getConnection().
                prepareStatement(("INSERT INTO orders (id_client, date) VALUES (?, ?)"), Statement.RETURN_GENERATED_KEYS)){
            ps.setInt(1, id_client);
            ps.setDate(2, new java.sql.Date(new Date().getTime()));
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                id_order = rs.getInt(1);
            }
        }catch(SQLException e){e.printStackTrace();}

        while(true) {
            System.out.println("Add new good into order(enter id): ");
            String enter = sc.nextLine();
            if(enter.equals("send")){
                for(int i = 0; i < goods.size(); i++){
                    query.append("(?, " + id_order + ")");
                    if( i != goods.size() - 1){
                        query.append(",");
                    }
                }
                try(PreparedStatement ps = dbConnect.getConnection().prepareStatement(query.toString())){
                    ps.setInt(1, id_client);
                    ps.setDate(2, new java.sql.Date(new Date().getTime()));
                    ps.executeUpdate();
                    ResultSet rs = ps.getGeneratedKeys();
                    if (rs.next()) {
                        id_order = rs.getInt(1);
                    }
                }catch(SQLException e){e.printStackTrace();}
            }else {
                int id = Integer.parseInt(sc.nextLine());
                goods.add(id);
            }
        }



    }
}
