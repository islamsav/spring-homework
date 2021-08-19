package com.example.MyBookShopApp.config;

import com.example.MyBookShopApp.dto.TestEntity;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManagerFactory;

@Configuration
@Slf4j
public class CommandLineRunnerImpl implements CommandLineRunner {

    private final EntityManagerFactory entityManagerFactory;

    @Autowired
    public CommandLineRunnerImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public void run(String... args) throws Exception {

        for (int i = 0; i < 5; i++) {
            createTestEntity(new TestEntity());
        }

        TestEntity readTestEntity = readTestEntityById(3L);
        if (readTestEntity != null) {
            log.info("read: {}", readTestEntity);
        } else {
            throw new NullPointerException();
        }

        TestEntity updateTestEntity = updateTestEntityById(5L);
        if (updateTestEntity != null) {
            log.info("update: {}", updateTestEntity);
        } else {
            throw new NullPointerException();
        }

        deleteTestEntityById(5L);
    }

    private void deleteTestEntityById(long id) {
        Session session = entityManagerFactory.createEntityManager().unwrap(Session.class);
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            TestEntity findEntity = readTestEntityById(id);
            TestEntity mergedTestEntity = (TestEntity) session.merge(findEntity);
            session.remove(mergedTestEntity);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            } else {
                e.printStackTrace();
            }
        } finally {
            session.close();
        }
    }

    private TestEntity updateTestEntityById(long id) {
        Session session = entityManagerFactory.createEntityManager().unwrap(Session.class);
        Transaction tx = null;
        TestEntity result = null;

        try {
            tx = session.beginTransaction();
            TestEntity findEntity = readTestEntityById(id);
            findEntity.setData("NEW DATA UPDATE");
            result = (TestEntity) session.merge(findEntity);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            } else {
                e.printStackTrace();
            }
        } finally {
            session.close();
        }
        return result;
    }

    private TestEntity readTestEntityById(long id) {
        Session session = entityManagerFactory.createEntityManager().unwrap(Session.class);
        Transaction tx = null;
        TestEntity result = null;

        try {
            tx = session.beginTransaction();
            result = session.find(TestEntity.class, id);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            } else {
                e.printStackTrace();
            }
        } finally {
            session.close();
        }
        return result;
    }

    private void createTestEntity(TestEntity entity) {
        Session session = entityManagerFactory.createEntityManager().unwrap(Session.class);
        Transaction ex = null;

        try {
            ex = session.beginTransaction();
            entity.setData(entity.getClass().getSimpleName() + entity.hashCode());
            session.save(entity);
            ex.commit();
        } catch (HibernateException e) {
            if (ex != null) {
                ex.rollback();
            } else {
                e.printStackTrace();
            }
        } finally {
            session.close();
        }
    }
}
