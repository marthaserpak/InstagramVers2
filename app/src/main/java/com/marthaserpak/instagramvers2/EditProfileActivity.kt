package com.marthaserpak.instagramvers2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_edit_profile.*
import kotlinx.android.synthetic.main.activity_login.*
import models.User

class EditProfileActivity : AppCompatActivity() {
    private val TAG = "EditProfileActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)
        Log.d(TAG, "onCreate")

        close_image.setOnClickListener {
            finish()
        }

        val auth = FirebaseAuth.getInstance()
        val user = auth.currentUser
        val database = FirebaseDatabase.getInstance().reference
        database.child("users").child(user!!.uid)
            .addListenerForSingleValueEvent(object: ValueEventListener {
            override fun onDataChange(data: DataSnapshot) {
                //Конвертирует полученые данные в класс User
                val user1 = data.getValue(User::class.java)
                name_input.setText(user1!!.name, TextView.BufferType.EDITABLE)
                username_input.setText(user1.username, TextView.BufferType.EDITABLE)
                website_input.setText(user1.website, TextView.BufferType.EDITABLE)
                bio_input.setText(user1.bio, TextView.BufferType.EDITABLE)
                privateInfo_input.setText(user1.privateInfo, TextView.BufferType.EDITABLE)
                email_label_input.setText(user1.email, TextView.BufferType.EDITABLE)
                phone_input.setText(user1.phone, TextView.BufferType.EDITABLE)
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e(TAG, "error", error.toException())
            }
        })


    }

}