package it.team8.bw.entities.means;

import it.team8.bw.abstractClass.Means;
import it.team8.bw.enums.MeansStatus;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("tram")
public class Tram extends Means {

    public Tram() {
    }

    public Tram(MeansStatus meansStatus) {
        super(80, meansStatus);
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
