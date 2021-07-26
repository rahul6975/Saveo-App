package com.rahul.saveoapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.rahul.saveoapp.modelClass.ResponseClass
import com.rahul.saveoapp.modelhorizontal.HorizonalClass
import com.rahul.saveoapp.repository.MainRespository
import kotlinx.coroutines.Dispatchers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * This is a VM layer in the `MVVM` architecture, where we are notifying the Activity/view about the
 * response changes via live data
 */
class MainViewModel : ViewModel() {

    private val repository = MainRespository()

    //hit the api and release the response using livedata
    fun getMovie(): LiveData<List<ResponseClass>> {
        return liveData(Dispatchers.IO) {
            val result = repository.getListOfMovies()
            emit(result.data!!)
        }
    }

    fun getMovie2(): LiveData<List<HorizonalClass>> {
        return liveData(Dispatchers.IO) {
            val result = repository.getListOfMovies2()
            emit(result.data!!)
        }
    }
}