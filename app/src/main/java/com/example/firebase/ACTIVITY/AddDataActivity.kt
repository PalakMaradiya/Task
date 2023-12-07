package com.example.firebase.ACTIVITY

import android.app.ProgressDialog
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.firebase.MODALCLASS.ModalClass
import com.example.firebase.databinding.ActivityAddDataBinding
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.util.UUID

class AddDataActivity : AppCompatActivity() {
    private val PICK_IMAGE_REQUEST = 100
    lateinit var uri: Uri
    lateinit var d: Uri

    lateinit var sharePreferences: SharedPreferences



    lateinit var binding: ActivityAddDataBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddDataBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharePreferences=getSharedPreferences("MySharedPref", MODE_PRIVATE)
        initview()

    }

    private fun initview() {


        var reference = FirebaseDatabase.getInstance().reference

        var myEdit: SharedPreferences.Editor = sharePreferences.edit()
        myEdit.putBoolean("isLogin", true)
        myEdit.apply()



        binding.btnSumbit.setOnClickListener {


            var name = binding.edtName.text.toString()
            var age = binding.edtAge.text.toString()
            var number = binding.edtNumber.text.toString()
            var city = binding.edtCity.text.toString()

            if (name.isEmpty()) {
                Toast.makeText(this, "Please Enter Your Name :", Toast.LENGTH_SHORT).show()
            } else if (age.isEmpty()) {
                Toast.makeText(this, "Please Enter Your Age ", Toast.LENGTH_SHORT).show()
            } else if (number.isEmpty()) {
                Toast.makeText(this, "Please Enter Your Number", Toast.LENGTH_SHORT).show()
            } else if (city.isEmpty()) {
                Toast.makeText(this, "Please Enter Your City", Toast.LENGTH_SHORT).show()
            } else {
                var key = reference.root.child("InformestionTb").push().key ?: ""

                var modelclass = ModalClass(name, age, number, city, key)


                reference.root.child("InformestionTb").child(key).setValue(modelclass)
                    .addOnCompleteListener {

                        if (it.isSuccessful) {
                            Toast.makeText(this, "Data Added SuccessFully", Toast.LENGTH_SHORT)
                                .show()

                            var i = Intent(this@AddDataActivity, ShowDataAvtivity::class.java)
                            startActivity(i)
                        }
                    }.addOnFailureListener {

                        Log.e("TAG", "Error: " + it)
                    }


            }

        }

    }


}
