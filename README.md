# HEIG-2017-AMT-Bootcamp

## Brief

HEIG-2017-AMT-Bootcamp is a web application designed to generate and manage a
list of random quotes. You can add to, delete, edit and sort the quotes saved in a
database.

## Deployment

The easiest way to deploy the application is to use [docker-compose](https://docs.docker.com/compose/).
Docker compose will build the different docker container needed for the application to run :

* [Wildfly](http://wildfly.org/) that manage the application.
* [MySQL](https://www.mysql.com/)

### Steps

1. You need to place yourself in the *servlet* folder and execute `docker-compose up --build`
in a terminal.
2. Open a web browser like [Mozilla Firefox](https://www.mozilla.org/en-US/firefox/new/)
3. Access to the different deployed structure :
    * The application : http://{ip docker}:9090/Bootcamp-1.0-SNAPSHOT/
    * Wildfly administration console : http://{ip docker}:9990 , user/password : admin/admin

## Authors

Christopher Meier and Daniel Palumbo for the HEIG-VD ([AMT 2017]( https://github.com/SoftEng-HEIGVD/Teaching-HEIGVD-AMT-2017-Main))

## Credits

Template Boostrap : https://startbootstrap.com/template-overviews/heroic-features/

Countries : https://gist.github.com/keeguon/2310008

Quotes : https://raw.githubusercontent.com/4skinSkywalker/Database-Quotes-JSON/master/quotes.json

Categories : http://www.quoteland.com/topic.asp
