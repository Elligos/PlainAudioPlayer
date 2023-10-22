package com.example.plainaudioplayer.data.audio


interface AudioRepository {

    suspend fun searchMusic(query: String): String

//    suspend fun searchMoviesWithReviews(query: String): Result<List<SearchMovieWithMyReview>, Throwable>
//
//    suspend fun getMovieDetails(id: MovieId): Result<Movie, Throwable>
//
//    fun observeMovieDetailsWithReviews(id: MovieId): Flow<Result<MovieWithReviews, Throwable>>
//
//    suspend fun addReview(
//        draft: ReviewDraft,
//        authorId: User.Id,
//        authorEmail: User.Email,
//    ): VoidResult<Throwable>
//
//    suspend fun getReviewsForUser(userId: User.Id)
//
//    suspend fun deleteUserReviews()
}

//suspend fun AudioRepository.getMovieDetailsList(ids: Set<MovieId>): Result<List<Movie>, List<Throwable>> =
//    coroutineScope {
//        val asyncCalls = ids.map { singleId ->
//            async { getMovieDetails(singleId) }
//        }
//
//        asyncCalls.awaitAll()
//            .toSuccessOrErrorList()
//    }