package com.example.lcvf.gardensmagiceye

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_login.*

/**
 * A login screen that offers login via email/password.
 */
class LoginActivity : AppCompatActivity() {
    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */
    private lateinit var databaseReference:DatabaseReference
    private lateinit var database:FirebaseDatabase
    private lateinit var auth:FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //set Instead FireBase
        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        databaseReference = database.reference.child("User")


        email_sign_in_button.setOnClickListener {
            loginUser()
        }
    }

    fun loginUser(){

        val email:String? = email.text.toString()
        val password:String?=password.text.toString()

        if(!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)){
            login_progress.visibility = View.VISIBLE
            auth.signInWithEmailAndPassword(email!!, password!!)
                    .addOnCompleteListener {
                        task ->
                        if(task.isSuccessful){
                            startActivity(Intent(applicationContext,MainActivity::class.java))
                        }else{
                            Toast.makeText(this, "Alguno de los parámetros son inválidos, favor intente nuevamente...", Toast.LENGTH_SHORT).show()
                        }
                        login_progress.visibility = View.GONE
                    }
        }else{
            Toast.makeText(this, "Los campos no pueden ir vacios...", Toast.LENGTH_SHORT).show()
        }



    }

}
