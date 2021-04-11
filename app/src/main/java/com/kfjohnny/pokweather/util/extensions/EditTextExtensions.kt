package com.kfjohnny.pokweather.util.extensions

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

/**
 * @author Created by Johnnylee Rocha (kfjohnny2) on 4/11/2021.
 */

/**
 * Extension function: Adding search function for running on "afterTextChanged" into editText
 */
fun EditText.onSearchTextChanged(searchTextChanged: (String) -> Unit){
    this.addTextChangedListener (object: TextWatcher{
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }

        override fun afterTextChanged(s: Editable?) {
            searchTextChanged.invoke(s.toString())
        }

    })
}