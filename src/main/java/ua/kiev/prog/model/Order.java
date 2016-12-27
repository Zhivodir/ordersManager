package ua.kiev.prog.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

/**
 * Created by User on 26.12.2016.
 */

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "date")
    @Temporal(value=TemporalType.TIMESTAMP)
    private Date date;

    @ManyToMany
    @JoinTable(
            name = "goodsinorders",
            joinColumns = @JoinColumn(name="id_client", referencedColumnName="id"),
            inverseJoinColumns=@JoinColumn(name="id_order", referencedColumnName="id"))
    private List<Good> goods;

    public Order() {
    }

    public Order(int id, Date date, List<Good> goods) {
        this.id = id;
        this.date = date;
        this.goods = goods;
    }
}
