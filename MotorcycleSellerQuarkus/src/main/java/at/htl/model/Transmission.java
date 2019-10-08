package at.htl.model;

import javax.persistence.*;

@Entity
@NamedQueries({
        @NamedQuery(name = "Transmission.findAll", query = "select t from Transmission t"),
})
public class Transmission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int gears;

    public Transmission() {
    }

    public Transmission(int gears) {
        this.gears = gears;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getGears() {
        return gears;
    }

    public void setGears(int gears) {
        this.gears = gears;
    }
}
