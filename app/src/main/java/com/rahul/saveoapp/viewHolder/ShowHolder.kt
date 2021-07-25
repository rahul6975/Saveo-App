package com.rahul.saveoapp.viewHolder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rahul.saveoapp.interfaces.ClickListener
import com.rahul.saveoapp.modelClass.ImageClass
import com.rahul.saveoapp.modelClass.ResponseClass
import com.rahul.saveoapp.modelClass.ShowClass
import kotlinx.android.synthetic.main.item_layout.view.*

class ShowHolder(private val view: View) :
    RecyclerView.ViewHolder(view) {

    fun setData(showClass: ShowClass) {
        view.apply {
            Glide.with(showImage).load(showClass.image!!.original).into(showImage)
        }
    }
}