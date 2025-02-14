package com.wwsis.microblog.dao;

import com.wwsis.microblog.model.Follower;

public interface FollowerDao {
    void followUser(int followerId, int followeeId); // Follow a user
    void unfollowUser(int followerId, int followeeId); // Unfollow a user
    boolean isFollowing(int followerId, int followeeId); // Check if a user is following another
}
