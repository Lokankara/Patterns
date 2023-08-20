[![Netlify Status](https://api.netlify.com/api/v1/badges/abc5df6e-c203-48a9-90e3-cce20dae76cb/deploy-status)](https://app.netlify.com/sites/hotel-room-slider/deploys)

[![Java CI with Maven](https://github.com/Lokankara/Patterns/actions/workflows/maven.yml/badge.svg)](https://github.com/Lokankara/Patterns/actions/workflows/maven.yml)

https://hotel-room-slider.netlify.app/


Task:

Firstly, the client wants the output in HTML as well.
Second, the client wants to add other types of movies: dramas, comedies, thrillers.
Third, we want to add new characteristics to the movies
(country of origin, short description, director, actors).

These edits are difficult to fit into the current structure, so we will have to change it.

Create the ability to account for multiple clients, work with text(!?) files
for data storage

The program should provide the ability to: 

- view the catalog of all movies,
- adding a movie,
- search by certain characteristics
- etc.
  Create a convenient menu for accessing functions

`mvn tomcat7:run -X`

`mvn -B package --file pom.xml`

`mvn dependency:tree`

