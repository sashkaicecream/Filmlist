package com.example.filmlist.ui.films.filmlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.filmlist.R
import com.example.filmlist.databinding.ItemFilmBinding
import com.example.filmlist.ui.utils.getHeart
import com.squareup.picasso.Picasso

class FilmsAdapter(
    val onLikeClicked: (Int, Boolean) -> Unit,
    val onItemClicked: (Int) -> Unit,
) : RecyclerView.Adapter<FilmsAdapter.FilmsViewHolder>() {
    private var data = listOf<FilmRecyclerItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmsViewHolder {
        val binding = ItemFilmBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false,
        )
        return FilmsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FilmsViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount() = data.size

    fun updateDataSet(new: List<FilmRecyclerItem>) {
        val diffUtils = FilmsDiffUtils(data, new)
        val filmsDiffUtil = DiffUtil.calculateDiff(diffUtils, false)

        data = new
        filmsDiffUtil.dispatchUpdatesTo(this)
    }

    inner class FilmsViewHolder(
        private val binding: ItemFilmBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(film: FilmRecyclerItem) {
            val picasso = Picasso.get()
            if (film.poster != null) {
                picasso
                    .load(film.poster)
                    .placeholder(R.drawable.pic_no_poster)
                    .error(R.drawable.pic_no_poster)
                    .into(binding.poster)
            } else {
                picasso.load(R.drawable.pic_no_poster_large).into(binding.poster)
            }

            binding.like.setImageResource(getHeart(film.liked))
            binding.like.setOnClickListener {
                val newState = !film.liked

                film.liked = newState
                binding.like.setImageResource(getHeart(newState))

                onLikeClicked(film.id, newState)
            }

            binding.root.setOnClickListener {
                onItemClicked(film.id)
            }

            val title = film.title
            binding.title.text = if (title.isNotBlank()) title else "[no title]"

            val date = film.date
            binding.year.text = if (title.isNotBlank()) date else "[no date]"
        }
    }
}
