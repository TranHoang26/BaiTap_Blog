package org.example.baitap_blog.service;


import org.example.baitap_blog.model.Blog;

import java.util.List;

public interface BlogService {
    List<Blog> findAll();

    Blog findById(int id);

    void save(Blog obj);

    void update(Blog obj);

    void remove(int id);
}