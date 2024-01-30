package it.team8.bw.entities.means;

import it.team8.bw.abstractClass.Means;
import it.team8.bw.entities.road.Draft;
import it.team8.bw.enums.MeansStatus;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("tram")
public class Tram extends Means {

    public Tram() {
    }

    public Tram(MeansStatus meansStatus, Draft draft) {
        super(80, meansStatus, draft);
    }

    @Override
    public String toString() {
        return "Tram{" +
                "capacity=" + capacity +
                ", meansStatus=" + meansStatus +
                ", obliterated=" + obliterated +
                ", draft=" + draft +
                ", maintenance=" + maintenance +
                '}';
    }
}
