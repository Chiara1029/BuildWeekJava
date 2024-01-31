package it.team8.bw.abstractClass;

import it.team8.bw.entities.road.Draft;
import it.team8.bw.entities.means.Maintenance;
import it.team8.bw.entities.users.Ticket;
import it.team8.bw.enums.MeansStatus;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "means")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Means {
    protected int capacity;
    @Enumerated(EnumType.STRING)
    protected MeansStatus meansStatus;
    protected int obliterated;

    @OneToOne
    @JoinColumn(name = "draft_id")
    protected Draft draft;

    @OneToMany(mappedBy = "means")
    protected Set<Maintenance> maintenance;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToMany(mappedBy = "meanUsed")
    private Set<Ticket> obliteratedTickets;

    protected Means() {
    }

    public Means(int capacity, MeansStatus meansStatus, Draft draft) {
        this.capacity = capacity;
        this.meansStatus = meansStatus;
        this.obliterated = 0;
        this.draft = draft;

    }

    public int getCapacity() {

        if (capacity < 30) {
            throw new RuntimeException("Capacity cannot be less than 30.");
        }
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
