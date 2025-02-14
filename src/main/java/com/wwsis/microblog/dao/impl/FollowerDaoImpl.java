package com.wwsis.microblog.dao.impl;

import com.wwsis.microblog.dao.FollowerDao;
import com.wwsis.microblog.model.Follower;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Transactional
public class FollowerDaoImpl implements FollowerDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void followUser(int followerId, int followeeId) {
        Follower follower = new Follower();
        follower.setUser(followerId);
        follower.setFollower(followeeId);
        entityManager.persist(follower);
    }

    @Override
    public void unfollowUser(int followerId, int followeeId) {
        entityManager.createQuery("DELETE FROM Follower f WHERE f.user.id = :followeeId AND f.follower.id = :followerId")
                .setParameter("followeeId", followeeId)
                .setParameter("followerId", followerId)
                .executeUpdate();
    }

    @Override
    public boolean isFollowing(int followerId, int followeeId) {
        Long count = entityManager.createQuery("SELECT COUNT(f) FROM Follower f WHERE f.user.id = :followeeId AND f.follower.id = :followerId", Long.class)
                .setParameter("followeeId", followeeId)
                .setParameter("followerId", followerId)
                .getSingleResult();
        return count > 0;
    }
}
