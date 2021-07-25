package com.rahul.saveoapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.rahul.saveoapp.R
import com.rahul.saveoapp.modelhorizontal.HorizonalClass
import com.rahul.saveoapp.viewModel.DetailViewModel
import kotlinx.android.synthetic.main.fragment_detail.*

class DetailFragment : Fragment() {
    private lateinit var detailViewModel: DetailViewModel
    private lateinit var verticalList: HorizonalClass

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        detailViewModel = ViewModelProviders.of(requireActivity()).get(DetailViewModel::class.java)
        detailViewModel.showShowDetails().observe(this.requireActivity(), Observer {
            verticalList = it
            setData()
        })

    }

    private fun setData() {
        Glide.with(imgShowImage).load(verticalList.image!!.original).into(imgShowImage)
        tvShowName.text = verticalList.name
        tvRating.text = verticalList.language
        tvTime.text = verticalList.runtime.toString()
        tvDate.text = verticalList.premiered
        tvDesciption.text = verticalList.summary

    }

}