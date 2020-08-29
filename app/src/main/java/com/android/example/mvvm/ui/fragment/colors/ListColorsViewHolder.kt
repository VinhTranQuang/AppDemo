package com.android.example.mvvm.ui.fragments.users.colors

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.View
import com.android.example.mvvm.databinding.ListColorsItemBinding

class ListColorsViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val binding: ListColorsItemBinding? = DataBindingUtil.bind(view)

}