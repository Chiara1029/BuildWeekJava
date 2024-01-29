package it.team8.bw.entities.road;

import javax.persistence.*;

@Entity
@Table(name = "stop")
public class Stop {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    private String departurStop;
    private String arrivalStop;
    @ManyToOne
    @JoinColumn(name = "draft_id")
    private Draft draft;

    public Stop() {
    }

    public Stop(String departurStop, String arrivalStop) {
        this.departurStop = departurStop;
        this.arrivalStop = arrivalStop;
    }

    public String getDeparturStop() {
        return departurStop;
    }

    public void setDeparturStop(String departurStop) {
        this.departurStop = departurStop;
    }

    public String getArrivalStop() {
        return arrivalStop;
    }

    public void setArrivalStop(String arrivalStop) {
        this.arrivalStop = arrivalStop;
    }

    public Draft getDraft() {
        return draft;
    }

    public void setDraft(Draft draft) {
        this.draft = draft;
    }

    public Long getId() {
        return id;
    }


}
