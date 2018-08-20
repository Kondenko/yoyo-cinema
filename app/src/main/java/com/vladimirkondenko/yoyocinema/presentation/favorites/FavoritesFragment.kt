package com.vladimirkondenko.yoyocinema.presentation.favorites


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.vladimirkondenko.yoyocinema.R
import com.vladimirkondenko.yoyocinema.presentation.common.FilmFragment
import kotlinx.android.synthetic.main.fragment_favorites.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoritesFragment : FilmFragment() {

    private val vm: FavoritesViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
            inflater.inflate(R.layout.fragment_favorites, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView(favorites_recyclerview_movies)
        vm.state(this) { state ->
            when(state) {
                is FavoritesState.Success -> {
                    favorites_progressbar.isVisible = false
                    filmAdapter.items = ArrayList(state.model)
                }
                is FavoritesState.Loading -> {
                    favorites_progressbar.isVisible = true
                }
                else -> {
                    favorites_progressbar.isVisible = false
                }
            }
        }
        vm.getFavorites()
    }

    override fun onResume() {
        super.onResume()
        vm.getFavorites()
    }

}
