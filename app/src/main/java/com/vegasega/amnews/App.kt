package com.vegasega.amnews

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import androidx.room.Room
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.vegasega.amnews.datastore.DataStoreUtil
import com.vegasega.amnews.datastore.db.AppDatabase
import com.vegasega.amnews.ui.DailyWorker
import dagger.hilt.android.HiltAndroidApp
import java.util.concurrent.TimeUnit


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

    val myWork = PeriodicWorkRequestBuilder<DailyWorker>(
        1, TimeUnit.HOURS)
        .build()
    WorkManager.getInstance(this).enqueueUniquePeriodicWork(
        "my_work",
    ExistingPeriodicWorkPolicy.KEEP,
    myWork)
    }
}