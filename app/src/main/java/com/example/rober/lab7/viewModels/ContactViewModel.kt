package com.example.rober.lab7.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.rober.lab7.Data.Contact
import com.example.rober.lab7.Data.ContactRepository

class ContactViewModel(application : Application) : AndroidViewModel(application) {

    private var repository: ContactRepository = ContactRepository(application)
    private var allNotes: LiveData<List<Contact>> = repository.getAllNotes()

    fun insert(contact: Contact) {
        repository.insert(contact)
    }

    fun update(contact: Contact) {
        repository.update(contact)
    }

    fun delete(contact: Contact) {
        repository.delete(contact)
    }

    fun deleteAllNotes() {
        repository.deleteAllNotes()
    }

    fun getAllNotes(): LiveData<List<Contact>> {
        return allNotes
    }

}