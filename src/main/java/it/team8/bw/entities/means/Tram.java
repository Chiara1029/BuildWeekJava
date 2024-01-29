package it.team8.bw.entities.means;

import it.team8.bw.abstractClass.Means;
import it.team8.bw.entities.means.Maintenance;
import it.team8.bw.entities.road.Draft;
import it.team8.bw.enums.MeansStatus;

import javax.persistence.*;
import java.util.Set;

@Entity
@DiscriminatorValue("tram")
public class Tram extends Means {

    public Tram() {
    }

    public Tram(MeansStatus meansStatus, Draft draft, Set<Maintenance> maintenance) {
        super(80, meansStatus, draft, maintenance);
    }

    @Override
    public String toString() {
        return "Tram{" +
                "id=" + super.getId() +
                ", capacity=" + capacity +
                ", meansStatus=" + meansStatus +
                ", obliterated=" + obliterated +
                ", draft=" + draft +
                ", maintenance=" + maintenance +
                '}';
    }
}
