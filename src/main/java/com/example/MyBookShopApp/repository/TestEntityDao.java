package com.example.MyBookShopApp.repository;

import com.example.MyBookShopApp.dto.TestEntity;
import org.springframework.stereotype.Repository;


@Repository
public class TestEntityDao extends AbstractHibernateDao<TestEntity> {


    public TestEntityDao() {
        super();
        setClazz(TestEntity.class);
    }
}
