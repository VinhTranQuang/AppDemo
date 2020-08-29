package com.android.example.mvvm.ui.fragments.users

import DaggerFragment.ui.users.ListUsersViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.example.mvvm.R
import com.android.example.mvvm.api.response.UsersResponse
import com.android.example.mvvm.databinding.FragmentListUsersBinding
import com.android.example.mvvm.interfaces.AppDatabase
import com.android.example.mvvm.interfaces.onLoadDataFormAPI
import dagger.android.support.DaggerFragment
import javax.inject.Inject
class ListUsersFragment : DaggerFragment(), onLoadDataFormAPI {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: ListUsersViewModel

    private lateinit var binding: FragmentListUsersBinding
    var database : AppDatabase?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ListUsersViewModel::class.java)
        viewModel.interfaceLoad = this@ListUsersFragment
        viewModel.getListUsers()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list_users, container, false)
        binding.recyclerView.layoutManager = LinearLayoutManager(activity)
        binding.swipeContainer.setOnRefreshListener(viewModel::getListUsers)
        return binding.root
    }

    private fun observeLoadingStatus() {
//        database = AppDatabase.getAppDataBase(activity!!)
//        Observable.fromCallable { database?.databaseDao()!!.insertOrder(response.data?.listOrder!!) }
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(
//                        {
//                        },
//                        {
//                        }
//                )
    }

    override fun onShowLoading() {
        binding.swipeContainer.isRefreshing = true
    }

    override fun onFailure(t: Throwable) {
        binding.swipeContainer.isRefreshing = false
    }

    override fun onResponse(response: Any?) {
        binding.swipeContainer.isRefreshing = false
        if(response != null && response is UsersResponse) {
            binding.users = response.data
            binding.executePendingBindings()
        } else {
            Snackbar.make(binding.root, R.string.load_data_error, Snackbar.LENGTH_LONG).show()
        }
    }
}