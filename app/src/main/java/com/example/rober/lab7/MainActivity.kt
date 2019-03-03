package com.example.rober.lab7

import android.os.Bundle
import android.widget.Toast
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.rober.lab7.Data.Contact
import com.example.rober.lab7.viewModels.ContactViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var contactViewModel: ContactViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        contactViewModel = ViewModelProviders.of(this).get(ContactViewModel::class.java)
        contactViewModel.getAllNotes().observe(this, Observer<List<Contact>>  {

            @Override
            fun onChange(@Nullable contacts : List<Contact>){
                //update Recycler View
                Toast.makeText(this,"onChanged",Toast.LENGTH_SHORT).show()
            }

        })

    }
}
