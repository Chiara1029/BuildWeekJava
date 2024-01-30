package it.team8.bw.entities.road;

import it.team8.bw.abstractClass.Means;

import javax.persistence.*;
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

    private String departure;
    private String arrival;
    @OneToMany
    @JoinColumn(name = "draft")
    private Set<Stop> stop;

    @OneToOne
    @JoinColumn(name = "means_id")
    private Means mean;

    public Draft() {
    }

    public Draft(String departure, String arrival) {
        this.departure = departure;
        this.arrival = arrival;
    }

    public Means getMeans_id() {
        return means_id;
    }

    public void setMeans_id(Means means_id) {
        this.means_id = means_id;
    }

    public String getDepartur() {
        return departure;
    }

    public void setDepartur(String departur) {
        this.departure = departur;
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

    @Override
    public String toString() {
        return "Draft{" +
                "departure='" + departure + '\'' +
                ", arrival='" + arrival + '\'' +
                ", stop=" + stop +
                '}';
    }
}
