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

/**
 * Base fragment receiving ViewBinding explicit type
 *
 * Extending Fragment and LifecycleOwner for register livedata observers
 *
 */
abstract class BaseFragment<D : ViewDataBinding> : Fragment(), LifecycleOwner {
    /**
     * View Binding template value
     */
    protected lateinit var binding: D

    /**
     * Function for getting layout resource of the fragment
     *
     * @return Layout resource id
     */
    @LayoutRes
    protected abstract fun layoutRes(): Int

    /**
     * Function for getting fragment's tag
     *
     * @return fragment tag name
     */
    protected fun tagFragment(): String = javaClass.simpleName

    /**
     * Create View instantiating content view layout with DataBinding
     * Register binding with the current fragment lifecycle owner
     */
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