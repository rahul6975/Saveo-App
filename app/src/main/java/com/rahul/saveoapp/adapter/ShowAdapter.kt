package com.rahul.saveoapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rahul.saveoapp.R
import com.rahul.saveoapp.interfaces.ClickListener
import com.rahul.saveoapp.modelClass.ResponseClass
import com.rahul.saveoapp.viewHolder.ShowHolder

class ShowAdapter(
    private var showList: List<ResponseClass>,
) :
    RecyclerView.Adapter<ShowHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return ShowHolder(view)
    }

    override fun onBindViewHolder(holder: ShowHolder, position: Int) {
        val showLists = showList[position].show!!
        holder.setData(showLists)
    }

    override fun getItemCount(): Int {
        return showList.size
    }

    fun updateData(showList: List<ResponseClass>) {
        this.showList = showList
        notifyDataSetChanged()
    }
}