package com.heyaiz.heymov.utils

import android.content.Context
import android.content.SharedPreferences

class Preferences(val context: Context) {
    companion object {
        const val USER_REFF = "USER_REFF"
    }

    var sharedPreferences = context.getSharedPreferences(USER_REFF, 0)

    fun setValues(key: String, value: String){
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun getValues (key: String) : String? {
        return sharedPreferences.getString(key, "")
    }
}