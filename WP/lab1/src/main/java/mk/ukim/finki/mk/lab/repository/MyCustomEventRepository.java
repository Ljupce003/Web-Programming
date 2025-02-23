package mk.ukim.finki.mk.lab.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import mk.ukim.finki.mk.lab.model.Event;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class MyCustomEventRepository {

    @PersistenceContext
    EntityManager em;

    @Transactional
    public Event findById(int id){
        return em.find(Event.class,id);
    }
    @Transactional
    public Event create(Event event){
        em.persist(event);
        return event;
    }
    @Transactional
    public Event update(Event event){
        em.merge(event);
        return event;
    }
    @Transactional
    public void delete(int id){
        Event address = em.find(Event.class,id);
        em.remove(address);
    }
    @Transactional
    public List<Event> findAll(){
        TypedQuery<Event> query = em.createQuery("select a from Event a", Event.class);
        return query.getResultList();
    }
    @Transactional
    public List<Event> findByName(String name){
        TypedQuery<Event> query = em.createQuery("select a from Event a where a.name = :name", Event.class);
        query.setParameter("name", name);
        return query.getResultList();

    }
}
