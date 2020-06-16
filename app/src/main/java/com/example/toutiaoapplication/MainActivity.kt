package com.example.toutiaoapplication

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.toutiaoapplication.ui.home.HomeFragment
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
        bottomNavigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.action_news -> showFragment(FRAGMENT_NEWS)
                R.id.action_media -> showFragment(FRAGMENT_MINE)
            }
            true
        }

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
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController) // MVC view.setController(control)
    }

    private fun showFragment(index: Int) {
        val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
//        hideFragment(ft)
        position = index
        when (index) {
            FRAGMENT_NEWS -> {
                toolbar.setTitle(R.string.app_name)
                /**
                 * 如果Fragment为空，就新建一个实例
                 * 如果不为空，就将它从栈中显示出来
                 * never happen?
                 */
                if (homeTableLayout == null) {
                    Log.d(TAG, "This is happened")
                    homeTableLayout = homeTableLayout.getInstance()
                    //  android.R.id.content提供了视图的根元素，而不必知道它的实际名称/类型/ ID。
                    ft.add(R.id.content, homeTableLayout, homeTableLayout::class.java.name)
                } else {
                    ft.show(homeTableLayout)
                }
            }
            FRAGMENT_MINE -> {
//                toolbar.setTitle(getString(R.string.title_media))
//                if (mediaChannelView == null) {
//                    mediaChannelView = MediaChannelView.getInstance()
//                    ft.add(R.id.container, mediaChannelView, MediaChannelView::class.java.getName())
//                } else {
//                    ft.show(mediaChannelView)
//                }
            }
        }
        ft.commit()
    }

    // 侧边导航栏的展开按钮function
    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}