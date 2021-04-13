package com.kfjohnny.pokweather.util.extensions

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.core.widget.doOnTextChanged

/**
 * @author Created by Johnnylee Rocha (kfjohnny2) on 4/11/2021.
 */

/**
 * Extension function: Adding search function for running on "afterTextChanged" into editText
 */
fun EditText.onSearchTextChanged(searchTextChanged: (String) -> Unit){
    this.doOnTextChanged { text, _, _, _ ->
        searchTextChanged.invoke(text.toString())
    }
}