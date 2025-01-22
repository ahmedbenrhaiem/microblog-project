drop schema public cascade;
drop table user if exists;
create table user (
    user_id SERIAL PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    email VARCHAR(50) NOT NULL UNIQUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

drop table follower if exists;
create table follower (
    follower_id SERIAL PRIMARY KEY,
    user_id INT NOT NULL,
    follower_user_id INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user(user_id) on delete cascade,
    FOREIGN KEY (follower_user_id) REFERENCES user(user_id) on delete cascade
);

drop table post if exists;
create table post (
    post_id SERIAL PRIMARY KEY,
    user_id INT NOT NULL,
    content TEXT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES user(user_id) on delete cascade
);
