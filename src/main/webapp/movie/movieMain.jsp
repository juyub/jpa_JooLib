<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>영화</title>
<script src="https://code.jquery.com/jquery-3.6.4.js"
	integrity="sha256-a9jBBRygX1Bh5lt8GZjXDzyOB+bWve9EiO7tROUtj/E="
	crossorigin="anonymous"></script>
<!-- 부트스트랩 -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ"
	crossorigin="anonymous" />
<!-- 부트스트랩 JS -->
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe"
	crossorigin="anonymous"></script>
<!-- 추가 Slick Carousel CSS -->
<link rel="stylesheet" type="text/css"
	href="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.8.1/slick.min.css" />
<!-- 추가 Slick Carousel theme CSS (선택 사항) -->
<link rel="stylesheet" type="text/css"
	href="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.8.1/slick-theme.min.css" />
<!-- 추가 Slick Carousel JavaScript -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.8.1/slick.min.js"></script>
<!-- <link rel="stylesheet" type="text/css" href="css/main.css"> -->
<style>
div {
	display: flex;
	flex-wrap: wrap;
}

article {
	border: 1px solid black;
	margin: 10px;
	padding: 10px;
	cursor: pointer;
	width: 300px;
}

.center {
	display: flex;
	justify-content: center;
	align-items: center;
	margin-bottom: 30px;
}

.center div {
	margin: 5px;
}

.center img {
	width: 100%;
	max-height: 600px;
	object-fit: contain;
	cursor: pointer;
}

html {
  background: url("image/wall.jpg");
  background-size: 100% 100%;
  background-repeat: no-repeat;
  background-attachment: fixed;
}

header {
  background-color: rgba(240, 248, 255, 0.9);
  margin-top: 1%;
  margin-bottom: 20px;
}

body {
	margin-left: 10%;
	margin-right: 10%;
	background-color: rgba(240, 248, 255, 0.9);
}

a {
  text-decoration: none; /* 밑줄 제거 */
  color: black;
}
</style>
<script>
    function nextPage(id) {
        window.location.href = "movieDetail?id=" + id;
    }

    function moviePost() {
        $.ajax({
            url: 'https://api.themoviedb.org/3/movie/upcoming',
            data: {
                api_key: 'a64533e7ece6c72731da47c9c8bc691f',
                language: 'ko-KR',
                page: 1
            },
            success: function (data) {
                const { results } = data;

                for (let i = 0; i < results.length; i++) {
                    const post = results[i];

                    $(".center").append(
                        '<div>' +
                            '<img src="https://image.tmdb.org/t/p/w500' + post.poster_path + '" alt="' + post.title + '" onclick="nextPage(' + post.id + ')">' +
                            '<div class="text-center">' +
                                '<h5>' + post.title + '</h5>' +
                                '<p>' + post.release_date + '</p>' +
                            '</div>' +
                        '</div>'
                    );

                    if (i % 4 === 0) {
                        $("#container2").append(`<div class="row">`);
                    }
                    $("#container2 .row:last-child").append(`
                            <div class="col-md-3">
                                <div class="card mb-3" onclick="nextPage(` + post.id + `)">
                                    <img src="https://image.tmdb.org/t/p/w500` + post.poster_path + `" class="card-img-top" alt="` + post.title + `">
                                    <div class="card-body">
                                        <h5 class="card-title">` + post.title + `</h5>
                                        <p class="card-text">` + post.release_date + `</p>
                                    </div>
                                </div>
                            </div>
                        `);
                    if (i % 4 === 3 || i === results.length - 1) {
                        $("#container2").append(`</div>`);
                    }
                }

                // Slick 설정
                $('.center').slick({
                    centerMode: true,
                    centerPadding: '60px',
                    slidesToShow: 3,
                    responsive: [
                        {
                            breakpoint: 768,
                            settings: {
                                arrows: false,
                                centerMode: true,
                                centerPadding: '40px',
                                slidesToShow: 3
                            }
                        },
                        {
                            breakpoint: 480,
                            settings: {
                                arrows: false,
                                centerMode: true,
                                centerPadding: '40px',
                                slidesToShow: 1
                            }
                        }
                    ]
                });
            },
            error: function (err) {
                console.error('영화 데이터를 가져오는 데 실패했습니다.', err);
            }
        });
    }
</script>
</head>

<body onload="moviePost()">
	<header>
		<jsp:include page="../main/topMenu.jsp" />
	</header>

	<div class="center"></div>

	<div id="container2" class="container"></div>
</body>
</html>
