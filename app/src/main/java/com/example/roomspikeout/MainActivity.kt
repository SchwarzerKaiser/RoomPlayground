package com.example.roomspikeout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import kotlinx.coroutines.launch

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dao = Room
            .databaseBuilder(this, AppDb::class.java, "AppDb")
            .fallbackToDestructiveMigration()
            .build()
            .getPersonDao()

        lifecycleScope.launch {
            val owners = dao.getAllOwners()
            Log.d(TAG, owners.toString())
        }
    }
}