package com.android.example.mvvm.ui

import DaggerFragment.ui.users.LoginViewModel
import DaggerFragment.ultil.helper.DialogHelper
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.android.example.mvvm.R
import com.android.example.mvvm.interfaces.onLoadDataFormAPI
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.FacebookSdk
import com.facebook.login.LoginResult
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_login.*
import javax.inject.Inject


class LoginActivity : DaggerAppCompatActivity(), onLoadDataFormAPI {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: LoginViewModel
    var dialog: DialogHelper? = null
    lateinit var callbackManager: CallbackManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        callbackManager = CallbackManager.Factory.create();
        FacebookSdk.sdkInitialize(getApplicationContext());
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
        login_fbutton.registerCallback(callbackManager, object : FacebookCallback<LoginResult?> {
            override fun onSuccess(loginResult: LoginResult?) {
                Toast.makeText(this@LoginActivity, "Successful", Toast.LENGTH_LONG).show()
                Log.d("Token:", loginResult?.accessToken?.token.toString())
                startMainAcitivy()
            }

            override fun onCancel() {
                Toast.makeText(this@LoginActivity, "Login attempt canceled.", Toast.LENGTH_LONG).show()
            }

            override fun onError(e: FacebookException) {
                Toast.makeText(this@LoginActivity, "Login attempt failed.", Toast.LENGTH_LONG).show()
            }
        })
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
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager.onActivityResult(requestCode, resultCode, data)
    }
}