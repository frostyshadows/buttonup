package com.sherryyuan.buttonup

import android.os.Bundle
import android.view.View
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.sherryyuan.buttonup.drafts.draftslist.DraftsListFragment
import com.sherryyuan.buttonup.drafts.writedraft.WriteDraftFragment
import com.sherryyuan.buttonup.subscribers.SubscribersFragment

class MainActivity : FragmentActivity() {

    @IdRes
    private val fragmentContainerId: Int = R.id.fragment_container

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_drafts -> {
                replaceFragment(DraftsListFragment())
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

        setupBottomNavigation()
        setupFloatingActionButton()
    }

    private fun setupBottomNavigation() {
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
        navView.selectedItemId = R.id.navigation_drafts
    }

    private fun setupFloatingActionButton() {
        val floatingActionButton: View = findViewById(R.id.fab)
        floatingActionButton.setOnClickListener {
            replaceFragment(WriteDraftFragment())
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(fragmentContainerId, fragment)
            .addToBackStack(null)
            .commit()
    }
}
