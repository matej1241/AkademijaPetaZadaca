package hr.ferit.brunozoric.taskie.persistence

import android.preference.PreferenceManager
import hr.ferit.brunozoric.taskie.Taskie

object PriorityPrefs {

    const val KEY_PRIORITY = "KEY_PRIORITY"

    private fun sharedPrefs() = PreferenceManager.getDefaultSharedPreferences(Taskie.instance)

    fun store(key: String, value: String){
        sharedPrefs().edit().putString(key, value).apply()
    }

    fun getPrefPriority(key: String, defaultValue: String): String?{
        return sharedPrefs().getString(key, defaultValue)
    }
}