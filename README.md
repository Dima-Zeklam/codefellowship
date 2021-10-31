# codefellowship

* http://localhost:8080/ this route will navigate to home page linked to sign up page and login page if you already have an account
* http://localhost:8080/signup page to create an account by filling the form when click signup will take you to login page 
* http://localhost:8080/login have to write username and password to go to profile page when you get authentication
* http://localhost:8080/profile the profile page that contain user info.
* http://localhost:8080/users/1 will go to the user profile that have id =1.

The database :
```
users=# SELECT * FROM application_user;

id |  bio  | date_of_birth | first_name | last_name |                           password                           |  username
----+-------+---------------+------------+-----------+--------------------------------------------------------------+-------------
1 | Java  | 5-8-1997      | dima       | Zeklam    | $2a$10$mKYP.e5rznOxNRp2j8R6vu.TJwJQygLRe5LNptz3xdjxKjH3NklEC | Dima-Zeklam
(1 row)
```