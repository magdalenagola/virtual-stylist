# Virtual-Stylist

## Table of Contents

- [About](#about)
- [Built-with](#built-with)
- [Installing](#installing)
- [Run](#run)
- [Usage](#usage)
- [Presentation](#presentation)
- [Contact](#contact)

## About <a name = "about"></a>

  An open source project for Codecool.
  As students on the 4th and last learning module in Codecool we are creating an open source web application using tools and knowledge we have gained during the course. Virtual-Stylist is an application which can help you manage your wardrobe, group and store every piece of clothing you have. Moreover, we offer our stylist module which is able to create stylizations for different occasions. You can also use clothes in your wardrobe to create and save your own stylization.

## Built With <a name = "built-with"></a>

* [Spring-boot](https://spring.io/projects/spring-boot)
* [PostgreSQL](https://www.postgresql.org/)
* [Hibernate](https://hibernate.org/)
* [Java 8](https://www.java.com/pl/download/faq/java8.xml)

## Installing <a name = "installing"></a>

Clone our repository using :

```
git clone https://github.com/magdalenagola/virtual-stylist.git
```

Maven properties will be set automatically.

### Important!

To run the application you need to set some data in the application properties file (src/main/resources/application.properties), that is:
- postgres database credentials
- JWT secret key
- Amazon credentials

To run tests there is a need to create application properties file in src/main/resources named "application-test.properties" with data for tests. 

## Run <a name = "run"></a>

To run our application simply type following commands in termial:
```
  mvn package
```

```
  cd target 
```

```
  java -jar virtual-stylist-0.0.1-SNAPSHOT.jar
```

## Usage <a name = "usage"></a>

 http://localhost:8080/swagger-ui.html#/
  
## Contact <a name = "contact"></a>
 * turcza.magdalena@gmail.com
 * szczepan.topolski@gmail.com
##### We invite you to see our presentation <a name = "presentation"></a>
