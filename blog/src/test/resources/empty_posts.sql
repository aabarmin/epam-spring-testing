DROP TABLE IF EXISTS COMMENTS;
DROP TABLE IF EXISTS POSTS;

CREATE TABLE POSTS (
    POST_ID INT NOT NULL,
    POST_TITLE VARCHAR(255),
    POST_CONTENT VARCHAR(4096)
);

CREATE TABLE COMMENTS (
    COMMENT_ID INT NOT NULL,
    COMMENT_AUTHOR VARCHAR(255),
    COMMENT_TEXT VARCHAR(4096),
    POST_ID INT NOT NULL
);