package com.sherryyuan.buttonup

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.sherryyuan.buttonup.drafts.DraftsFragment
import com.sherryyuan.buttonup.subscribers.SubscribersFragment

class MainActivity : FragmentActivity() {

    @IdRes
    private val fragmentContainerId: Int = R.id.fragment_container

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_drafts -> {
                replaceFragment(DraftsFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_subscribers -> {
                replaceFragment(SubscribersFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_archives -> {
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
        navView.selectedItemId = R.id.navigation_drafts
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(fragmentContainerId, fragment)
            .addToBackStack(null)
            .commit()
    }
}
