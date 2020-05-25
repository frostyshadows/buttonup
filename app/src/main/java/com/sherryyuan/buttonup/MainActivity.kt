package com.sherryyuan.buttonup

import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.annotation.IdRes
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.sherryyuan.buttonup.databinding.ActivityMainBinding
import com.sherryyuan.buttonup.drafts.writedraft.WriteDraftContract
import com.sherryyuan.buttonup.drafts.writedraft.WriteDraftFragment

private const val TAG_WRITE_DRAFT = "tag_write_draft"

class MainActivity : FragmentActivity(), WriteDraftContract.DismissListener {

    @IdRes
    private val fragmentContainerId: Int = R.id.nav_host_fragment
    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navController = findNavController(R.id.nav_host_fragment)
        NavigationUI.setupWithNavController(binding.bottomNavView, navController)
        setupFloatingActionButton()
    }

    override fun onBackPressed() {
        val writeDraftFragment: Fragment? =
            supportFragmentManager.findFragmentByTag(TAG_WRITE_DRAFT)
        if (writeDraftFragment != null && writeDraftFragment.isVisible) {
            // If the fragment we're leaving is WriteDraftFragment,
            // make the floating action button reappear.
            onWriteDraftDismissed()
        }
        super.onBackPressed()
    }

    override fun onWriteDraftDismissed() {
        // Hide keyboard.
        val inputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        if (inputMethodManager.isAcceptingText) {
            inputMethodManager.hideSoftInputFromWindow(this.currentFocus?.windowToken, 0)
        }
        binding.floatingActionButton.isVisible = true
    }

    private fun setupFloatingActionButton() {
        binding.floatingActionButton.setOnClickListener {
            supportFragmentManager
                .beginTransaction()
                .add(fragmentContainerId, WriteDraftFragment(this), TAG_WRITE_DRAFT)
                .addToBackStack(null)
                .commit()
            binding.floatingActionButton.isVisible = false
        }
    }
}
