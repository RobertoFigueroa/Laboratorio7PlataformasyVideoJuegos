package com.example.rober.lab7

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_add_contact.*
import kotlinx.android.synthetic.main.activity_view_contact.*

class ViewContact : AppCompatActivity() {
    companion object {
        const val EXTRA_ID = "com.example.rober.lab7.EXTRA_ID"
        const val EXTRA_TITLE = "com.example.rober.lab7EXTRA_TITLE"
        const val EXTRA_DESCRIPTION = "com.example.rober.lab7.EXTRA_DESCRIPTION"
        const val EXTRA_PRIORITY = "com.example.rober.lab7.EXTRA_PRIORITY"
        const val EXTRA_EMAIL = "com.example.rober.lab7.EXTRA_EMAIL"

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_contact)


        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_close)

        textView.text = intent.getStringExtra(AddContactActivity.EXTRA_TITLE)
        text_phone.text = intent.getStringExtra(AddContactActivity.EXTRA_DESCRIPTION)
        text_view_email.text = (intent.getStringExtra(AddContactActivity.EXTRA_EMAIL))


    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.edit_contact, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.edit_contact -> {
                val intent = Intent(this,AddContactActivity::class.java)

                intent.putExtra(AddContactActivity.EXTRA_ID,AddContactActivity.EXTRA_ID)
                intent.putExtra(AddContactActivity.EXTRA_TITLE, textView.text.toString())
                intent.putExtra(AddContactActivity.EXTRA_DESCRIPTION, text_phone.text.toString())
                intent.putExtra(AddContactActivity.EXTRA_EMAIL, text_view_email.text.toString())
                intent.putExtra(AddContactActivity.EXTRA_PRIORITY, AddContactActivity.EXTRA_PRIORITY)
                startActivityForResult(intent, 2)
                finish()
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }
}
