package com.kfjohnny.pokweather.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import com.kfjohnny.pokweather.R
import com.kfjohnny.pokweather.ui.description.DetailsFragment
import com.kfjohnny.pokweather.util.extensions.setBackgroundColorFromId
import com.kfjohnny.pokweather.util.extensions.setTitleTextColorFromId

abstract class BaseFragment<D : ViewDataBinding> : Fragment(), LifecycleOwner {
    protected lateinit var binding: D

    @LayoutRes
    protected abstract fun layoutRes(): Int


    protected fun tagFragment(): String = javaClass.simpleName

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