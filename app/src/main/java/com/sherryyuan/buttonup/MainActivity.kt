package com.sherryyuan.buttonup

import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.annotation.IdRes
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.sherryyuan.buttonup.archives.ArchivesListFragment
import com.sherryyuan.buttonup.drafts.draftslist.DraftsListFragment
import com.sherryyuan.buttonup.drafts.writedraft.WriteDraftContract
import com.sherryyuan.buttonup.drafts.writedraft.WriteDraftFragment
import com.sherryyuan.buttonup.subscribers.SubscribersFragment

class MainActivity : WriteDraftContract.DismissListener, FragmentActivity() {

    @IdRes
    private val fragmentContainerId: Int = R.id.fragment_container
    private lateinit var floatingActionButton: FloatingActionButton

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
                replaceFragment(ArchivesListFragment())
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupBottomNavigation()
        floatingActionButton = findViewById(R.id.fab)
        setupFloatingActionButton()
    }

    override fun onWriteDraftDismissed() {
        // Hide keyboard.
        val inputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        if (inputMethodManager.isAcceptingText) {
            inputMethodManager.hideSoftInputFromWindow(this.currentFocus?.windowToken, /*flags:*/ 0)
        }
        floatingActionButton.isVisible = true
    }

    private fun setupBottomNavigation() {
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
        navView.selectedItemId = R.id.navigation_drafts
    }

    private fun setupFloatingActionButton() {
        floatingActionButton.setOnClickListener {
            supportFragmentManager
                .beginTransaction()
                .add(fragmentContainerId, WriteDraftFragment(this))
                .addToBackStack(null)
                .commit()
            floatingActionButton.isVisible = false
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
