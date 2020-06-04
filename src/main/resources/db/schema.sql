CREATE TABLE Blogs
(
    post_id  INT UNSIGNED           NOT NULL AUTO_INCREMENT,
    title       VARCHAR(255)        NOT NULL,
    description VARCHAR(1000)        NOT NULL,
    author      VARCHAR(255)        NOT NULL,
    PRIMARY KEY (post_id)
);

CREATE TABLE Users
(
    user_id INT UNSIGNED           NOT NULL AUTO_INCREMENT,
    username    VARCHAR(255)        NOT NULL,
    password VARCHAR(255)            NOT NULL,
    PRIMARY KEY (user_id)
);