package com.vladimirkondenko.yoyocinema.presentation.common

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import com.vladimirkondenko.yoyocinema.R
import com.vladimirkondenko.yoyocinema.domain.search.model.Film
import com.vladimirkondenko.yoyocinema.utils.BaseAdapter

class FilmAdapter(context: Context) : BaseAdapter<Film, FilmAdapter.FilmViewHolder>(context) {

   override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int) = FilmViewHolder(getView(R.layout.item_film, viewGroup))

   inner class FilmViewHolder(view: View) : BaseAdapter.BaseViewHolder<Film>(view) {

        override fun bindItem(item: Film) {
            super.bindItem(item)
            if (item.posterUrl.isNotBlank()) {
                Picasso.get()
                        .load(item.posterUrl)
                        .fit()
                        .centerCrop()
                        .placeholder(R.drawable.ic_film_black_48dp)
                        .into(view.findViewById<ImageView>(R.id.item_film_imageview_poster))
            }
            view.findViewById<TextView>(R.id.item_film_textview_title).text = item.title
            view.findViewById<TextView>(R.id.item_film_textview_year).text = item.year.toString()
            view.findViewById<TextView>(R.id.item_film_textview_rating).text = context.getString(R.string.item_film_rating_template, item.rating)
        }

    }

}