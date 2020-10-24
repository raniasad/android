package rnuma.isis.miniprojetflickr.ui.liste

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import rnuma.isis.miniprojetflickr.model.Photo
import rnuma.isis.miniprojetflickr.model.Photos
import rnuma.isis.miniprojetflickr.model.SearchResult
import rnuma.isis.miniprojetflickr.repository.Repository

class ListeViewModel : ViewModel() {
    val rep: Repository = Repository()
    var i=0
    val Lphotos= MutableLiveData<List<Photo>>()

    val call= object: Callback<SearchResult> {
        override fun onFailure(call: Call<SearchResult>, t: Throwable) {
            print("erreur")
        }

        override fun onResponse(call: Call<SearchResult>, response: Response<SearchResult>) {
            var Sr=response.body()
            if (Sr != null) {
                Lphotos.value=Sr.photos.photo
            }



        }

    }

    init {
        rep.getPhotos(call)

    }

}