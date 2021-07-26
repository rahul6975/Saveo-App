package com.rahul.saveoapp.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rahul.saveoapp.modelhorizontal.HorizonalClass

class DetailViewModel : ViewModel() {

    private var showDetails = MutableLiveData<HorizonalClass>()

    // get the response and save it in mutableLiveData
    fun getShowDetails(details: HorizonalClass) {
        showDetails.value = details
    }

    //release the livedata for observers
    fun showShowDetails(): MutableLiveData<HorizonalClass> {
        return showDetails
    }

}