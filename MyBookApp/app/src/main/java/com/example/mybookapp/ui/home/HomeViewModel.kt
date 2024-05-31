package com.example.mybookapp.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mybookapp.data.BookRepository
import com.example.mybookapp.model.BooksResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class HomeViewModel : ViewModel() {

    val books = MutableLiveData<List<BooksResponse.Item>>()
    private val repository = BookRepository()
    private val disposable = CompositeDisposable()

    fun getBooksFromRepository() {
        disposable.add(
            repository.getBooks().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<BooksResponse>() {
                    override fun onSuccess(t: BooksResponse) {
                        books.value = t.items
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                    }

                })

        )

    }

}