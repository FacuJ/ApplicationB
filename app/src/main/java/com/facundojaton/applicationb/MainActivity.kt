package com.facundojaton.applicationb

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.facundojaton.applicationb.adapters.NotificationsListAdapter
import com.facundojaton.applicationb.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var adapter = NotificationsListAdapter()
    private lateinit var dataRetrievingRegister: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dataRetrievingRegister = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                result.data?.let { handleCustomData(it) }
            } else {
                dataNotRetrievedError()
            }
        }

        binding.rvNotifications.adapter = adapter
        if (intent?.action == "com.facundojaton.sharedata" && intent.type == "*/*") {
            handleCustomData(intent)
        }
        binding.btnRetrieveData.setOnClickListener {
            retrieveDataFromA()
        }
    }

    private fun dataNotRetrievedError() {
        Toast.makeText(this, getString(R.string.data_not_retrieved), Toast.LENGTH_LONG).show()
    }

    private fun retrieveDataFromA() {
        val getDataIntent: Intent = Intent().apply {
            action = "com.facundojaton.getdata"
            type = "*/*"
        }
        try {
            dataRetrievingRegister.launch(getDataIntent)
        } catch (exception: Exception) {
        }
    }

    private fun handleCustomData(intent: Intent) {
        val data = intent.extras?.get("Object") as? ArrayList<String>
        if (data != null) {
            adapter.setNotifications(data)
        }
    }

    override fun onDestroy() {
        dataRetrievingRegister.unregister()
        super.onDestroy()
    }
}