package com.example.mybookapp.ui.search

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mybookapp.databinding.ItemSearchBinding
import com.example.mybookapp.model.BooksResponse

class SearchAdapter : RecyclerView.Adapter<SearchAdapter.SearchHolder>(){

    private var bookList : MutableList<BooksResponse.Item> = arrayListOf()

    class SearchHolder(private var binding: ItemSearchBinding) : RecyclerView.ViewHolder(binding.root) {


        fun bind(item : BooksResponse.Item) {
            with(binding) {
                itemTVName.text = item.volumeInfo.title
                itemTVAuthorname.text = item.volumeInfo.authors?.toString()
                itemTVCategoryname.text = item.volumeInfo.categories.toString()

                Glide.with(itemView.context).load(item.volumeInfo.imageLinks.thumbnail).into(itemIVSearch)
            }
        }



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchHolder {
        val binding = ItemSearchBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return SearchHolder(binding)
    }

    override fun getItemCount(): Int {
        return bookList.size
    }

    override fun onBindViewHolder(holder: SearchHolder, position: Int) {
        holder.bind(bookList[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateBooksList(newBooks : List<BooksResponse.Item>){

        bookList.addAll(newBooks)
        notifyDataSetChanged()
    }
}