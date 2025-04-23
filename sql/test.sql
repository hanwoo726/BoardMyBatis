CREATE TABLE board
(
    id      INT          NOT NULL,
    name    VARCHAR(20)  NOT NULL,
    subject VARCHAR(30)  NOT NULL,
    content VARCHAR(100) NOT NULL,
    view    INT          NOT NULL DEFAULT 0,
    regdate DATETIME     NOT NULL,
    PRIMARY KEY (id)
);

SELECT * FROM board;


# 보드의 id값 자동 증가하게 하기
ALTER TABLE board
    MODIFY COLUMN id INT AUTO_INCREMENT;

INSERT INTO board (id, name, subject, content, regdate) VALUES
                    (1, '테스트 작성자', '첫 번째 게시글', '내용내용', now());

GRANT ALL PRIVILEGES ON *.* TO 'root'@'localhost' IDENTIFIED BY 'gksdn1631!';
FLUSH PRIVILEGES;

CREATE TABLE comment
(
    commentId INT          NOT NULL,
    comment   VARCHAR(100) NOT NULL,
    id        INT          NOT NULL COMMENT 'boardId',
    PRIMARY KEY (commentId)
);

ALTER TABLE comment
    ADD CONSTRAINT FK_board_TO_comment
        FOREIGN KEY (id)
            REFERENCES board (id);

SELECT * FROM board
ORDER BY id DESC;

select b.id, c.comment
from board b
         join comment c on b.id = c.id;

insert into comment (commentId, comment, id) VALUES (2,'반갑습니다22', 11);

select b.id, c.comment
from board b
         join comment c on b.id = c.id
where b.id = 12;

alter table comment ADD
    regdate DATETIME;

select * from comment;

insert into comment (comment, regdate,id)
select c.comment, NOW()
from board b
         join comment c ON b.id = c.id;

ALTER TABLE comment
    MODIFY COLUMN commentId INT AUTO_INCREMENT;

update comment
set regdate = now()
where id = 11;



CREATE TABLE user
(
    id       INT         NOT NULL,
    userID   VARCHAR(20) NOT NULL,
    password VARCHAR(20) NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE comment
    ADD CONSTRAINT userId
        FOREIGN KEY (userId)
            REFERENCES user (id);

ALTER TABLE board
    ADD CONSTRAINT user_id
        FOREIGN KEY (userId)
            REFERENCES user (id);

ALTER TABLE board DROP FOREIGN KEY user_id;

SHOW CREATE TABLE comment;

select * from board;

select * from comment;

select u.id, u.userID from user u
JOIN board b
where b.userId = u.id;


select * from comment;

select b.id, c.comment, c.regdate, u.id
from board b
         join comment c on b.id = c.id
         join user u on c.userId = u.id;

UPDATE comment SET userId = 2
WHERE commentId = 2;

select * from comment;



select * from board;

select * from user;

select * from comment;

select b.id,c.userId, c.comment, c.regdate
from board b
         join comment c on b.id = c.id
         join user u on c.userId = u.id AND
                        b.userId = u.id;


select * from comment;

SELECT c.commentId,
       c.comment,
       c.regdate,
       u.userID
FROM comment c
         JOIN user u ON u.id = c.userId;

select * from comment;


SELECT c.id,
       c.comment,
       c.regdate,
       u.userID
FROM comment c
         JOIN user u ON c.userId = u.id
WHERE c.id =11;

select * from board;
select * from user;
select * from comment;

select b.id AS board_id, b.subject, b.content, b.regdate, b.view,
       u.userID AS user_id,
       c.commentId AS comment_id ,c.comment, c.regdate AS comment_regdate
from board b
         JOIN user u ON b.userId = u.id
         LEFT JOIN comment c ON b.id = c.id
where b.id;

select * from board;

select id, comment, comment.regdate
from comment
where id = 11;


select b.id AS board_id,b.name , b.subject, b.content, b.regdate, b.view
from board b
where b.id;

select u.id, u.userID, c.comment from user u
JOIN comment c ON u.id = c.userId
where u.id = 1;

select * from comment;

select * from user;

select userID, password,id
from user
where userID = 'test1' AND password = '1234';

SELECT c.commentId,
       c.id AS cid,
       c.comment,
       c.regdate,
       c.userId,
       u.id AS uid,
       u.userID,
       b.id AS boardId,
       b.subject
FROM comment c
         JOIN user u ON u.id = c.userId
         JOIN board b ON b.id = c.id
WHERE u.id = 1;

select * from user;

INSERT INTO user (id, userID, password) VALUES (0, 'GUEST', '0000');





INSERT INTO user (id, userID, password) VALUES (3, '테스트3', '1234');