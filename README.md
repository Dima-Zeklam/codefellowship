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
 id |  bio  | date_of_birth | first_name | last_name |                           password                           |
username
----+-------+---------------+------------+-----------+--------------------------------------------------------------+---------------
  1 | Java  | 5-8-1997      | dima       | Zeklam    | $2a$10$JoNlzYm1oJ29DEBm6g1xMO4stqajw0glaC3DCcRCi.bPg86clxDTq | Dima-Zeklam
  2 | Java  | 5-8-1997      | dima       | Zeklam    | $2a$10$ca7DaZvBvFaq5DE6P59gguCuAuilDCaENbFR0LVyxSgN.x2xSG7Cm | Dima-Zeklam97
(2 rows)

```

### lab 18:
* users can’t perform SQL injection or HTML injection with their posts by using JPA
* http://localhost:8080/feed this page have that users posts show up in the logged-in user’s feed, where they can see what all of their followed users have posted recently.
* http://localhost:8080/suggest -> user can discover other users on the service when they visit this page and Allow users to follow other users.
* http://localhost:8080/suggest -> On a user profile page that does NOT belong to the currently logged-in user there is “Follow” button, When a user clicks that follow button, the logged-in user is now following the viewed-profile-page user,also Each post have a link to the user profile of the user who wrote the post.


#### The database :
Join Table:
```
users=# SELECT * FROM followed_stream;
 followers_id | following_id
--------------+--------------
            2 |            1
            1 |            2
(2 rows)

```
