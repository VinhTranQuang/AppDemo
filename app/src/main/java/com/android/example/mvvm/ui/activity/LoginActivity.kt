package com.android.example.mvvm.ui

import DaggerFragment.ui.users.LoginViewModel
import DaggerFragment.ultil.helper.DialogHelper
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.util.Patterns
import android.widget.Toast
import com.android.example.mvvm.R
import com.android.example.mvvm.interfaces.onLoadDataFormAPI
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_login.*
import java.sql.Time
import javax.inject.Inject


@Suppress("DEPRECATION")
class LoginActivity : DaggerAppCompatActivity(), onLoadDataFormAPI {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: LoginViewModel
    var dialog: DialogHelper? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(LoginViewModel::class.java)
        viewModel.interfaceLoad = this@LoginActivity
        setupViews()
    }

    private fun setupViews() {
        setContentView( R.layout.activity_login)
//        val sharedPreference = SharePreferenceHelper(this)

//        sharedPreference.saveString(sharedPreference.ACCESSTOKEN, "122222222222")
        btn_login.setOnClickListener {
            viewModel.validateLogin(input_email.text.toString(), input_password.getText().toString())
        }
        viewModel.emailError.observe(this@LoginActivity, Observer<Boolean> {
            if(it == true) {
                Toast.makeText(this@LoginActivity, "Please enter a valid email", Toast.LENGTH_LONG).show()
            }
        })
        viewModel.passwordError.observe(this@LoginActivity, Observer<Boolean> {
            if(it== true) {
                Toast.makeText(this@LoginActivity, "Pass should be between 4 and 10 alphanumeric characters", Toast.LENGTH_LONG).show()
            }
        })
    }

    override fun onShowLoading() {
        dialog = DialogHelper(this@LoginActivity)
        dialog.let { dialog?.progressShow("Authenticating", null) }
    }

    override fun onFailure(t: Throwable) {
        dialog.let { dialog?.progressDismiss() }
    }

    override fun onResponse(response: Any?) {
        dialog.let {
            dialog?.progressDismiss()
            this@LoginActivity.finish()
            startMainAcitivy()}

    }
    fun startMainAcitivy() {
        val startIntent = Intent(this@LoginActivity, MainActivity::class.java)
        startIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        this@LoginActivity.startActivity(startIntent)
    }

}