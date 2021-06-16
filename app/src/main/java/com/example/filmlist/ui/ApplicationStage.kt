package com.example.filmlist.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import com.example.filmlist.ui.animations.AnimSet

sealed class ApplicationStage(val weight: Int) {
    object Cancel : ApplicationStage(-1)
    object FilmsStage : ApplicationStage(0)
    object LikedFilmsStage : ApplicationStage(1)
    class FilmDetailedStage(val id: Int) : ApplicationStage(2)
}

operator fun ApplicationStage.compareTo(other: ApplicationStage?): Int {
    return this.weight - (other?.weight ?: 0)
}

fun FragmentManager.changeFragment(
    fragment: Fragment,
    container: Int,
    backStack: Boolean = true,
    animSet: AnimSet,
) {
    this.commit {
        val (animEnter, animExit, popEnter, popExit) = animSet
        setCustomAnimations(animEnter, animExit, popEnter, popExit)
        replace(container, fragment)
        if (backStack) addToBackStack(fragment::class.simpleName)
    }
}
