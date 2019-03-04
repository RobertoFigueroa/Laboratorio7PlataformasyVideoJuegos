package com.example.rober.lab7

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add_contact.*

class AddContactActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_ID = "com.example.rober.lab7.EXTRA_ID"
        const val EXTRA_TITLE = "com.example.rober.lab7EXTRA_TITLE"
        const val EXTRA_DESCRIPTION = "com.example.rober.lab7.EXTRA_DESCRIPTION"
        const val EXTRA_PRIORITY = "com.example.rober.lab7.EXTRA_PRIORITY"
        const val EXTRA_EMAIL = "com.example.rober.lab7.EXTRA_EMAIL"

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_contact)


        number_picker_priority.minValue = 1
        number_picker_priority.maxValue = 10

        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_close)

        if (intent.hasExtra(EXTRA_ID)) {
            title = "Edit Note"
            edit_text_name.setText(intent.getStringExtra(EXTRA_TITLE))
            edit_text_phone.setText(intent.getStringExtra(EXTRA_DESCRIPTION))
            edit_text_email.setText(intent.getStringExtra(EXTRA_EMAIL))
            number_picker_priority.value = intent.getIntExtra(EXTRA_PRIORITY, 1)
        } else {
            title = "Add Note"
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.add_contact_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.save_contact -> {
                saveNote()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun saveNote() {
        if (edit_text_name.text.toString().trim().isBlank() || edit_text_phone.text.toString().trim().isBlank() || edit_text_email.text.toString().trim().isBlank()) {
            Toast.makeText(this, "Porfavor llene todos los campos!", Toast.LENGTH_SHORT).show()
            return
        }

        val data = Intent().apply {
            putExtra(EXTRA_TITLE, edit_text_name.text.toString())
            putExtra(EXTRA_DESCRIPTION, edit_text_phone.text.toString())
            putExtra(EXTRA_EMAIL, edit_text_email.text.toString())
            putExtra(EXTRA_PRIORITY, number_picker_priority.value)
            if (intent.getIntExtra(EXTRA_ID, -1) != -1) {
                putExtra(EXTRA_ID, intent.getIntExtra(EXTRA_ID, -1))
            }
        }

        setResult(Activity.RESULT_OK, data)
        finish()
    }


}
