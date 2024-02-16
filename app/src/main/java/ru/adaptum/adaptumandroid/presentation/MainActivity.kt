package ru.adaptum.adaptumandroid.presentation

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import ru.adaptum.adaptumandroid.AdaptumApp
import ru.adaptum.adaptumandroid.R
import ru.adaptum.adaptumandroid.presentation.common.ToolbarVisibilityListener
import ru.adaptum.adaptumandroid.presentation.viewModels.MainActivityViewModel
import javax.inject.Inject

class MainActivity : AppCompatActivity(), ToolbarVisibilityListener {
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var actionBarDrawerToggle: ActionBarDrawerToggle

    @Inject
    lateinit var mainActivityViewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as AdaptumApp).appComponent.inject(this)
        setContentView(R.layout.activity_main)
        initBaseToolbar()
        bindViewModel()
    }

    override fun onStart() {
        super.onStart()
//        mainActivityViewModel.checkUserAuthorized()
//        mainActivityViewModel.getProfileData()
    }

    private fun initBaseToolbar() {
        drawerLayout = findViewById(R.id.my_drawer_layout)
        actionBarDrawerToggle =
            ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close)
        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        findViewById<NavigationView>(R.id.nav_view).setNavigationItemSelectedListener {
//            when (it.itemId) {
//                R.id.nav_profile -> {
//                    openProfileFragment()
//                }
//
//                R.id.nav_my_plans -> {
//                    openPlansFragment()
//                }
//
//                R.id.nav_events -> {
//                    openEventsFragment()
//                }
//            }
//            drawerLayout.closeDrawer(GravityCompat.START)
            true
        }
    }

//    private fun initToolbarInfo(profileDataUI: ProfileDataUI) {
//        val navigationView = findViewById<View>(R.id.nav_view) as NavigationView
//        val headerView = navigationView.getHeaderView(0)
//        val navHeaderTextView = headerView.findViewById<View>(R.id.name_tv) as TextView
//        navHeaderTextView.text = profileDataUI.name
//        Glide.with(this).load(profileDataUI.avatarUrl)
//            .into(headerView.findViewById(R.id.avatar))
//    }

    private fun bindViewModel() {
//        collectFlow(mainActivityViewModel.isAuthorizedState) {
//            if (it) {
//                openPlansFragment()
//            } else {
//                openLoginFragment()
//            }
//        }
//        collectFlow(mainActivityViewModel.profileDataState) {
//            it?.let { initToolbarInfo(it) }
//        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true
        } else {
            super.onOptionsItemSelected(item)
        }
    }

//    private fun openPlansFragment() {
//        Navigator.navigateReplace(AdaptPlansFragment(), supportFragmentManager)
//    }
//
//    private fun openLoginFragment() {
//        Navigator.navigateReplace(LoginFragment(), supportFragmentManager)
//    }
//
//    private fun openProfileFragment() {
//        Navigator.navigateReplace(ProfileFragment(), supportFragmentManager)
//    }
//
//    private fun openEventsFragment() {
//        Navigator.navigateReplace(EventsFragment(), supportFragmentManager)
//    }

    override fun showToolbar() {
        supportActionBar?.show()
    }

    override fun hideToolbar() {
        supportActionBar?.hide()
    }
}
