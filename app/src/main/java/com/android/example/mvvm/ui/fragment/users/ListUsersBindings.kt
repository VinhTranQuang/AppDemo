package br.com.wellingtoncosta.mvvm.ui.users

import android.databinding.BindingAdapter
import android.support.v7.widget.RecyclerView
import com.android.example.mvvm.domain.model.User
import com.android.example.mvvm.ui.fragments.users.ListUsersAdapter
import com.facebook.drawee.view.SimpleDraweeView
import com.facebook.drawee.generic.RoundingParams
object ListUsersBindings {

    @JvmStatic
    @BindingAdapter("load_users")
    fun loadUsers(recyclerView: RecyclerView, users: List<User>?) {
        recyclerView.adapter = if (users != null) ListUsersAdapter(users) else ListUsersAdapter(emptyList())
    }

    @JvmStatic
    @BindingAdapter("load_user_avatar")
    fun loadUserAvatar(simpleDraweeView: SimpleDraweeView, user: User?) {
        val roundingParams = RoundingParams.fromCornersRadius(5f)
        roundingParams.roundAsCircle = true

        simpleDraweeView.hierarchy.roundingParams = roundingParams
        simpleDraweeView.setImageURI(user?.avatar)
    }

}