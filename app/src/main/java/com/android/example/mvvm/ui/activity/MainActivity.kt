package com.android.example.mvvm.ui

import DaggerFragment.ultil.helper.SharePreferenceHelper
import android.databinding.DataBindingUtil
import android.os.Bundle
import br.com.wellingtoncosta.mvvm.ui.common.ViewPagerAdapter
import com.android.example.mvvm.ListColorsFragment
import dagger.android.support.DaggerAppCompatActivity
import com.android.example.mvvm.R
import com.android.example.mvvm.databinding.ActivityMainBinding
import com.android.example.mvvm.ui.fragments.users.ListUsersFragment

class MainActivity : DaggerAppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupViews()
    }

    private fun setupViews() {
        val sharedPreference = SharePreferenceHelper(this)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        sharedPreference.saveString(sharedPreference.ACCESSTOKEN, "122222222222")
        setupToolbar()
        setupTabs()
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.includeToolbar?.toolbar)
        supportActionBar?.title = getString(R.string.app_name)
    }

    private fun setupTabs() {
        val adapter = ViewPagerAdapter(supportFragmentManager)

        adapter.addFragment(ListUsersFragment(), getString(R.string.users))
        adapter.addFragment(ListColorsFragment(), getString(R.string.colors))

        binding.viewPager.adapter = adapter
        binding.includeToolbar?.tabs?.setupWithViewPager(binding.viewPager)
    }

}