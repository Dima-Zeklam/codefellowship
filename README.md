# codefellowship
* The site should have home page with sign up link so that visitor can register to the app.
* Each user has a username, password (will be hashed using BCrypt), firstName, lastName, dateOfBirth, bio
* The app encode the password of new user before saving it using Bcrypt and PasswordEncoder.
* Each user has a profile page with that information mentioned above
* http://localhost:8080/ this route will navigate to home page linked to sign up page and login page if you already have an account
* http://localhost:8080/signup page to create an account by filling the form when click signup will take you to login page 
* http://localhost:8080/login have to write username and password to go to profile page when you get authentication
* http://localhost:8080/profile the profile page that contain user info.
* http://localhost:8080/users/1 will go to the user profile that have id =1.
* the username showed in all pages like profile and home page when he lodged in.
* I add Post entity that has a body and a createdAt timestamp
* A logged-in user can add a Post that belong to him in profile page.
* Also each page have a navbar.

#### application.properties
```
server.port=8080
spring.sql.init.platform=postgres
spring.datasource.url=jdbc:postgresql://localhost:5432/users
spring.datasource.username=username
spring.datasource.password=password
spring.jpa.hibernate.ddl-auto=create
spring.sql.init.mode=always
```

#### The database :
CREATE DATABASE users

```
users=# SELECT * FROM application_user;

id |  bio  | date_of_birth | first_name | last_name |                           password                           |  username
----+-------+---------------+------------+-----------+--------------------------------------------------------------+-------------
1 | Java  | 5-8-1997      | dima       | Zeklam    | $2a$10$mKYP.e5rznOxNRp2j8R6vu.TJwJQygLRe5LNptz3xdjxKjH3NklEC | Dima-Zeklam
(1 row)

users=#  SELECT * FROM post;
 id |        body        | created_at | app_user_id
----+--------------------+------------+-------------
  1 | i can add a post  | 2021-11-01 |           1
(1 row)

```
