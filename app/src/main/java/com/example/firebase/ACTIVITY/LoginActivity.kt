package com.example.firebase.ACTIVITY


import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.firebase.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class LoginActivity : AppCompatActivity() {


    companion object {
        const val SHARED_PREFS = "shared_prefs"
        const val EMAIL_KEY = "email_key"
        const val PASSWORD_KEY = "password_key"
    }

    lateinit var binding: ActivityLoginBinding
    lateinit var auth: FirebaseAuth
    private var email: String? = null
    private var password: String? = null

    lateinit var sharedpreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedpreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE)
        initview()
    }

    private fun initview() {


        email = sharedpreferences.getString("EMAIL_KEY", null)
        password = sharedpreferences.getString("PASSWORD_KEY", null)

        auth = Firebase.auth

        var email = binding.edtEmail.text.toString()
        var pass = binding.edtPassword.text.toString()


//        for crete Account
//
//        binding.btnCreteAccount.setOnClickListener {


//            auth.createUserWithEmailAndPassword(email, pass)
//                .addOnCompleteListener(this) { task ->
//                    if (task.isSuccessful) {
//
//                        Toast.makeText(this, "Account Create Succsefully", Toast.LENGTH_SHORT)
//                            .show()
//
//
//                    } else {
//
//                        Log.e("TAG", "initview: " + task)
//                        Toast.makeText(this, "Invalid Email & Password ", Toast.LENGTH_SHORT).show()
//                    }
//                }


//            for login
            binding.btnLogin.setOnClickListener {


                var email = binding.edtEmail.text.toString()
                var pass = binding.edtPassword.text.toString()
                auth.signInWithEmailAndPassword(email, pass)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {


                            Toast.makeText(this, "Login Succsefully", Toast.LENGTH_SHORT).show()

                            val editor = sharedpreferences.edit()

                            // below two lines will put values for
                            // email and password in shared preferences.
                            editor.putString(EMAIL_KEY, email.text.toString())
                            editor.putString(PASSWORD_KEY, pass.text.toString())

                            // to save our data with key and value.
                            editor.apply()
                            Log.e("TAG", "initview: " + task.exception.toString())
                            val i = Intent(this@LoginActivity, AddDataActivity::class.java)
                            startActivity(i)

                        } else {

                            Log.e("TAG", "initview: " + task.exception.toString())
                            Toast.makeText(this, "Invaild UserName & PassWord", Toast.LENGTH_SHORT)
                                .show()

                        }
                    }

            }
        }



    override fun onStart() {
        super.onStart()
        if (email != null && password != null) {
            val i = Intent(this@LoginActivity, AddDataActivity::class.java)
            startActivity(i)
        }

    }

    private val String.text: Any
        get() {
            var i: String
            return 0
        }


}
