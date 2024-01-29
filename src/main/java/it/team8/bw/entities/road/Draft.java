package it.team8.bw.entities.road;

import it.team8.bw.abstractClass.Means;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "draft")

public class Draft {
    @OneToOne
    @JoinColumn(name = "means_id")
    private Means means_id;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    private String departur;
    private String arrival;
    @OneToMany
    @JoinColumn(name = "stop_id")
    private Set<Stop> stop = new HashSet<>();

    public Draft() {
    }

    public Draft(String departur, String arrival) {
        this.departur = departur;
        this.arrival = arrival;
    }

    public Means getMeans_id() {
        return means_id;
    }

    public void setMeans_id(Means means_id) {
        this.means_id = means_id;
    }

    public String getDepartur() {
        return departur;
    }

    public void setDepartur(String departur) {
        this.departur = departur;
    }

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public Set<Stop> getStop() {
        return stop;
    }

    public void setStop(Set<Stop> stop) {
        this.stop = stop;
    }


    public Long getId() {
        return id;
    }


}
