package com.giftoholics.pojo

import com.google.firebase.database.PropertyName

data class User(

        @PropertyName("email")
        var email:String,

        @PropertyName("role")
        var role:String,

        @PropertyName("password")
        var password:String
){
    constructor():this("","","")
}