package com.rahul.saveoapp.views

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
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
import com.rahul.saveoapp.viewModel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), ClickListener {
    private lateinit var showAdapter: ShowAdapter
    private lateinit var verticalAdapter: VerticalAdapter
    private var viewModel = MainViewModel()
    private var movieList: List<ResponseClass> = listOf()
    private var verticalList: List<HorizonalClass> = listOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setRecyclerView()
        hitapi()
    }

    private fun setRecyclerView() {
        showAdapter = ShowAdapter(movieList, this)
        val linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        recyclerview.layoutManager = linearLayoutManager
        recyclerview.adapter = showAdapter

        verticalAdapter = VerticalAdapter(verticalList, this)
        val gridLayoutManager = GridLayoutManager(this,3)
        verticalrecyclerview.layoutManager = gridLayoutManager
        verticalrecyclerview.adapter = verticalAdapter

    }

    private fun hitapi() {
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewModel.getMovie().observe(this, Observer {
            showAdapter.updateData(it)
        })

        viewModel.getMovie2().observe(this, Observer {
            verticalAdapter.updateData(it)
        })
    }

    override fun onClick(position: Int) {
        Toast.makeText(this, "$position", Toast.LENGTH_SHORT).show()
    }

}