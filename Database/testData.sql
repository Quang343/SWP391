select * from blog ;
SELECT * FROM blogdetail;
SELECT * FROM blogcategory;
SELECT * FROM blog WHERE status = 'ACTIVE';

UPDATE blog
SET status = 'ACTIVE'
WHERE status = 'active';

SELECT b.blogid, b.title, b.author, bd.thumbnail, bd.detailcontent
FROM blog b
LEFT JOIN blogdetail bd ON b.blogid = bd.blogid;

select * from user ;

SET SQL_SAFE_UPDATES = 0;
UPDATE blog b
JOIN user u ON b.userid = u.userid
SET b.author = u.fullname;
SET SQL_SAFE_UPDATES = 1;

-- Kiểm tra lại
SELECT b.blogid, b.title, u.fullname, b.author
FROM blog b
JOIN user u ON b.userid = u.userid;
