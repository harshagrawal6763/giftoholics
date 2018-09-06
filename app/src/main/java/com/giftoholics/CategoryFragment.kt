package com.giftoholics


import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.giftoholics.pojo.Category
import com.giftoholics.pojo.User
import com.giftoholics.viewmodel.CategoriesViewModel
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_sign_in.*
import kotlinx.android.synthetic.main.fragment_category.*
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.RecyclerView
import android.content.DialogInterface
import android.R.string.cancel
import android.content.res.Configuration
import android.content.res.Configuration.KEYBOARD_12KEY
import android.os.Handler
import android.support.v7.app.AlertDialog
import android.text.InputType
import android.widget.EditText








class CategoryFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_category, container, false)
    }
    private var categoriesViewModel:CategoriesViewModel?=null

    private var categoryList= arrayListOf<Category>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        getViewModel()
//
//        watchViewModel()

        //insertCategory("Gifts To Give")

        if (categoryList.isEmpty()) {
            getAllCategories()
        }

        fabButton?.setOnClickListener {
            callAddCategory()
        }
    }

    private fun callAddCategory() {
        val alert = AlertDialog.Builder(this.context!!)
        alert.setTitle("Add Category")
        val input = EditText(this.context)
        input.inputType = InputType.TYPE_CLASS_TEXT
        alert.setView(input)
        alert.setPositiveButton("Ok", DialogInterface.OnClickListener { dialog, whichButton ->
            //Put actions for OK button here
            insertCategory(input?.text?.toString()!!)
            Handler().postDelayed({getAllCategories()},2000)
        })
        alert.setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, whichButton ->
            //Put actions for CANCEL button here, or leave in blank
            dialog.cancel()
        })
        alert.show()
    }

    private fun getAllCategories() {
        val database = FirebaseDatabase.getInstance()
        val myRef = database.reference
        val query=myRef.child(CATEGORIES).orderByChild("categoryname")
        categoryList.clear()
        query.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                makeToast(p0?.message!!)

            }

            override fun onDataChange(p0: DataSnapshot) {
                p0?.children?.forEach {
                    val category =it?.getValue(Category::class.java)
                            category?.let {
                                categoryList.add(category)

                            }
//                            makeToast("Data "+category?.categoryname)
                      }
                setAdapter()
            }



        })
    }

    private fun setAdapter() {
        categoryAdapter= CategoryAdapter(categoryList){
           // makeToast(it.categoryname)
        }

        val mLayoutManager = LinearLayoutManager(this.context)

        // horizontal RecyclerView
        // keep movie_list_row.xml width to `wrap_content`
        // RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);

        recyCategoryList.setLayoutManager(mLayoutManager)

        // adding inbuilt divider line
        recyCategoryList.addItemDecoration(DividerItemDecoration(this.context, LinearLayoutManager.VERTICAL))

        // adding custom divider line with padding 16dp
        // recyclerView.addItemDecoration(new MyDividerItemDecoration(this, LinearLayoutManager.VERTICAL, 16));
        recyCategoryList.setItemAnimator(DefaultItemAnimator())

        recyCategoryList?.adapter=categoryAdapter

    }

    private var categoryAdapter:CategoryAdapter?=null


    fun insertCategory(categoryName:String){
        val mDatabase = FirebaseDatabase.getInstance().reference
        var id=mDatabase.child(CATEGORIES).push().setValue(Category(categoryname = categoryName,categoryid = System.currentTimeMillis().toString()),object : DatabaseReference.CompletionListener{
            override fun onComplete(p0: DatabaseError?, p1: DatabaseReference) {
                var key=p1.key
            }

        })
    }
    private fun watchViewModel() {

    }

    private fun getViewModel() {
        categoriesViewModel= ViewModelProviders.of(this).get(CategoriesViewModel::class.java)
    }
}
