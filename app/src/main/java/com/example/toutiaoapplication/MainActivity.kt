package com.example.toutiaoapplication

import android.os.Bundle
import android.view.Menu
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // @layout.app_bar_main.xml 默认使用 CoordinatorLayout
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        toolbar.inflateMenu(R.menu.menu_activity_main)
        setSupportActionBar(toolbar)

        // in activity_main
        // activity_main 就是一个DrawerLayout
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        // drawer layout 中的导航栏
        val navView: NavigationView = findViewById(R.id.nav_view)
        // host fragment
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        // 设置侧边drawer导航栏
        appBarConfiguration = AppBarConfiguration(setOf(
                R.id.nav_home), drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController) // MVC view.setController(control)
    }

    // 侧边导航栏的展开按钮function
    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}