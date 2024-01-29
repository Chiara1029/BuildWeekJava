package it.team8.bw.abstractClass;

import it.team8.bw.entities.Draft;
import it.team8.bw.entities.Maintenance;
import it.team8.bw.enums.MeansStatus;

import javax.persistence.*;
import java.util.Set;

@Entity

@Table(name = "means")
public abstract class Means {
    protected int capacity;
    @Enumerated(EnumType.STRING)
    protected MeansStatus meansStatus;
    protected int obliterated;

    @OneToOne(mappedBy = "means_id")
    protected Draft draft;

    @OneToMany(mappedBy = "means_id")
    protected Set<Maintenance> maintenance;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    protected Means() {
    }

    public Means(int capacity, MeansStatus meansStatus, Draft draft, Set<Maintenance> maintenance) {
        this.capacity = capacity;
        this.meansStatus = meansStatus;
        this.obliterated = 0;
        this.draft = draft;
        this.maintenance = maintenance;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public MeansStatus getMeansStatus() {
        return meansStatus;
    }

    public void setMeansStatus(MeansStatus meansStatus) {
        this.meansStatus = meansStatus;
    }

    public int isObliterated() {
        return obliterated;
    }

    public void setObliterated(int obliterated) {
        this.obliterated = obliterated;
    }

    public Draft getDraft() {
        return draft;
    }

    public void setDraft(Draft draft) {
        this.draft = draft;
    }

    public Set<Maintenance> getMaintenance() {
        return maintenance;
    }

    public void setMaintenance(Set<Maintenance> maintenance) {
        this.maintenance = maintenance;
    }

    public Long getId() {
        return id;
    }

}
