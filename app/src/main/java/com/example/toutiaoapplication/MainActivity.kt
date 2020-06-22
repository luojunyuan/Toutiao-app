package com.example.toutiaoapplication

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.*
import com.example.toutiaoapplication.utils.URL
import com.example.toutiaoapplication.utils.getPortSP
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController
    private lateinit var menuItem: MenuItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        instance = this

        URL = getPortSP(this)!!

        // if(isAlreadyLogged(this)){
            // may need
            // activeLoginStatus()
        // }

        initControl()
    }

    /**
     * 初始化底部，侧边相关的组件
     */
    private fun initControl() {
        navController = findNavController(R.id.main_nav_host) //Initialising navController

        appBarConfiguration = AppBarConfiguration.Builder(R.id.homeFragment, R.id.accountsFragment,
            R.id.sideFragment, R.id.settingFragment, R.id.accountsFragment) //Pass the ids of fragments from nav_graph which you d'ont want to show back button in toolbar
            .setDrawerLayout(main_drawer_layout) //Pass the drawer layout id from activity xml
            .build()

        setSupportActionBar(main_toolbar) //Set toolbar

        setupActionBarWithNavController(navController, appBarConfiguration) //Setup toolbar with back button and drawer icon according to appBarConfiguration

        visibilityNavElements(navController) //If you want to hide drawer or bottom navigation configure that in this function
    }

    private fun visibilityNavElements(navController: NavController) {

        //Listen for the change in fragment (navigation) and hide or show drawer or bottom navigation accordingly if required
        //Modify this according to your need
        //If you want you can implement logic to deselect(styling) the bottom navigation menu item when drawer item selected using listener

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.settingFragment -> hideBottomNavigation()
                else -> showBothNavigation()
            }
        }
    }

    private fun hideBothNavigation() { //Hide both drawer and bottom navigation bar
        main_bottom_navigation_view?.visibility = View.GONE
        main_navigation_view?.visibility = View.GONE
        main_drawer_layout?.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED) //To lock navigation drawer so that it don't respond to swipe gesture
    }

    private fun hideBottomNavigation() { //Hide bottom navigation
        main_bottom_navigation_view?.visibility = View.GONE
        main_navigation_view?.visibility = View.VISIBLE
        main_drawer_layout?.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED) //To unlock navigation drawer

        main_navigation_view?.setupWithNavController(navController) //Setup Drawer navigation with navController
    }

    private fun showBothNavigation() {
        main_bottom_navigation_view?.visibility = View.VISIBLE
        main_navigation_view?.visibility = View.VISIBLE
        main_drawer_layout?.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
        setupNavControl() //To configure navController with drawer and bottom navigation
    }

    private fun setupNavControl() {
        main_navigation_view?.setupWithNavController(navController) //Setup Drawer navigation with navController
        main_bottom_navigation_view?.setupWithNavController(navController) //Setup Bottom navigation with navController
    }

    fun exitApp() { //To exit the application call this function from fragment
        this.finishAffinity()
    }

    // 侧边导航栏的展开按钮function
    override fun onSupportNavigateUp(): Boolean { //Setup appBarConfiguration for back arrow
        return NavigationUI.navigateUp(navController, appBarConfiguration)
    }

    override fun onBackPressed() {
        when { //If drawer layout is open close that on back pressed
            main_drawer_layout.isDrawerOpen(GravityCompat.START) -> {
                main_drawer_layout.closeDrawer(GravityCompat.START)
            }
            else -> {
                super.onBackPressed() //If drawer is already in closed condition then go back
            }
        }
    }

    companion object {
        const val TAG = "MainActivity"
        var instance: MainActivity by Delegates.notNull()
    }
}