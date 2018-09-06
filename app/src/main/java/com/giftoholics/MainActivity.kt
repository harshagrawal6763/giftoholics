package com.giftoholics

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.app.ActionBarDrawerToggle
import android.view.Menu
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main_tab.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        setUpNavigationView()
        setupViewPager(viewpager)
        tabs.setupWithViewPager(viewpager)
        invalidateOptionsMenu()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)


    }
    private fun setUpNavigationView() {
        //Setting Navigation View Item Selected Listener to handle the item click of the navigation menu
        nav_view.setNavigationItemSelectedListener({ menuItem ->

            menuItem.isChecked = !menuItem.isChecked
            menuItem.isChecked = true



            true
        })

        var actionBarDrawerToggle=object :ActionBarDrawerToggle(this,drawer_layout, toolbar, R.string.openDrawer, R.string.closeDrawer){

        }

        //Setting the actionbarToggle to drawer layout
        drawer_layout.setDrawerListener(actionBarDrawerToggle)

        //calling sync state is necessary or else your hamburger icon wont show up
        actionBarDrawerToggle.syncState()
    }

    private fun setupViewPager(viewPager: ViewPager) {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFrag(CategoryFragment(), "Category")

        viewPager.adapter = adapter
    }


}
