package com.example.toutiaoapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.toutiaoapplication.ui.home.HomeFragment
import com.example.toutiaoapplication.ui.mine.MineActivity
import com.example.toutiaoapplication.ui.mine.MineFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity() {

    companion object {
        const val TAG = "MainActivity"
        const val FRAGMENT_NEWS = 0
        const val FRAGMENT_MINE = 1
    }

    private lateinit var appBarConfiguration: AppBarConfiguration
    private var position by Delegates.notNull<Int>()
    private lateinit var toolbar: Toolbar
    private var homeTableLayout: HomeFragment = HomeFragment().getInstance()
    private var mineTableLayout: MineFragment = MineFragment().getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initControl()
    }

    /**
     * 创建toolbar，bottomNavigation，drawer侧边栏导航控件
     */
    private fun initControl() {
        // @layout.app_bar_main.xml 默认使用 CoordinatorLayout
        toolbar = findViewById(R.id.toolbar)
        toolbar.inflateMenu(R.menu.menu_activity_main)
        setSupportActionBar(toolbar)

        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigation.setOnNavigationItemReselectedListener {
            when (it.itemId) {
                // FIXME 需要按两下才能进入那个页面
                R.id.action_mine -> startActivity(Intent(this, MineActivity::class.java))
            }
        }

        // 以下drawer组件 in activity_main
        // activity_main 就是一个DrawerLayout
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        // drawer layout 中的导航栏
        val navView: NavigationView = findViewById(R.id.nav_view)
        // host fragment
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        // 设置侧边drawer导航栏
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_setting, R.id.nav_mine
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController) // MVC view.setController(control)
    }

    // 侧边导航栏的展开按钮function
    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}