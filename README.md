# codefellowship
* The site have home page at the root route (http://localhost:8080/) with sign up link so that visitor can register to the app.
* Each user have a username, password (will be hashed using BCrypt), firstName, lastName, dateOfBirth, bio.
* * http://localhost:8080/signup page to create an account by filling the form when click signup will take you to login page
* The app encode the password of new user before saving it using Bcrypt and PasswordEncoder.
* http://localhost:8080/users/{id} Each user has a profile page with its information and include a default profile picture.
* http://localhost:8080/users/1 will go to the user profile that have id =1.
* http://localhost:8080/login have to write username and password ( the ability for users to log in to my app.)
* when the user logging in the username showed in all pages in the head.
* http://localhost:8080/profile the profile page that contain user info.
* homepage, login, and registration routes are accessible to non-logged in users. All other routes such as  http://localhost:8080/profile limited to logged-in users.
* I used card as style in profile and I added some CSS on the pages.
* the app have one Thymeleaf fragment that is used on multiple pages as navbar.
* The site non-whitelabel error handling page that lets the user know, by said that page not found and a link that will navigate him to home page.
* user registration also logs users into my app automatically.
* I add Post entity that has a body and a createdAt timestamp
* A logged-in user can add a Post that belong to him and visible on their profile page.


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
