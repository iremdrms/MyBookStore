package com.example.mybookapp.data

import com.example.mybookapp.model.BooksResponse
import com.example.mybookapp.util.Constants.VOLUMES
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface BookInterface {

    @GET(VOLUMES)
    fun getBooksFromApi(
        @Query("q") q:String
    ): Single<BooksResponse>
}