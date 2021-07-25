package com.rahul.saveoapp.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.rahul.saveoapp.R
import com.rahul.saveoapp.adapter.ShowAdapter
import com.rahul.saveoapp.adapter.VerticalAdapter
import com.rahul.saveoapp.interfaces.ClickListener
import com.rahul.saveoapp.modelClass.ResponseClass
import com.rahul.saveoapp.modelhorizontal.HorizonalClass
import com.rahul.saveoapp.viewModel.DetailViewModel
import com.rahul.saveoapp.viewModel.MainViewModel
import com.rahul.saveoapp.views.MainActivity
import kotlinx.android.synthetic.main.fragment_base.*


class BaseFragment : Fragment(), ClickListener {
    private lateinit var showAdapter: ShowAdapter
    private lateinit var verticalAdapter: VerticalAdapter
    private var viewModel = MainViewModel()
    private lateinit var detailViewModel: DetailViewModel
    private var movieList: List<ResponseClass> = listOf()
    private var verticalList: List<HorizonalClass> = listOf()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_base, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        detailViewModel = ViewModelProviders.of(this).get(DetailViewModel::class.java)
        setRecyclerView()
        hitApi()
    }

    private fun setRecyclerView() {
        showAdapter = ShowAdapter(movieList, this)
        val linearLayoutManager = LinearLayoutManager(this.context)
        linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        recyclerview.layoutManager = linearLayoutManager
        recyclerview.adapter = showAdapter

        verticalAdapter = VerticalAdapter(verticalList, this)
        val gridLayoutManager = GridLayoutManager(this.context, 3)
        verticalrecyclerview.layoutManager = gridLayoutManager
        verticalrecyclerview.adapter = verticalAdapter
    }

    private fun hitApi() {
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewModel.getMovie().observe(this.requireActivity(), Observer {
            showAdapter.updateData(it)
        })

        viewModel.getMovie2().observe(this.requireActivity(), Observer {
            verticalAdapter.updateData(it)
        })
    }

    override fun onClick(position: Int) {
        detailViewModel.getShowDetails(verticalList[position])
        val intent = Intent(requireActivity().baseContext, MainActivity::class.java)
        intent.putExtra("message", "kill me")
        requireActivity().startActivity(intent)
    }

}