package com.giftoholics

/*
  Created by fedor on 28.11.2016.
 */


import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.giftoholics.pojo.Category

class CategoryAdapter(private val categoryList: List<Category>, var listener :(Category)-> Unit) : RecyclerView.Adapter<CategoryAdapter.MyViewHolder>() {

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var txtCategoryName: TextView

        init {
            txtCategoryName = view.findViewById<View>(R.id.txtCategoryName) as TextView
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_category, parent, false)

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val categoryname = categoryList[position]
        holder.txtCategoryName.text = categoryname.categoryname
        holder?.txtCategoryName?.setOnClickListener {
            listener.invoke(categoryname)
        }

    }

    override fun getItemCount(): Int {
        return categoryList.size
    }
}