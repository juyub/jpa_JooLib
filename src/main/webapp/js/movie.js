function moviePost() { $.ajax({ url: 'https://api.themoviedb.org/3/movie/upcoming', data: { api_key: 'a64533e7ece6c72731da47c9c8bc691f', language: 'ko-KR', page: 1 }, success: function(data) { const { results } = data;
  for (let i = 0; i < results.length; i++) {
    const post = results[i];
    const title = post.title;
    const releaseDate = post.release_date;
    const posterPath = post.poster_path;
    const imgUrl = `https://image.tmdb.org/t/p/w500${posterPath}`;

    if (i === 0) {
      $('.carousel-indicators').append(`<button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="${i}" class="active" aria-current="true"></button>`);

      $('.carousel-inner').append(`<div class="carousel-item active">
        <img src="${imgUrl}" class="d-block w-100" alt="${title}">
        <div class="carousel-caption d-none d-md-block">
          <h5>${title}</h5>
          <p>개봉일: ${releaseDate}</p>
        </div>
      </div>`);
    } else {
      $('.carousel-indicators').append(`<button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="${i}"></button>`);

      $('.carousel-inner').append(`<div class="carousel-item">
        <img src="${imgUrl}" class="d-block w-100" alt="${title}">
        <div class="carousel-caption d-none d-md-block">
          <h5>${title}</h5>
          <p>개봉일: ${releaseDate}</p>
        </div>
      </div>`);
    }
  }
},
error: function(err) {
  console.error('영화 데이터를 가져오는 데 실패했습니다.', err);
}
});
}