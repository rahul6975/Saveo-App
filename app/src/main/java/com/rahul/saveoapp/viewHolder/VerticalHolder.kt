package com.rahul.saveoapp.viewHolder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rahul.saveoapp.interfaces.ClickListener
import com.rahul.saveoapp.modelClass.ImageClass
import com.rahul.saveoapp.modelClass.ShowClass
import com.rahul.saveoapp.modelhorizontal.ImageClasss
import kotlinx.android.synthetic.main.item_layout.view.*
import kotlinx.android.synthetic.main.vertical_item.view.*

class VerticalHolder(private val view: View, private val clickListener: ClickListener) :
    RecyclerView.ViewHolder(view) {

    fun setData(imageClasss: ImageClasss) {
        view.apply {
            Glide.with(showImage2).load(imageClasss.original).into(showImage2)
        }
        view.setOnClickListener {
            clickListener.onClick(adapterPosition)
        }
    }
}