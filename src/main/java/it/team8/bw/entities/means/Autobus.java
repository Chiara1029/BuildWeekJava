package it.team8.bw.entities.means;

import it.team8.bw.abstractClass.Means;
import it.team8.bw.enums.MeansStatus;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("autobus")
public class Autobus extends Means {

    public Autobus() {
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

    public Autobus(MeansStatus meansStatus) {
        super(50, meansStatus);

    }
}
