package ua.kiev.prog;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.Scanner;

/**
 * Created by User on 26.12.2016.
 */
public class Manager {
    private static EntityManagerFactory emf;
    private static EntityManager em;
    private Scanner scanner;

    public Manager() {
        this.scanner = new Scanner(System.in);
        while(true){
            System.out.println();
            System.out.println("Choice an operation:");
            System.out.println("1.Add new client");
            System.out.println("2.Add new good");
            System.out.println("3.Create new order");
            System.out.println("4.Cancel order");

            int choice = Integer.parseInt(scanner.nextLine());
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
                cancelOrder();
                break;
            default:
                break;
        }
    }
}
