package com.example.rober.lab7

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class EditContact : AppCompatActivity() {
    companion object {
        const val EXTRA_ID = "com.example.rober.lab7.EXTRA_ID"
        const val EXTRA_TITLE = "com.example.rober.lab7EXTRA_TITLE"
        const val EXTRA_DESCRIPTION = "com.example.rober.lab7.EXTRA_DESCRIPTION"
        const val EXTRA_PRIORITY = "com.example.rober.lab7.EXTRA_PRIORITY"
        const val EXTRA_EMAIL = "com.example.rober.lab7.EXTRA_EMAIL"

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_contact)

        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_close)



    }
}
