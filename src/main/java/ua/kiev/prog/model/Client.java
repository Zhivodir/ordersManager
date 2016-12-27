package ua.kiev.prog.model;

import javax.persistence.*;

/**
 * Created by User on 26.12.2016.
 */

@Entity
@Table(name = "clients")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    @Column(name = "name", nullable = false)
    private String name;

    public Client() {
    }

    public Client(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
