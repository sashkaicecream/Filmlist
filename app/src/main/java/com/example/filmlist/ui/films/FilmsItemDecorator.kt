package com.example.filmlist.ui.films

import android.R.attr.spacing
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView


class FilmsItemDecorator(
    private val spanCount: Int,
    private val spaceBetween: Int,
    private val spaceEdge: Int,
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)
        val column = position % spanCount

        val halfBetween = spaceBetween / 2

        outRect.top = halfBetween
        outRect.bottom = halfBetween
        when (column) {
            0 -> {
                outRect.left = spaceEdge
                outRect.right = halfBetween
            }
            spanCount - 1 -> {
                outRect.left = halfBetween
                outRect.right = spaceEdge
            }
            else -> {
                outRect.left = halfBetween
                outRect.right = halfBetween
            }
        }
    }
}