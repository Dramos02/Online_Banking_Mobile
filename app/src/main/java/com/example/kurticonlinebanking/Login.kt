package com.example.kurticonlinebanking

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.example.kurticonlinebanking.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class Login : AppCompatActivity() {

    /* ViewBinding */
    private lateinit var LoginBinding: ActivityLoginBinding

    /* Firebase Authentication */
    private lateinit var mAuth: FirebaseAuth

    /* UI Elements Variables */
    private lateinit var emailAddr: EditText
    private lateinit var password: EditText

    private lateinit var rememberAcc: CheckBox

    private lateinit var loginBtn: CardView

    private lateinit var registerRedirect: TextView

    private lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LoginBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(LoginBinding.root)

        /* Retrieves an instance of Firebase Authentication from the app, if it's available */
        mAuth = FirebaseAuth.getInstance()

        /* Setting UI Elements as variables to be accessed programmatically */
        emailAddr = LoginBinding.LoginEmailText
        password = LoginBinding.LoginPasswordText
        rememberAcc = LoginBinding.rememberAccBox
        loginBtn = LoginBinding.LoginButton

        loginBtn.setOnClickListener {
            if(!isFinishing) { // Prevents the code from executing after the activity is finished/closed

            }
        }

        registerRedirect = LoginBinding.registerRedirect
        registerRedirect.setOnClickListener {
            /* Insert register intent here */
            finish()
        }
    }

    /* Overrides the onStart() Function */
    override fun onStart() {
        // Calls what is originally done in the onStart() Function
        super.onStart()
        // Retrieves current user from Firebase auth, if there is any
        val currentUser: FirebaseUser? = mAuth.currentUser
        // Redirects users to Main Activity if there is an account found.
        if(currentUser != null) {
            startActivity(Intent(applicationContext, MainActivity::class.java))
            finish()
        }
    }

    /* Overrides Back key function, redirects to Main Activity rather than close the app */
    override fun onKeyDown(keyCode: Int, event: KeyEvent?) : Boolean {
        if(keyCode == KeyEvent.KEYCODE_BACK) {
            startActivity(Intent(applicationContext, MainActivity::class.java))
            finish()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }
}