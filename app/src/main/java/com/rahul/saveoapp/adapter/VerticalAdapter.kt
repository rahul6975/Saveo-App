package com.rahul.saveoapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rahul.saveoapp.R
import com.rahul.saveoapp.interfaces.ClickListener
import com.rahul.saveoapp.modelhorizontal.HorizonalClass
import com.rahul.saveoapp.viewHolder.VerticalHolder

//adapter class which holds the data

class VerticalAdapter(
    private var showList: List<HorizonalClass>,
    private val clickListener: ClickListener
) :
    RecyclerView.Adapter<VerticalHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VerticalHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.vertical_item, parent, false)
        return VerticalHolder(view, clickListener)
    }

    override fun onBindViewHolder(holder: VerticalHolder, position: Int) {
        val showLists = showList[position].image
        holder.setData(showLists!!)
    }

    override fun getItemCount(): Int {
        return showList.size
    }

    // notifies the adapter everytime new data is received
    fun updateData(showList: List<HorizonalClass>) {
        this.showList = showList
        notifyDataSetChanged()
    }
}