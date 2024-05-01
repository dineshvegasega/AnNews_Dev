package com.vegasega.amnews

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.vegasega.amnews.datastore.DataStoreUtil
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class App : Application() {
    companion object{
       // var scale10: Int = 0
    }
//   lateinit var mFirebaseRemoteConfig: FirebaseRemoteConfig
    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        DataStoreUtil.initDataStore(applicationContext)
    }
}