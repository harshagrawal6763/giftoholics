package com.giftoholics

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.support.v4.app.Fragment
import android.widget.EditText
import android.widget.Toast

fun EditText.validate(): Boolean {
    return !this.text.toString().isEmpty()
}

fun Activity.makeToast(message:String,duration:Int=Toast.LENGTH_SHORT){
    Toast.makeText(this,message,duration).show()
}

fun Fragment.makeToast(message:String,duration:Int=Toast.LENGTH_SHORT){
    Toast.makeText(this.activity,message,duration).show()
}

fun checkNetwork(context: Context): Boolean {
    val conMgr = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork = conMgr.getActiveNetworkInfo()
    return activeNetwork != null && activeNetwork.isConnected()
}