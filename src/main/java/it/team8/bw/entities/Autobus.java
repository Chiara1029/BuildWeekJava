package it.team8.bw.entities;

import it.team8.bw.abstractClass.Means;
import it.team8.bw.enums.MeansStatus;

import javax.persistence.*;
import java.util.Set;

@Entity
@DiscriminatorValue("autobus")
public class Autobus extends Means {

    public Autobus(){}

    public Autobus(MeansStatus meansStatus, Draft draft, Set<Maintenance> maintenance) {
        super(50, meansStatus, draft, maintenance);
    }

    @Override
    public String toString() {
        return "Autobus{" +
                "id=" + super.getId()+
                ", capacity=" + capacity +
                ", meansStatus=" + meansStatus +
                ", obliterated=" + obliterated +
                ", draft=" + draft +
                ", maintenance=" + maintenance +
                '}';
    }
}
