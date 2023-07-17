<%--
  Created by IntelliJ IDEA.
  User: Pasha
  Date: 7/8/2023
  Time: 6:22 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>MovieLand</title>
    <link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.5.0/css/bootstrap.min.css'>
    <style><%@include file="../classes/templates/css/catalog.css"%></style>
</head>
<body>

<nav class="fixed-top d-flex justify-content-center navbar p-2">
    <a href="${pageContext.request.contextPath}/movie" class="p-0 navbar-brand d-none d-lg-block">Movie
        Rental</a>
    <form id="search-form" class="form-inline" action="search.jsp" method="GET">
        <input id="search" class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search" name="title" value="${title}">
        <button type="submit">Submit</button>
    </form>
    <header>
        <ul class="filter">
            <li data-gid="" class="selected">All</li>
            <li data-gid="28">Action</li>
            <li data-gid="12">Adventures</li>
            <li data-gid="16">Animation</li>
            <li data-gid="35">Comedy</li>
            <li data-gid="80">Crime</li>
            <li data-gid="18">Drama</li>
            <li data-gid="14">Fantasy</li>
            <li data-gid="27">Horror</li>
            <li data-gid="9648">Mystery</li>
            <li data-gid="878">Sci-Fi</li>
            <li data-gid="53">Thriller</li>
        </ul>
    </header>
</nav>
<section>
    <div class="container-fluid mt-5 p-5">
        <div id="movie-content" class="row d-flex justify-content-center">
        </div>
    </div>
</section>
<nav aria-label="Page navigation example">
    <ul class="pagination justify-content-center">
        <li class="page-item">
            <a id="previous" class="btn page-link" tabindex="-1">Previous</a>
        </li>
        <li class="page-item">
            <a id="next" class="btn page-link">Next</a>
        </li>
    </ul>
</nav>

<script>
    const form = document.getElementById("search-form");

    const API_KEY = "249f222afb1002186f4d88b2b5418b55";
    const API_SEARCH = "https://api.themoviedb.org/3/search/movie?api_key=" + API_KEY + "&language=en-US&query=";
    const IMAGE_PATH = "https://image.tmdb.org/t/p/w500";
    let page = "1";
    const API_URL = "https://api.themoviedb.org/3/discover/movie?api_key=" + API_KEY + "&language=en-US&sort_by=popularity.desc&include_video=true&page=";
    const mainContent = document.getElementById("movie-content");
    const search = document.getElementById("search");
    const pageLinks = document.querySelectorAll(".page-link");

    getMovies(API_URL + page, "");

    pageLinks.forEach((pageLink) => {
        pageLink.addEventListener("click", () => {
            if (pageLink.id === "next") {
                page++;
                getMovies(API_URL + page, "");
            }
            if (pageLink.id === "previous" && page > 1) {
                page--;
                getMovies(API_URL + page, "");
            }
        });
    });

    form.addEventListener("submit", (e) => {
        e.preventDefault();
        const query = search.value;
        if (query) {
            getMovies(API_SEARCH + query ,"");
        }
    });

    async function getMovies(url , id) {
        const resp = await fetch(url);
        const respData = await resp.json();
        if (filter === "") {
            showMovies(respData);
        } else{
            showMovies(filter(respData, id));
        }
    }

    function filter(respData, filter) {
        return respData.results.filter((movie) => {
            const genreIds = movie.genre_ids;
            return filter === "" || genreIds.includes(parseInt(filter));
        });
    }

    function showMovies(movies) {
        console.log(movies)
        mainContent.innerHTML = "";
        movies.forEach((movie) => {
            const movieTitle = movie.title;
            const moviePoster = movie.poster_path;
            const movieVote = movie.vote_average;

            if (moviePoster) {
                const movieElm = document.createElement("div");
                movieElm.classList.add("col-xs-12", "col-sm-6", "col-md-4", "col-lg-3", "p-0");
                movieElm.innerHTML = "<div class='movie-card selected'>" +
                    "<div class='movie-image loaded up'>" +
                    "<img class='img-fluid movie-img' src='" + IMAGE_PATH + moviePoster + "'" +
                    " alt='poster'>" +
                    "</div>" +
                    "<div class='movie-description p-3 d-flex justify-content-between align-items-center'>" +
                    "<h3 class='movie-title'>" + movieTitle + "</h3>" +
                    "<h3 class='movie-vote'>" + movieVote + "</h3>" +
                    "</div></div>";
                mainContent.appendChild(movieElm);
            }
        });
    }
    const filterItems = document.querySelector('.filter');
    filterItems.addEventListener('click', (event) => {
        const target = event.target;
        if (target.tagName === 'LI') {
            const filterValue = target.getAttribute('data-gid');
            console.log(filterValue);
            filterItems.querySelectorAll('li').forEach((item) => {
                item.classList.remove('selected');
            });
            target.classList.add('selected');

            getMovies(API_URL + page, filterValue);
        }
    });
</script>
</body>
</html>
