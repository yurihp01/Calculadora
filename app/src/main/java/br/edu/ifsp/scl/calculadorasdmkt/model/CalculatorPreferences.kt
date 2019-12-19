package br.edu.ifsp.scl.calculadorasdmkt.model

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences

object CalculatorPreferences {
    const val LAYOUT_KEY = "LAYOUT"
    const val SEPARATOR_TYPE_KEY = "SEPARATOR"

    private lateinit var sharedPreferences: SharedPreferences

    fun getPreferences(context: Context) : SharedPreferences {
            sharedPreferences =
                context.getSharedPreferences(context.packageName, Activity.MODE_PRIVATE)

        return sharedPreferences
    }

    fun write(key: String, value: String) {
        sharedPreferences.edit().putString(key, value).apply()
    }

    fun write(key: String, value: Boolean) {
        sharedPreferences.edit().putBoolean(key, value).apply()
    }

    fun readString(key: String) : String? {
        return sharedPreferences.getString(key, null)
    }

    fun readBoolean(key: String) : Boolean {
        return sharedPreferences.getBoolean(key, true)
    }


}