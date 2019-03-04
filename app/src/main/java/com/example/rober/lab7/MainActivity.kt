package com.example.rober.lab7

import android.app.Activity
import android.app.Application
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rober.lab7.Data.Contact
import com.example.rober.lab7.MainActivity.Companion.ADD_NOTE_REQUEST
import com.example.rober.lab7.MainActivity.Companion.EDIT_NOTE_REQUEST
import com.example.rober.lab7.adapters.ContactAdapter
import com.example.rober.lab7.viewModels.ContactViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        const val ADD_NOTE_REQUEST = 1
        const val EDIT_NOTE_REQUEST = 2
    }


    private lateinit var contactViewModel: ContactViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val botonAgregarContacto : FloatingActionButton = findViewById(R.id.button_add_contact)
        botonAgregarContacto.setOnClickListener{
            startActivityForResult(
                Intent(this, AddContactActivity::class.java),
                ADD_NOTE_REQUEST)
        }


        val recyclerView : RecyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        var adapter = ContactAdapter()
        recyclerView.adapter = adapter

        contactViewModel = ViewModelProviders.of(this).get(ContactViewModel::class.java)
        contactViewModel.getAllNotes().observe(this, Observer<List<Contact>>  {

            adapter.submitList(it)

        })
        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT.or(ItemTouchHelper.RIGHT)) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
               contactViewModel.delete(adapter.getContactAt(viewHolder.adapterPosition))
                Toast.makeText(baseContext, "Se ha eliminado el contacto", Toast.LENGTH_SHORT).show()
            }
        }
        ).attachToRecyclerView(recycler_view)

        adapter.setOnItemClickListener(object : ContactAdapter.OnItemClickListener {
            override fun onItemClick(contact: Contact) {
                var intent = Intent(baseContext, ViewContact::class.java)
                intent.putExtra(ViewContact.EXTRA_ID, contact.id)
                intent.putExtra(ViewContact.EXTRA_TITLE, contact.name)
                intent.putExtra(ViewContact.EXTRA_DESCRIPTION, contact.phone)
                intent.putExtra(ViewContact.EXTRA_EMAIL, contact.email)
                intent.putExtra(ViewContact.EXTRA_PRIORITY, contact.priority)

                startActivity(intent)
            }
        })


        }




    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == ADD_NOTE_REQUEST && resultCode == Activity.RESULT_OK){
            val newContact= Contact(
                data!!.getStringExtra(AddContactActivity.EXTRA_TITLE),
                data.getStringExtra(AddContactActivity.EXTRA_DESCRIPTION),
                data.getStringExtra(AddContactActivity.EXTRA_EMAIL),
                data.getIntExtra(AddContactActivity.EXTRA_PRIORITY, 1)
            )
            contactViewModel.insert(newContact)
            Toast.makeText(this, "Se ha guardado el contacto exitosamente", Toast.LENGTH_SHORT).show()
        }
        else if (requestCode == EDIT_NOTE_REQUEST && resultCode == Activity.RESULT_OK) {
            val id = data?.getIntExtra(AddContactActivity.EXTRA_ID, -1)

            if(id == -1){
                Toast.makeText(this, "Error, no se puedo actualizar la lista", Toast.LENGTH_SHORT).show()
            }
            val updateContact = Contact(
                data!!.getStringExtra(AddContactActivity.EXTRA_TITLE),
                data.getStringExtra(AddContactActivity.EXTRA_DESCRIPTION),
                data.getStringExtra(AddContactActivity.EXTRA_EMAIL),
                data.getIntExtra(AddContactActivity.EXTRA_PRIORITY, 1)
            )
            updateContact.id = data.getIntExtra(AddContactActivity.EXTRA_ID, -1)
            contactViewModel.update(updateContact)

        }
        else{
            Toast.makeText(this, "No se guardo el contacto!", Toast.LENGTH_SHORT).show()
        }

    }

    override  fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater : MenuInflater = menuInflater
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.delete_all_contacts -> {
                contactViewModel.deleteAllNotes()
                Toast.makeText(this, "Se han eliminado todos los contactos", Toast.LENGTH_SHORT).show()
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }
}
