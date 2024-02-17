package com.example.firebase.MODALCLASS

import android.net.Uri

class ModalClass {
    var name : String = ""
    var age : String = ""
    var number : String = ""
     var city : String = ""
     var key : String = ""
      var uri : String=""


    constructor(name: String, age: String, number: String, city: String, key: String,uri : Uri)
    {

        this.age = age
        this.city = city
        this.key = key
        this.name = name
        this.number = number
        this.uri = uri.toString()


    }


    constructor(name: String, age: String, number: String, city: String, key: String)
    {

        this.age = age
        this.city = city
        this.key = key
        this.name = name
        this.number = number


    }

    constructor()
    {

    }
}