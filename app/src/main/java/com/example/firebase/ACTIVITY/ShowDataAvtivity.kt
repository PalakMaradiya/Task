package com.example.firebase.ACTIVITY

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firebase.MODALCLASS.ModalClass
import com.example.firebase.ADAPTER.ShowAdapter
import com.example.firebase.databinding.ActivityShowDataAvtivityBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ShowDataAvtivity : AppCompatActivity() {
    lateinit var binding: ActivityShowDataAvtivityBinding
    lateinit var adapter : ShowAdapter
     var list = ArrayList<ModalClass>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShowDataAvtivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
    }

    private fun initView() {


        var reference = FirebaseDatabase.getInstance().reference

        binding.btnShowData.setOnClickListener {

            reference.root.child("InformestionTb").addValueEventListener(object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {

                    for (child in snapshot.children) {
                        var data : ModalClass? = child.getValue(ModalClass::class.java)
                        if (data != null) {
                            list.add(data)
                        }
                    }

                    adapter = ShowAdapter(this@ShowDataAvtivity,list, onItemClick = { name,age,city,number,key ->

                        var i = Intent(this@ShowDataAvtivity, UpdateDataActivity::class.java)
                        i.putExtra("key",key)
                        i.putExtra("name",name)
                        i.putExtra("age",age)
                        i.putExtra("city",city)
                        i.putExtra("number",number)
                        startActivity(i)

                    }) { key ->


                        reference =
                            FirebaseDatabase.getInstance().getReference().child("InformestionTb")
                                .child(key)
                        reference.removeValue()
                    }

                    adapter.refresh(list)
                    var manager = LinearLayoutManager(this@ShowDataAvtivity,LinearLayoutManager.VERTICAL,false)
                    binding.rcv.adapter = adapter
                    binding.rcv.layoutManager= manager


                }

                override fun onCancelled(error: DatabaseError) {

                }

            })




        }

    }
}