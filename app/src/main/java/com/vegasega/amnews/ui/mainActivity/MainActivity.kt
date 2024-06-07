package com.vegasega.amnews.ui.mainActivity

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.core.view.isVisible
import androidx.navigation.NavOptions
import androidx.navigation.fragment.NavHostFragment
import androidx.room.Room
import com.vegasega.amnews.R
import com.vegasega.amnews.databinding.MainActivityBinding
import com.vegasega.amnews.datastore.db.AppDatabase
import com.vegasega.amnews.networking.ConnectivityManager
import com.vegasega.amnews.networking.Home
import com.vegasega.amnews.networking.OnboardLanguage
import com.vegasega.amnews.networking.Screen
import com.vegasega.amnews.utils.LocaleHelper
import com.vegasega.amnews.utils.determineScreenDensityCode
import com.vegasega.amnews.utils.getDensityName
import com.vegasega.amnews.utils.showSnackBar
import dagger.hilt.android.AndroidEntryPoint
import java.lang.ref.WeakReference

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    companion object {
        @JvmStatic
        var scale10: Int = 0

        @JvmStatic
        var fontSize: Float = 0f

        @JvmStatic
        lateinit var context: WeakReference<Context>

        @JvmStatic
        lateinit var activity: WeakReference<Activity>

        @JvmStatic
        lateinit var mainActivity: WeakReference<MainActivity>

        @SuppressLint("StaticFieldLeak")
        var navHostFragment: NavHostFragment? = null

        private var _binding: MainActivityBinding? = null
        val binding get() = _binding!!

        @JvmStatic
        var networkFailed: Boolean = false

        @JvmStatic
        var db : AppDatabase?= null

    }


    private val viewModel: MainActivityVM by viewModels()

    private val connectivityManager by lazy { ConnectivityManager(this) }

    private val recordAUDIOPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { granted ->
        if (granted) {
//            Log.e("TAG", "AAAAgranted " + granted)

        } else {
//            Log.e("TAG", "BBBBgranted " + granted)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        context = WeakReference(this)
        activity = WeakReference(this)
        mainActivity = WeakReference(this)

        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "music-name"
        ).build()

        observeConnectivityManager()
        recordAUDIOPermissionLauncher.launch(android.Manifest.permission.RECORD_AUDIO)


        val sss = determineScreenDensityCode()
        Log.e("TAG", "sss " + sss)

        val ddd = getDensityName()
        Log.e("TAG", "ddd " + ddd)



        val bundle = intent?.extras
        if (bundle != null) {
            showData(bundle)
        }

    }

    public override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        if (intent?.extras != null) {
            showData(intent?.extras!!)
        }
    }



    private fun showData(bundle: Bundle) {
        try {
            val navOptions: NavOptions = NavOptions.Builder()
                .setPopUpTo(R.id.navigation_bar, true)
                .build()
            if (intent!!.hasExtra(Screen)) {
                val screen = intent.getStringExtra(Screen)
//                Log.e("TAG", "screenAA " + screen)
//                if (screen == Home) {
//                  //  binding.topLayout.topToolbar.visibility = View.VISIBLE
//                }
                if (screen == OnboardLanguage) {
                    navHostFragment?.navController?.navigate(R.id.walkThrough, null, navOptions)
                } else if (screen == Home) {
                    if (bundle?.getString("key") != null) {
                       // callRedirect(bundle)
                    } else {
//                        Log.e("key", "showDataBB ")
//                        Log.e("_id", "showDataBB ")
                        navHostFragment?.navController?.navigate(R.id.home, null, navOptions)
                    }
                }
            } else {
//                Log.e("TAG", "screenBB ")
                if (bundle?.getString("key") != null) {
                    //callRedirect(bundle)
                }
            }
        } catch (e: Exception) {
        }
    }


    private fun observeConnectivityManager() = try {
        connectivityManager.observe(this) {
            binding.tvInternet.isVisible = !it
            networkFailed = it
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }





    override fun onStart() {
        super.onStart()
        val fontScale = resources.configuration.fontScale
        scale10 = when (fontScale) {
            0.7f -> 14
            0.8f -> 13
            0.9f -> 12
            1.0f -> 11
            1.1f -> 10
            1.2f -> 9
            1.3f -> 8
            1.5f -> 7
            1.7f -> 6
            2.0f -> 5
            else -> 4
        }

        val densityDpi = getDensityName()
//        Log.e("TAG", "densityDpiAA " + densityDpi)
        fontSize = when (densityDpi) {
            "xxxhdpi" -> 9f
            "xxhdpi" -> 9.5f
            "xhdpi" -> 10.5f
            "hdpi" -> 10.5f
            "mdpi" -> 11f
            "ldpi" -> 11.5f
            else -> 12f
        }

      //  showSnackBar("Font Scale: $fontSize")
    }




    fun reloadActivity(language: String, screen: String) {
        LocaleHelper.setLocale(this, language)
        val refresh = Intent(Intent(this, MainActivity::class.java))
        refresh.putExtra(Screen, screen)
        refresh.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        finish()
        finishAffinity()
        startActivity(refresh)
    }


    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(LocaleHelper.onAttach(newBase, ""))
    }
}

