package it.team8.bw.entities;

import it.team8.bw.abstractClass.Means;

import javax.persistence.*;

@Entity
@Table(name = "draft")

public class Draft {
    @OneToOne
    @JoinColumn(name = "means_id")
    protected Means means_id;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    public Long getId() {
        return id;
    }


}
