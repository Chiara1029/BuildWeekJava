package it.team8.bw.entities.means;

import it.team8.bw.abstractClass.Means;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "maintenance")
public class Maintenance {
    protected LocalDate startMaintenance;
    protected LocalDate finishMaintenance;
    @ManyToOne
    @JoinColumn(name = "means_id")
    protected Means means;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    public Maintenance() {
    }

    public Maintenance(LocalDate startMaintenance, LocalDate finishMaintenance, Means means) {
        this.startMaintenance = startMaintenance;
        this.finishMaintenance = finishMaintenance;
        this.means = means;
    }

    public LocalDate getStartMaintenance() {
        return startMaintenance;
    }

    public void setStartMaintenance(LocalDate startMaintenance) {
        this.startMaintenance = startMaintenance;
    }

    public LocalDate getFinishMaintenance() {
        return finishMaintenance;
    }

    public void setFinishMaintenance(LocalDate finishMaintenance) {
        this.finishMaintenance = finishMaintenance;
    }

    public Means getMeans_id() {
        return means;
    }

    public void setMeans_id(Means means_id) {
        this.means = means_id;
    }

    public Long getId() {
        return id;
    }


}
