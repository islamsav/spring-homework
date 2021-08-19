package com.example.MyBookShopApp.repository;

import com.example.MyBookShopApp.dto.TestEntity;
import org.springframework.data.repository.CrudRepository;

public interface TestEntityCrudRepository extends CrudRepository<TestEntity, Long> {
}
