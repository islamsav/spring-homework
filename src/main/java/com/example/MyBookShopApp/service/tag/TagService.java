package com.example.MyBookShopApp.service.tag;

import com.example.MyBookShopApp.entity.other.TagEntity;
import com.example.MyBookShopApp.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService {

    private final TagRepository tagRepository;

    @Autowired
    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public List<TagEntity> tagsByRating() {
        return tagRepository.findAll();
    }
}
