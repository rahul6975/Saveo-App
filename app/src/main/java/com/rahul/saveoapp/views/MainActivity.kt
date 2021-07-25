package com.rahul.saveoapp.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.rahul.saveoapp.R
import com.rahul.saveoapp.fragments.BaseFragment
import com.rahul.saveoapp.fragments.DetailFragment


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addFragment()

    }

    private fun addFragment() {
        val baseFragment = BaseFragment()
        /*
           This is the method used to send FragmentListener from Activity to LandingFragment
        */
        supportFragmentManager.beginTransaction()
            .add(R.id.fragment1, baseFragment, "baseFragment")
            .commit()
    }

    override fun onResume() {
        super.onResume()
        val intent = intent
        val message = intent.getStringExtra("message")
        if (message.equals("Kill me")) {
            val detailFragment = DetailFragment()
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment1, detailFragment, "baseFragment")
                .commit()
        }
    }


}