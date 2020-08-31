package com.marthaserpak.instagramvers2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.google.firebase.auth.FirebaseAuth

class HomeActivity : BaseActivity(0) {
    private val TAG = "HomeActivity"
    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        Log.d(TAG, "onCreate")
        bottomNavigation()

        mAuth = FirebaseAuth.getInstance()
        mAuth.signOut()

        /*auth.signInWithEmailAndPassword("m.v.serpak@gmail.com", "password")
            .addOnCompleteListener{
                if (it.isSuccessful) {
                    Log.i(TAG,  "auth: successful")
                } else {
                    Log.i(TAG, "auth: failure", it.exception)
                }
            }*/
    }

    override fun onStart() {
        super.onStart()
        if (mAuth.currentUser == null) {
            startActivity(Intent(this, LoginActivity::class.java))

            /**если пользователь не зашел в акк, чтоб он не смог при нажатии
            кнопки "назад" вернуться в HomeActivity */
            finish()
        }
    }
}