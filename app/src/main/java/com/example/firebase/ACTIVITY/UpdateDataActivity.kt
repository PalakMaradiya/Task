package com.example.firebase.ACTIVITY

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.firebase.MODALCLASS.ModalClass
import com.example.firebase.databinding.ActivityUpdateDataBinding
import com.google.firebase.database.FirebaseDatabase

class UpdateDataActivity : AppCompatActivity() {
    lateinit var binding: ActivityUpdateDataBinding
    lateinit var key : String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
    }

    private fun initView() {


        var reference = FirebaseDatabase.getInstance().reference

        if (intent != null) {
             key = intent.getStringExtra("key").toString()
            var name = intent.getStringExtra("name").toString()
            var age = intent.getStringExtra("age").toString()
            var city = intent.getStringExtra("city").toString()
            var number = intent.getStringExtra("number").toString()

            binding.edtName.setText(name)
            binding.edtAge.setText(age)

            binding.edtNumber.setText(number)
            binding.edtCity.setText(city)


            binding.btnSumbit.setOnClickListener {


                var name1 = binding.edtName.text.toString()
                var age1 = binding.edtAge.text.toString()
                var city1 = binding.edtCity.text.toString()
                var number1 = binding.edtNumber.text.toString()


                var modelClass = ModalClass(name1, age1, number1, city1, key,)

                reference.root.child("InformestionTb").child(key).setValue(modelClass)
                    .addOnCompleteListener {

                        if (it.isSuccessful) {
                            Toast.makeText(this, "Data Added SuccessFully", Toast.LENGTH_SHORT)
                                .show()
                            onBackPressed()

                        }
                    }.addOnFailureListener {

                        Log.e("TAG", "Error: " + it)
                    }

            }


        }

    }

}
