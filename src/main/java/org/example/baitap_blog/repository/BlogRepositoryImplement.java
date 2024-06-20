package org.example.baitap_blog.repository;


import org.example.baitap_blog.model.Blog;
import org.springframework.stereotype.Repository;


import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;
@Repository
@Transactional
public class BlogRepositoryImplement implements BlogRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Blog> findAll() {
        TypedQuery<Blog> query = entityManager.createQuery("SELECT obj from Blog obj", Blog.class);
        return query.getResultList();
    }

    @Override
    public Blog findById(int id) {
        TypedQuery<Blog> query = entityManager.createQuery("SELECT obj from Blog obj where obj.id=:id", Blog.class);
        query.setParameter("id", id);
        try {
            return query.getSingleResult();
        } catch (NoResultException e){
            return null;
        }
    }

    @Override
    public void save(Blog obj) {
        if(obj.getId() != 0){
            entityManager.merge(obj);
        } else {
            entityManager.persist(obj);
        }
    }

    @Override
    public void update(Blog obj) {
        entityManager.merge(obj);
    }

    @Override
    public void remove(int id) {
        Blog obj = findById(id);
        if (obj != null) {
            entityManager.remove(obj);
        }
    }
}