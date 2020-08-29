package com.android.example.mvvm.ui.fragments.users.colors

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.android.example.mvvm.R
import com.android.example.mvvm.domain.model.Color
import com.android.example.mvvm.ui.base.BaseAdapter

class ListColorsAdapter constructor(list: List<Color>) : BaseAdapter<Color>(list) {

    override fun onCreateViewHolderBase(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        return ListColorsViewHolder(LayoutInflater
                .from(parent?.context)
                .inflate(R.layout.list_colors_item, parent, false))
    }

    override fun onBindViewHolderBase(holder: RecyclerView.ViewHolder?, position: Int) {
        val binding = (holder as ListColorsViewHolder).binding
        val color = list[position]
        binding?.color = color
    }

}