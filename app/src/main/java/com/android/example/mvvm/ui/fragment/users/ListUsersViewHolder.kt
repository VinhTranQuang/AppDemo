package br.com.wellingtoncosta.mvvm.ui.users

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.View
import com.android.example.mvvm.databinding.ListUsersItemBinding

class ListUsersViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val binding: ListUsersItemBinding? = DataBindingUtil.bind(view)

}