package com.giftoholics.pojo

import com.google.firebase.database.PropertyName

data class Category(

        @PropertyName("categoryname")
        var categoryname:String,

        @PropertyName("categoryid")
        var categoryid:String
){
    constructor():this("","")
}