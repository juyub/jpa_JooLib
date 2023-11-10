<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>영화정보</title>
    <script
      src="https://code.jquery.com/jquery-3.6.4.js"
      integrity="sha256-a9jBBRygX1Bh5lt8GZjXDzyOB+bWve9EiO7tROUtj/E="
      crossorigin="anonymous"
    ></script>
<link rel="stylesheet" type="text/css" href="css/main.css">
    <style>
      .movie-info-container {
        border: 1px solid black;
        margin-bottom: 10px;
        padding: 10px;
        display: flex;
        flex-wrap: wrap;
        background-color : white;
        margin-left: 10%;
  		margin-right: 10%;
      }
      .movie-info-container h1 {
        font-size: 24px;
        margin: 0 0 10px;
        width: 100%;
      }
      .movie-poster {
        max-width: 40%;
        margin-right: 20px;
      }
      .movie-info {
        flex-grow: 1;
        display: flex;
        flex-direction: column;
        justify-content: space-between;
      }
      .movie-info ul {
        list-style: none;
        padding: 0;
        margin: 0;
        flex-grow: 1;
        display: flex;
        flex-direction: column;
        justify-content: space-around;
      }
      .movie-info li {
        margin-bottom: 5px;
      }
      .movie-info .label {
        font-weight: bold;
      }
      .movie-info .overview {
        flex-grow: 1;
      }
    </style>
	
	<script>
	  async function movieInfo() {
	    const params = new URLSearchParams(window.location.search);
	    const movieId = params.get("id");
	
	    const response = await fetch(
	      "https://api.themoviedb.org/3/movie/" + movieId + "?api_key=a64533e7ece6c72731da47c9c8bc691f&language=ko-KR"
	    );
	    const json = await response.json();
	
	    // 영화 정보를 출력합니다.
	    const imageURL = "https://image.tmdb.org/t/p/w500" + json.poster_path;
	    const movieInfoHTML = 
	        '<div class="movie-info-container">' +
	          '<h1>' + json.title + '</h1>' +
	          '<div class="movie-poster-container">' +
	            '<img class="movie-poster" src="' + imageURL + '" alt="' + json.title + '">' +
	          '</div>' +
	          '<div class="movie-info">' +
	            '<ul>' +
	              '<li>' +
	                '<span class="label">인기도:</span>' +
	                '<span>' + json.popularity + '</span>' +
	              '</li>' +
	              '<li>' +
	                '<span class="label">평점:</span>' +
	                '<span>' + json.vote_average + '</span>' +
	              '</li>' +
	              '<li>' +
	                '<span class="label">평점 개수:</span>' +
	                '<span>' + json.vote_count + '</span>' +
	              '</li>' +
	            '</ul>' +
	            '<p class="overview">' + json.overview + '</p>' +
	          '</div>' +
	        '</div>';
	
	    $("body").append(movieInfoHTML);
	  }
	</script>

  </head>
  <body onload="movieInfo()">
  		<header>
		<jsp:include page="../main/topMenu.jsp" />
	</header>
  
  </body>
</html>
