package com.example.rober.lab7.Data

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData

class ContactRepository(application : Application){

    private var contactDao: ContactDao

    private var allContacts: LiveData<List<Contact>>

    init {
        val database: ContactDatabase = ContactDatabase.getInstance(
            application.applicationContext
        )!!
        contactDao = database.contactDao()
        allContacts = contactDao.getAllContacts()
    }
    fun insert(contact: Contact) {
        val insertNoteAsyncTask = InsertNoteAsyncTask(contactDao).execute(contact)
    }

    fun update(contact: Contact) {
        val updateNoteAsyncTask = UpdateNoteAsyncTask(contactDao).execute(contact)
    }


    fun delete(contact: Contact) {
        val deleteNoteAsyncTask = DeleteNoteAsyncTask(contactDao).execute(contact)
    }

    fun deleteAllNotes() {
        val deleteAllNotesAsyncTask = DeleteAllNotesAsyncTask(
            contactDao
        ).execute()
    }

    fun getAllNotes(): LiveData<List<Contact>> {
        return allContacts
    }








    companion object {
        private class InsertNoteAsyncTask(noteDao: ContactDao) : AsyncTask<Contact, Unit, Unit>() {
            val noteDao = noteDao

            override fun doInBackground(vararg p0: Contact?) {
                noteDao.insert(p0[0]!!)
            }
        }

        private class UpdateNoteAsyncTask(noteDao: ContactDao) : AsyncTask<Contact, Unit, Unit>() {
            val noteDao = noteDao

            override fun doInBackground(vararg p0: Contact?) {
                noteDao.update(p0[0]!!)
            }
        }

        private class DeleteNoteAsyncTask(noteDao: ContactDao) : AsyncTask<Contact, Unit, Unit>() {
            val noteDao = noteDao

            override fun doInBackground(vararg p0: Contact?) {
                noteDao.delete(p0[0]!!)
            }
        }

        private class DeleteAllNotesAsyncTask(noteDao: ContactDao) : AsyncTask<Unit, Unit, Unit>() {
            val noteDao = noteDao

            override fun doInBackground(vararg p0: Unit?) {
                noteDao.deleteAllContact()
            }
        }
    }
}

