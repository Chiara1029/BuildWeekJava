package it.team8.bw.entities.road;

import it.team8.bw.abstractClass.Means;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "draft")

public class Draft {
    @OneToOne(mappedBy = "draft")
    private Means mean;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    private String departure;
    private String arrival;
    @OneToMany(mappedBy = "draft")
    private Set<Stop> stops;
    private int timeToTravel;


    public Draft() {
    }

    public Draft(String departure, String arrival, int timeToTravel) {
        this.departure = departure;
        this.arrival = arrival;
        this.timeToTravel = timeToTravel;
    }

    public Means getMeans_id() {
        return mean;
    }

    public void setMeans_id(Means means_id) {
        this.mean = means_id;
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
        return stops;
    }

    public void setStop(Set<Stop> stop) {
        this.stops = stop;
    }


    public Long getId() {
        return id;
    }

    public int getTimeToTravel() {
        return timeToTravel;
    }

    public void setTimeToTravel(int timeToTravel) {
        this.timeToTravel = timeToTravel;
    }

    @Override
    public String toString() {
        return "Draft{" +
                "departure='" + departure + '\'' +
                ", arrival='" + arrival + '\'' +
                ", stops=" + stops +
                '}';
    }

}
