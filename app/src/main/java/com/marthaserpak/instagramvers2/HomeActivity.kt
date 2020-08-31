package com.marthaserpak.instagramvers2

import android.os.Bundle
import android.util.Log
import com.google.firebase.auth.FirebaseAuth

class HomeActivity : BaseActivity(0) {
    private val TAG = "HomeActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        Log.d(TAG, "onCreate")
        bottomNavigation()

        val auth = FirebaseAuth.getInstance()
        auth.signInWithEmailAndPassword("m.v.serpak@gmail.com", "password")
            .addOnCompleteListener{
                if (it.isSuccessful) {
                    Log.i(TAG,  "auth: successful")
                } else {
                    Log.i(TAG, "auth: failure", it.exception)
                }
            }
    }
}