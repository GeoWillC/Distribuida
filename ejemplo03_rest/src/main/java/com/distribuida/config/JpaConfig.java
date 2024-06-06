package com.distribuida.config;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.jboss.logging.annotations.Producer;

@ApplicationScoped
public class JpaConfig {
    EntityManagerFactory emf;

    @PostConstruct
    public void init(){
        System.out.println("JPA CONFIG: init");
        emf= Persistence.createEntityManagerFactory("distribuida");
    }

    @Produces
    public EntityManager em(){
        System.out.println("JPA CONFIG: em");
        return emf.createEntityManager();
    }
}
