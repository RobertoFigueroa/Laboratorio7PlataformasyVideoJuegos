package com.example.rober.lab7.Data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contact_table")
class Contact(


    var name : String,
    var phone : String,
    var email : String,
    var priority : Int

) {

    @PrimaryKey(autoGenerate = true)
    var id : Int = 0

    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    private val image: ByteArray? = null

}