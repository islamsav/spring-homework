package com.example.MyBookShopApp.config;

import com.example.MyBookShopApp.dto.TestEntity;
import com.example.MyBookShopApp.repository.TestEntityCrudRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;


@Configuration
@Slf4j
public class CommandLineRunnerImpl implements CommandLineRunner {

    private final TestEntityCrudRepository testEntityCrudRepository;

    @Autowired
    public CommandLineRunnerImpl(TestEntityCrudRepository testEntityCrudRepository) {
        this.testEntityCrudRepository = testEntityCrudRepository;
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

        TestEntity updateTestEntity = updateTestEntityById(3L);
        if (updateTestEntity != null) {
            log.info("update: {}", updateTestEntity);
        } else {
            throw new NullPointerException();
        }

        deleteTestEntityById(5L);
    }

    private void deleteTestEntityById(long id) {
        TestEntity testEntity = testEntityCrudRepository.findById(id).get();
        testEntityCrudRepository.delete(testEntity);
    }

    private TestEntity updateTestEntityById(long id) {
        TestEntity testEntity = testEntityCrudRepository.findById(id).get();
        testEntity.setData("NEW DATA");
        testEntityCrudRepository.save(testEntity);
        return testEntity;
    }

    private TestEntity readTestEntityById(long id) {
        return testEntityCrudRepository.findById(id).get();
    }

    private void createTestEntity(TestEntity entity) {
        entity.setData(entity.getClass().getSimpleName() + entity.hashCode());
        testEntityCrudRepository.save(entity);
    }
}
