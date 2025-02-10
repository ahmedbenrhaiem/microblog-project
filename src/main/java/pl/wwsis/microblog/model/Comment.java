package pl.wwsis.microblog.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "comments") // Mapping to "comments" table
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false) // Foreign key to Post
    private Post post;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false) // Foreign key to User
    private User user;

    @Column(name = "content", nullable = false, length = 500)
    private String content;

    @Column(name = "created_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt = new Date();

    public Comment() {}

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Post getPost() { return post; }
    public void setPost(Post post) { this.post = post; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public Date getCreatedAt() { return createdAt; }
    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }
}