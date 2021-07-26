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

    //sets the recyclerview
    private fun setRecyclerView() {
        showAdapter = ShowAdapter(movieList)
        val linearLayoutManager = LinearLayoutManager(this.context)
        linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        recyclerview.layoutManager = linearLayoutManager
        recyclerview.adapter = showAdapter
        var isLastPage: Boolean = false
        var isLoading: Boolean = false

        recyclerview.addOnScrollListener(object : PaginationScrollListener(linearLayoutManager) {
            override fun isLastPage(): Boolean {
                return isLastPage
            }

            override fun isLoading(): Boolean {
                return isLoading
            }

            override fun loadMoreItems() {
                isLoading = true
                //you have to call loadmore items to get more data
                hitApi()
            }
        })

        fun getMoreItems() {
            isLoading = false

            showAdapter.updateData(movieList)
        }



        verticalAdapter = VerticalAdapter(verticalList, this)
        val gridLayoutManager = GridLayoutManager(this.context, 3)
        verticalrecyclerview.layoutManager = gridLayoutManager
        verticalrecyclerview.adapter = verticalAdapter

    }

    //hit the api and notify our adapter that new data has been received
    private fun hitApi() {
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewModel.getMovie().observe(this.requireActivity(), Observer {
            movieList = it
            showAdapter.updateData(movieList)
        })

        viewModel.getMovie2().observe(this.requireActivity(), Observer {
            verticalList = it
            verticalAdapter.updateData(verticalList)
        })
    }


    /*
     this function gets triggered when we click any viewHolder in recyclerview
      and gives us the position of the view
    */

    override fun onClick(position: Int) {
        detailViewModel.getShowDetails(verticalList[position])
        val detailFragment = DetailFragment()
        requireActivity().supportFragmentManager.beginTransaction()
            .add(R.id.fragment1, detailFragment, "detailFragment").addToBackStack(null).commit()
    }

    // pagination for infinite scrolling

    abstract class PaginationScrollListener
        (var layoutManager: LinearLayoutManager) : RecyclerView.OnScrollListener() {

        abstract fun isLastPage(): Boolean

        abstract fun isLoading(): Boolean

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            val visibleItemCount = layoutManager.childCount
            val totalItemCount = layoutManager.itemCount
            val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

            if (!isLoading() && !isLastPage()) {
                if (visibleItemCount + firstVisibleItemPosition >= totalItemCount && firstVisibleItemPosition >= 0) {
                    loadMoreItems()
                }//                    && totalItemCount >= ClothesFragment.itemsCount
            }
        }

        abstract fun loadMoreItems()
    }

}