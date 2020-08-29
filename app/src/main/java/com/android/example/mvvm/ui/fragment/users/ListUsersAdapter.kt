package com.android.example.mvvm.ui.fragments.users

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import br.com.wellingtoncosta.mvvm.ui.users.ListUsersViewHolder
import com.android.example.mvvm.R
import com.android.example.mvvm.domain.model.User
import com.android.example.mvvm.ui.base.BaseAdapter

class ListUsersAdapter constructor(list: List<User>) : BaseAdapter<User>(list) {

    override fun onCreateViewHolderBase(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        return ListUsersViewHolder(LayoutInflater
                .from(parent?.context)
                .inflate(R.layout.list_users_item, parent, false))
    }

    override fun onBindViewHolderBase(holder: RecyclerView.ViewHolder?, position: Int) {
        val binding = (holder as ListUsersViewHolder).binding
        val user = list[position]
        binding?.user = user
    }

}