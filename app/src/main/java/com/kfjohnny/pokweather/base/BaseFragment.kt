package com.kfjohnny.pokweather.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kfjohnny.pokweather.ui.main.MainViewModel

abstract class BaseFragment<D : ViewDataBinding> : Fragment(), LifecycleOwner {
    protected lateinit var binding : D

    @LayoutRes
    protected abstract fun layoutRes(): Int


    protected fun tagFragment(): String = javaClass.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layoutRes(), container, false)
        binding.lifecycleOwner = this

        return binding.root
    }

}