package com.example.mybookapp.data

import com.example.mybookapp.model.BooksResponse
import com.example.mybookapp.util.Constants.A
import com.example.mybookapp.util.Constants.BASE_URL
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class BookRepository {

    fun getBooks(a:String= A): Single<BooksResponse> {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(BookInterface::class.java)
            .getBooksFromApi(a)
    }


}