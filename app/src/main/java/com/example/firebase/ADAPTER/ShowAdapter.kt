package com.example.firebase.ADAPTER

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.firebase.ACTIVITY.ShowDataAvtivity
import com.example.firebase.MODALCLASS.ModalClass
import com.example.firebase.R
import com.google.firebase.database.ValueEventListener

class ShowAdapter(
    var showDataAvtivity: ShowDataAvtivity,
    var list: ArrayList<ModalClass>,
    var onItemClick: (String, String, String, String, String) -> Unit,
    var onItemDeleted: (String) -> Unit
) : RecyclerView.Adapter<ShowAdapter.MyViewHolder>() {
    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

        val img: ImageView = itemView.findViewById(R.id.img)
        var txtName : TextView = itemView.findViewById(R.id.txtName)
        var txtAge : TextView = itemView.findViewById(R.id.txtAge)
        var txtCity : TextView = itemView.findViewById(R.id.txtCity)
        var txtNumber : TextView = itemView.findViewById(R.id.txtNumber)
        var imgEdit : ImageView = itemView.findViewById(R.id.imgEdit)
        var imgDelete : ImageView = itemView.findViewById(R.id.imgDelete)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var View = LayoutInflater.from(parent.context).inflate(R.layout.item_file,parent,false)
        return MyViewHolder(View)

    }

    override fun getItemCount(): Int {
        return  list.size

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.txtName.text = list[position].name
        holder.txtAge.text = list[position].age
        holder.txtCity.text = list[position].city
        holder.txtNumber.text = list[position].number

        Glide.with(showDataAvtivity).load(list[position].uri).into(holder.img)

        holder.imgEdit.setOnClickListener {

            onItemClick.invoke(list[position].name,
                list[position].age,
                list[position].city,
                list[position].number,
                list[position].key)
        }

        holder.imgDelete.setOnClickListener {


            onItemDeleted.invoke(list[position].key)
        }





    }


    fun refresh(list : ArrayList<ModalClass>)
    {
        this.list = list
        notifyDataSetChanged()

    }

}