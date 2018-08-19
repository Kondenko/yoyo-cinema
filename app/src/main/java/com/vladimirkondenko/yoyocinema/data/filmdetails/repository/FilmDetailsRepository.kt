package com.vladimirkondenko.yoyocinema.data.filmdetails.repository

import com.vladimirkondenko.yoyocinema.data.filmdetails.service.FilmDetailsService

class FilmDetailsRepository(private val detailsService: FilmDetailsService) {

    fun getDetails(id: Int) = detailsService.getDetails(id)

}