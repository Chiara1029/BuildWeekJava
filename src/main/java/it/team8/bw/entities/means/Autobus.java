package it.team8.bw.entities.means;

import it.team8.bw.abstractClass.Means;
import it.team8.bw.entities.road.Draft;
import it.team8.bw.enums.MeansStatus;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("autobus")
public class Autobus extends Means {

    public Autobus() {
    }

    public Autobus(MeansStatus meansStatus, Draft draft) {
        super(50, meansStatus, draft);

    }

    @Override
    public String toString() {
        return "Autobus{" +
                "capacity=" + capacity +
                ", meansStatus=" + meansStatus +
                ", obliterated=" + obliterated +
                ", draft=" + draft +
                ", maintenance=" + maintenance +
                '}';
    }


}
