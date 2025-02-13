package pl.wwsis.microblog.model;

import javax.persistence.*;

@Entity
@Table(name = "followers")
public class Follower {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "follower_id", nullable = false)
    private User follower;

    @Column(name = "followed_at", nullable = false)
    private String followedAt;

    public Follower() {}

    public Follower(User user, User follower) {
        this.user = user;
        this.follower = follower;
    }

    // Getters and Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public User getFollower() { return follower; }
    public void setFollower(User follower) { this.follower = follower; }

    public String getFollowedAt() { return followedAt; }
    public void setFollowedAt(String followedAt) { this.followedAt = followedAt; }
}
