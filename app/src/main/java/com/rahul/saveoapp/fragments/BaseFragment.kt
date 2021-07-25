package com.rahul.saveoapp.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rahul.saveoapp.R
import com.rahul.saveoapp.adapter.ShowAdapter
import com.rahul.saveoapp.adapter.VerticalAdapter
import com.rahul.saveoapp.interfaces.ClickListener
import com.rahul.saveoapp.modelClass.ResponseClass
import com.rahul.saveoapp.modelhorizontal.HorizonalClass
import com.rahul.saveoapp.viewModel.DetailViewModel
import com.rahul.saveoapp.viewModel.MainViewModel
import kotlinx.android.synthetic.main.fragment_base.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class BaseFragment : Fragment(), ClickListener {
    private lateinit var showAdapter: ShowAdapter
    private lateinit var verticalAdapter: VerticalAdapter
    private var viewModel = MainViewModel()
    private lateinit var detailViewModel: DetailViewModel
    private var movieList: List<ResponseClass> = listOf()
    private var verticalList: List<HorizonalClass> = listOf()
    private var loading = true
    var pastVisiblesItems = 0
    var visibleItemCount: Int = 0
    var totalItemCount: Int = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_base, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        detailViewModel = ViewModelProviders.of(requireActivity()).get(DetailViewModel::class.java)
        setRecyclerView()
        hitApi()
    }

    private fun setRecyclerView() {
        showAdapter = ShowAdapter(movieList)
        val linearLayoutManager = LinearLayoutManager(this.context)
        linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        recyclerview.layoutManager = linearLayoutManager
        recyclerview.adapter = showAdapter

        recyclerview.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0) { //check for scroll down
                    visibleItemCount = linearLayoutManager.getChildCount()
                    totalItemCount = linearLayoutManager.getItemCount()
                    pastVisiblesItems = linearLayoutManager.findFirstVisibleItemPosition()
                    if (loading) {
                        if (visibleItemCount + pastVisiblesItems >= totalItemCount) {
                            loading = false
                            Log.d("hello", "Last Item Wow !")
                            CoroutineScope(Dispatchers.IO).launch {
                                viewModel.getMovie()
                            }
                            loading = true
                        }
                    }
                }
            }
        })



        verticalAdapter = VerticalAdapter(verticalList, this)
        val gridLayoutManager = GridLayoutManager(this.context, 3)
        verticalrecyclerview.layoutManager = gridLayoutManager
        verticalrecyclerview.adapter = verticalAdapter

        verticalrecyclerview.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0) { //check for scroll down
                    visibleItemCount = gridLayoutManager.getChildCount()
                    totalItemCount = gridLayoutManager.getItemCount()
                    pastVisiblesItems = gridLayoutManager.findFirstVisibleItemPosition()
                    if (loading) {
                        if (visibleItemCount + pastVisiblesItems >= totalItemCount) {
                            loading = false
                            Log.v("...", "Last Item Wow !")
                            CoroutineScope(Dispatchers.IO).launch {
                                viewModel.getMovie2()
                            }
                            loading = true
                        }
                    }
                }
            }
        })
    }

    private fun hitApi() {
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewModel.getMovie().observe(this.requireActivity(), Observer {
            showAdapter.updateData(it)
        })

        viewModel.getMovie2().observe(this.requireActivity(), Observer {
            verticalList = it
            verticalAdapter.updateData(verticalList)
        })
    }

    override fun onClick(position: Int) {
        detailViewModel.getShowDetails(verticalList[position])
        val detailFragment = DetailFragment()
        requireActivity().supportFragmentManager.beginTransaction()
            .add(R.id.fragment1, detailFragment, "detailFragment").addToBackStack(null).commit()
    }

}