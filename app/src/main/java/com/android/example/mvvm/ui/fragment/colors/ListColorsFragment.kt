package com.android.example.mvvm

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.wellingtoncosta.mvvm.ui.colors.ListColorsViewModel
import com.android.example.mvvm.R
import com.android.example.mvvm.api.response.ColorsResponse
import com.android.example.mvvm.databinding.FragmentListColorsBinding
import com.android.example.mvvm.interfaces.onLoadDataFormAPI
import dagger.android.support.DaggerFragment
import javax.inject.Inject
class ListColorsFragment : DaggerFragment(), onLoadDataFormAPI {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: ListColorsViewModel

    private lateinit var binding: FragmentListColorsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ListColorsViewModel::class.java)
        viewModel.interfaceLoad = this@ListColorsFragment
        viewModel.getListColors()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list_colors, container, false)
        binding.recyclerView.layoutManager = LinearLayoutManager(activity)
        binding.swipeContainer.setOnRefreshListener(viewModel::getListColors)
        return binding.root
    }


    override fun onShowLoading() {
        binding.swipeContainer.isRefreshing = true
    }

    override fun onFailure(t: Throwable) {
        binding.swipeContainer.isRefreshing = false
    }

    override fun onResponse(response: Any?) {
        binding.swipeContainer.isRefreshing = false
        if(response != null && response is ColorsResponse) {
            binding.colors = response.data
            binding.executePendingBindings()
        } else {
            Snackbar.make(binding.root, R.string.load_data_error, Snackbar.LENGTH_LONG).show()
        }
    }

}
