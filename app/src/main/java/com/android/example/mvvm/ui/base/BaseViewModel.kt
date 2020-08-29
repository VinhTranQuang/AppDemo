package com.android.example.mvvm.ui.base

import android.arch.lifecycle.ViewModel
import com.android.example.mvvm.interfaces.onLoadDataFormAPI
abstract class BaseViewModel : ViewModel() {

   var interfaceLoad : onLoadDataFormAPI? =null

}