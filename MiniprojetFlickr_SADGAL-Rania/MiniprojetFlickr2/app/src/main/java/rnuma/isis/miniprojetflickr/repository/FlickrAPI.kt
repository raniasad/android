package rnuma.isis.miniprojetflickr.repository

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import rnuma.isis.miniprojetflickr.model.SearchResult

interface FlickrAPI {
    @GET("/services/rest?format=json&nojsoncallback=1")
    fun getInterestingPhotos(@Query("method") method : String,
                             @Query("api_key") key: String, @Query("per_page")
                             perpage: String) : Call<SearchResult>
}