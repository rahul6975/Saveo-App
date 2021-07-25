package com.rahul.saveoapp.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rahul.saveoapp.modelhorizontal.HorizonalClass

class DetailViewModel : ViewModel() {

    private var showDetails = MutableLiveData<HorizonalClass>()

    fun getShowDetails(details: HorizonalClass) {
        showDetails.value = details
    }

    fun showShowDetails(): MutableLiveData<HorizonalClass> {
        return showDetails
    }

}