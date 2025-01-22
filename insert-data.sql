INSERT INTO user (username, email) VALUES ('Jan', 'jan01@test.com');
INSERT INTO user (username, email) VALUES ('Marcin', 'marcin01@test.com');
INSERT INTO user (username, email) VALUES ('Pawel', 'pawel01@test.com');

INSERT INTO follower (user_id, follower_user_id) VALUES (3, 1);
INSERT INTO follower (user_id, follower_user_id) VALUES (3, 2);
INSERT INTO follower (user_id, follower_user_id) VALUES (1, 2);

INSERT INTO post (user_id, content) VALUES (1, 'Welcome');
INSERT INTO post (user_id, content) VALUES (2, 'Test message');
INSERT INTO post (user_id, content) VALUES (3, 'Another test message');
