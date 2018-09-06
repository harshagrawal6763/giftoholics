package com.giftoholics

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.giftoholics.pojo.User
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_sign_in.*


class SignInActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        btnLogin?.setOnClickListener({
            if (checkNetwork(this)) {
                if(txtEmail.validate() && txtPassword.validate()){
                    val database = FirebaseDatabase.getInstance()
                    val myRef = database.reference
                    val query=myRef.child(USERS).orderByChild("email").equalTo(txtEmail?.text?.toString())
                    query.addListenerForSingleValueEvent(object :ValueEventListener{
                        override fun onCancelled(p0: DatabaseError) {
                            makeToast(p0?.message!!)

                        }

                        override fun onDataChange(p0: DataSnapshot) {
                            p0?.children?.forEach {
                                val user =it?.getValue(User::class.java)
                                if(user?.password==txtPassword?.text?.toString()){
                                    if(user?.role=="admin")
                                        succeed()
                                    else{
                                        makeToast("App Still In Development :)")
                                    }
                                }
                            }
                        }



                    })
                }else{
                    makeToast("Please Enter the fields",Toast.LENGTH_LONG)
                }
            }else{
                makeToast("Please connect to internet",Toast.LENGTH_LONG)
            }
        })
    }

    private fun succeed() {
        val intent= Intent(this,MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}
