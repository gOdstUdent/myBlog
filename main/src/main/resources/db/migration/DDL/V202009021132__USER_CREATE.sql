create table blog_user(
id int(11) PRIMARY key auto_increment,
username VARCHAR(30) not null,
passsward VARCHAR(100) not null,
inuse TINYINT(1)
)