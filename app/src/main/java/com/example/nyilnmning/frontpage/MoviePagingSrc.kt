package com.example.nyilnmning.frontpage

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.nyilnmning.api.ApiInterface
import com.example.nyilnmning.model.Movie
import javax.inject.Inject

class MoviePagingSrc  @Inject constructor(
    private val api: ApiInterface,
    private val apiKey: String
) : PagingSource<Int, Movie>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        // which page 1 of X, starts with page 1
        val page = params.key ?: 1
        return try {
            val response = api.getPopular(apiKey, page)
            LoadResult.Page(
                data = response.results, // fetched list of movies
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (response.results.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { position ->
            state.closestPageToPosition(position)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(position)?.nextKey?.minus(1)
        }
    }
}


//params = .key = key for current page, can be page num, timestamp or any identifier.
//loadside = the number of items to load in request