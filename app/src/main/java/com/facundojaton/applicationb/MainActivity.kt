package com.facundojaton.applicationb

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.facundojaton.applicationb.adapters.NotificationsListAdapter
import com.facundojaton.applicationb.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var adapter = NotificationsListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.rvNotifications.adapter = adapter
        if (intent?.action == "com.facundojaton.sharedata" && intent.type == "*/*") {
            handleCustomData(intent)
        }
    }

    private fun handleCustomData(intent: Intent) {
        val data = intent.extras?.get("Object") as? ArrayList<String>
        if (data != null) {
            adapter.setNotifications(data)
        }
    }
}