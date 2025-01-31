INSERT INTO user (username, email, created_at) VALUES 
('john_doe', 'john@example.com', NOW()),
('jane_smith', 'jane@example.com', NOW()),
('alice_brown', 'alice@example.com', NOW());

INSERT INTO follower (user_id, follower_id, followed_at) VALUES 
(1, 2, NOW()),  
(2, 3, NOW()),  
(3, 1, NOW());  

INSERT INTO post (user_id, content, created_at) VALUES 
(1, 'Hello, this is my first microblog post!', NOW()),
(2, 'Excited to be part of this platform!', NOW()),
(3, 'Testing the new database schema.', NOW());
