package com.distribuida.servicios;

import com.distribuida.db.Persona;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

import java.util.List;

@ApplicationScoped
public class ServicioPersonaImpl implements ServicioPersona {

    @Inject
    EntityManager em;

    @Override
    public void crear(Persona persona) {
        em.persist(persona);
    }

    @Override
    public Persona findbyId(Integer id) {
        return em.find(Persona.class, id);
    }

    @Override
    public List<Persona> allFind() {
        return em.createQuery("select p from Persona p order by id asc", Persona.class).getResultList();
    }

    @Override
    public void actualizar(Integer id) {
        Persona pupdate= findbyId(id);
        em.merge(pupdate);
    }

    @Override
    public void eliminar(Integer id) {
        em.getTransaction().begin();
        em.remove(findbyId(id));
        em.getTransaction().commit();

    }
}
