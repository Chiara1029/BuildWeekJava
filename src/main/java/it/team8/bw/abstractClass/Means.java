package it.team8.bw.abstractClass;

import javax.persistence.*;

@Entity
@Table(name = "ticket_office")
public abstract class Means {
    protected int capacity;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    public Long getId() {
        return id;
    }

}
