package com.example.mybookapp.ui.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mybookapp.databinding.ItemBookBinding
import com.example.mybookapp.model.BooksResponse

class HomeAdapter : RecyclerView.Adapter<HomeAdapter.HomeHolder>() {

    private var bookList: MutableList<BooksResponse.Item> = arrayListOf()

    class HomeHolder(private var binding: ItemBookBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: BooksResponse.Item) {
            with(binding) {
                itemTVNameBook.text = item.volumeInfo.title
                itemTVAuthor.text = item.volumeInfo.authors?.toString()
                itemTVCategory.text = item.volumeInfo.categories.toString()

                Glide.with(itemView.context).load(item.volumeInfo.imageLinks.thumbnail).into(itemIVHome)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeHolder {
        val binding = ItemBookBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeHolder(binding)
    }

    override fun getItemCount(): Int {
        return bookList.size
    }

    override fun onBindViewHolder(holder: HomeHolder, position: Int) {
        holder.bind(bookList[position])


    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateBooksList(newBooks: List<BooksResponse.Item>) {
        bookList.addAll(newBooks)
        notifyDataSetChanged()
    }

}