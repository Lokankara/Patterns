<%--
  Created by IntelliJ IDEA.
  User: Pasha
  Date: 7/8/2023
  Time: 6:22 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="patterns.refactoring.model.entity.Movie" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Movie Catalog</title>
    <style>
        <%@include file="../classes/templates/css/index.css"%>
    </style>
</head>
<body>
<h1>Movie Catalog</h1>

<table>
    <caption></caption>
    <tr>
        <th>Poster</th>
        <th>Title</th>
        <th>Genre</th>
        <th>Country</th>
        <th>Short Description</th>
        <th>Director</th>
        <th>Actors</th>
        <th>Rating</th>
    </tr>
    <% List<Movie> movies = (List<Movie>) request.getAttribute("movies");
        if (movies != null) {
            for (Movie movie : movies) { %>
    <tr onclick="openMovieDetails('<%= movie.getTitle() %>')">
        <td onclick="openMovie('<%= movie.getTitle() %>')">
            <img src="https://image.tmdb.org/t/p/w500/<%=
        movie.getReview().getBackdropPath() %>" alt="Movie"></td>
        <td><strong><%= movie.getTitle() %></strong><br></td>
        <td><%= movie.getGenre() %><br></td>
        <td><%= movie.getReview().getCountry() %><br></td>
        <td><%= movie.getReview().getOverview() %><br></td>
        <td><%= movie.getReview().getDirector() %><br></td>
        <td><%= movie.getReview().getActor() %><br></td>
        <td><%= movie.getReview().getRating() %><br></td>
    </tr>
    <% }
    } %>
</table>

<script>
    function openMovie(title) {
        window.location.href = "/api/api.jsp?title=" +
            encodeURIComponent(title);
    }

    function openMovieDetails(movie) {
        const API_KEY = "249f222afb1002186f4d88b2b5418b55";
        const API_SEARCH = "https://api.themoviedb.org/3/search/movie?api_key="
            + API_KEY + "&language=en-US&query=";
        const searchURL = API_SEARCH + encodeURIComponent(movie);
        window.open(searchURL, "_blank");
    }
</script>
</body>
</html>
