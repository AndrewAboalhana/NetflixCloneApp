package com.aa.netflixclone.repository.mappers

import com.aa.netflixclone.data.remote.resources.movie_details.MovieDetailsResource
import com.aa.netflixclone.domain.models.movie.MovieDetailsEntity

fun MovieDetailsResource.toMovieDetailsEntity(): MovieDetailsEntity{
    return MovieDetailsEntity(
        id = id,
        imageUrl = posterPath,
        adult = adult,
        imdbId = imdbId,
        overview = overview,
        date = releaseDate,
        runtime = runtime,
        title = title,
        video = video ,
        voteAverage = voteAverage

    )
}