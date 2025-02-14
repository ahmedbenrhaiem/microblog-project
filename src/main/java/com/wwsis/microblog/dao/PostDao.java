package com.wwsis.microblog.dao;

import com.wwsis.microblog.model.Post;
import java.util.List;

public interface PostDao {
    List<Post> getUserPosts(int userId); // Retrieve all posts for a specific user
    List<Post> getFullTimeline(int userId); // Retrieve all posts from followed users and self
    List<Post> getPublicPosts(); // Retrieve all public posts from all users
    void addPost(Post post); // Add a new post
}
