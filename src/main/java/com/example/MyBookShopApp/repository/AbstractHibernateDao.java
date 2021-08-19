package com.example.MyBookShopApp.repository;

import lombok.Setter;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManagerFactory;

@Repository
public abstract class AbstractHibernateDao<T> {

    @Autowired
    private EntityManagerFactory entityManagerFactory;


    @Setter
    private Class<T> clazz;

    public T findOne(Long id) {
        return getSession().find(clazz, id);
    }

    public Session getSession() {
        return entityManagerFactory
                .createEntityManager()
                .unwrap(Session.class);
    }
}
