package at.htl.business;

import at.htl.model.*;
import io.quarkus.runtime.StartupEvent;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.time.LocalDate;

@ApplicationScoped
public class InitBean {

    @Inject
    EntityManager em;

    @Transactional
    public void init(@Observes StartupEvent ev) {
        System.err.println("******** hello");
        MotorcycleType ktm = new MotorcycleType("KTM", "1290 Superduke", LocalDate.of(2017,4,15));
        Engine ktmEngine = new Engine(2,1290);
        Transmission ktmTransmission = new Transmission(6);
        Motorcycle ktmMotorcycle = new Motorcycle("Orange", 160, ktm,ktmEngine,ktmTransmission);
        addMotorcycle(ktmMotorcycle);
        MotorcycleType honda = new MotorcycleType("Honda","CBR 1000",LocalDate.of(2016,5,3));
        Engine hondaEngine = new Engine(4,1000);
        Transmission hondaTransmission = new Transmission(5);
        Motorcycle hondaMotorcycle = new Motorcycle("Red",130,honda,hondaEngine,hondaTransmission);
        addMotorcycle(hondaMotorcycle);
        MotorcycleType yamaha = new MotorcycleType("Yamaha", "R1",LocalDate.of(2018,10,24));
        Engine yamahaEngine = new Engine(2,1200);
        Transmission yamahaTransmission = new Transmission(6);
        Motorcycle yamahaMotorcycle = new Motorcycle("Blue",140, yamaha,yamahaEngine,yamahaTransmission);
        addMotorcycle(yamahaMotorcycle);
        Customer hans = new Customer("Hans","Huber");
        em.persist(hans);
        Purchase purchase = new Purchase(hans,ktmMotorcycle);
        em.persist(purchase);
    }

    public void addMotorcycle(Motorcycle motorcycle) {
        if(em == null) {
            System.out.println(motorcycle);
        }
        em.persist(motorcycle);
    }
}
