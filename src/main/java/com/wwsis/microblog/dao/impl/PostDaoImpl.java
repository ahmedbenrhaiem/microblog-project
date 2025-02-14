package com.wwsis.microblog.dao.impl;

import com.wwsis.microblog.dao.PostDao;
import com.wwsis.microblog.model.Post;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class PostDaoImpl implements PostDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Post> getUserPosts(int userId) {
        return entityManager.createQuery("SELECT p FROM Post p WHERE p.user.id = :userId", Post.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    @Override
    public List<Post> getFullTimeline(int userId) {
        return entityManager.createQuery("SELECT p FROM Post p WHERE p.user.id = :userId OR p.user.id IN "
                + "(SELECT f.followee.id FROM Follower f WHERE f.follower.id = :userId)", Post.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    @Override
    public List<Post> getPublicPosts() {
        return entityManager.createQuery("SELECT p FROM Post p", Post.class).getResultList();
    }

    @Override
    public void addPost(Post post) {
        entityManager.persist(post);
    }
}
