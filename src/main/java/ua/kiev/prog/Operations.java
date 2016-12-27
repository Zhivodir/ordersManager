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
    private DBConnect dbConnect;
    private Scanner sc;

    public Operations(DBConnect dbConnect) {
        this.dbConnect = dbConnect;
        this.sc = new Scanner(System.in);

        while(true){
            System.out.println();
            System.out.println("Choice an operation:");
            System.out.println("1.Add new client");
            System.out.println("2.Add new good");
            System.out.println("3.Create new order");
            System.out.println("4.Cancel new order");

            int choice = Integer.parseInt(sc.nextLine());
            select(choice);
        }
    }

    public void select(int choice){
        switch (choice){
            case 1:
                addClient();
                break;
            case 2:
                addGood();
                break;
            case 3:
                addOrder();
                break;
            case 4:
                break;
            default:
                break;
        }
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
        query.append("INSERT INTO goodsinorders (id_good, id_order) VALUES");
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
                System.out.println(query);
                try(PreparedStatement ps = dbConnect.getConnection().prepareStatement(query.toString())){
                    for(int i = 0; i < goods.size(); i++) {
                        ps.setInt(i+1, goods.get(i));
                    }
                    ps.executeUpdate();
                }catch(SQLException e){e.printStackTrace();}
            }else {
                int id = Integer.parseInt(enter);
                goods.add(id);
            }
        }
    }
}
