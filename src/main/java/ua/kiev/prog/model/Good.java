package ua.kiev.prog.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by User on 26.12.2016.
 */

@Entity
@Table(name = "goods")
public class Good {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "price", nullable = false)
    private double price;



    public Good() {
    }

    public Good(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
}
