package at.htl.model;

import javax.persistence.*;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(name = "Motorcycle.findAll", query = "select m from Motorcycle m"),
})
public class Motorcycle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String colour;
    private int hp;
    @OneToOne(cascade = CascadeType.ALL)
    private MotorcycleType motorcycleType;
    @OneToOne(cascade = CascadeType.ALL)
    private Engine engine;
    @OneToOne(cascade = CascadeType.ALL)
    private Transmission transmission;
    @OneToMany
    private List<Purchase> purchases;


    public Motorcycle() {
    }

    public Motorcycle(String colour, int hp, MotorcycleType motorcycleType, Engine engine, Transmission transmission) {
        this.colour = colour;
        this.hp = hp;
        this.motorcycleType = motorcycleType;
        this.engine = engine;
        this.transmission = transmission;
    }

    public Long getId() {
        return id;
    }

    public MotorcycleType getMotorcycleType() {
        return motorcycleType;
    }

    public void setMotorcycleType(MotorcycleType motorcycleType) {
        this.motorcycleType = motorcycleType;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public Transmission getTransmission() {
        return transmission;
    }

    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
    }
}
