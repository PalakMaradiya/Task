package com.example.firebase.ACTIVITY


import android.R.attr.name
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
    lateinit var binding: ActivityLoginBinding
    lateinit var auth: FirebaseAuth
    lateinit var email : String
    lateinit var pass : String
    lateinit var sharePreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharePreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE)
        initview()
    }

    private fun initview() {


        auth = Firebase.auth

        var email = binding.edtEmail.text.toString()
        var pass = binding.edtPassword.text.toString()
//        for crete Account

        binding.btnCreteAccount.setOnClickListener {


            auth.createUserWithEmailAndPassword(email, pass)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {

                        Toast.makeText(this, "Account Create Succsefully", Toast.LENGTH_SHORT).show()


                    } else {

                        Log.e("TAG", "initview: " + task)
                        Toast.makeText(this, "Invalid Email & Password ", Toast.LENGTH_SHORT).show()
                    }
                }


//            for login
            binding.btnLogin.setOnClickListener {


                var email = binding.edtEmail.text.toString()
                var pass = binding.edtPassword.text.toString()
                auth.signInWithEmailAndPassword(email, pass)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {


                            Toast.makeText(this, "Login Succsefully", Toast.LENGTH_SHORT).show()

                            var myEdit: SharedPreferences.Editor = sharePreferences.edit()
                            myEdit.putBoolean("isLogin", true)
                            myEdit.putString("email", email)
                            myEdit.putString("pass", pass)
                            myEdit.apply()
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
    }


}
