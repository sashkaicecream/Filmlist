package com.example.filmlist.ui.animations

import androidx.annotation.AnimRes
import com.example.filmlist.R
import com.example.filmlist.ui.ApplicationStage
import com.example.filmlist.ui.compareTo

data class AnimationSet(
    @AnimRes val animEnter: Int = R.anim.slide_from_right,
    @AnimRes val animExit: Int = R.anim.slide_to_left,
    @AnimRes val popEnter: Int = R.anim.slide_from_left,
    @AnimRes val popExit: Int = R.anim.slide_to_right,
)

fun getAnimationSet(cur: ApplicationStage?, prev: ApplicationStage?) = when {
    prev == null -> {
        AnimationSet(
            animEnter = R.anim.no_anim,
            animExit = R.anim.slide_to_left,
            popEnter = R.anim.no_anim,
            popExit = R.anim.slide_to_right
        )
    }
    prev <= cur -> {
        AnimationSet(
            animEnter = R.anim.slide_from_right,
            animExit = R.anim.slide_to_left,
            popEnter = R.anim.slide_from_left,
            popExit = R.anim.slide_to_right
        )
    }
    else -> {
        AnimationSet(
            animEnter = R.anim.slide_from_left,
            animExit = R.anim.slide_to_right,
            popEnter = R.anim.slide_from_right,
            popExit = R.anim.slide_to_left
        )
    }
}
