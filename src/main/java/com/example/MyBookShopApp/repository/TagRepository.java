package com.example.MyBookShopApp.repository;

import com.example.MyBookShopApp.entity.other.TagEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<TagEntity, Integer> {
}
