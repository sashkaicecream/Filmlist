package com.example.filmlist.ui.films.filmlist

import androidx.recyclerview.widget.DiffUtil

class FilmsDiffUtils(
    private val old: List<FilmRecyclerItem>,
    private val new: List<FilmRecyclerItem>,
    ) : DiffUtil.Callback() {
    override fun getOldListSize() = old.size
    override fun getNewListSize() = new.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return old[oldItemPosition] === new[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return old[oldItemPosition] == new[newItemPosition]
    }
}