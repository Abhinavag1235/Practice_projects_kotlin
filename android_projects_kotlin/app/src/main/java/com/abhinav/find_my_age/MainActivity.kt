package com.abhinav.find_my_age

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener{
            val year = editText.text.toString().toInt()
            val currentyear = Calendar.getInstance().get(Calendar.YEAR)
            val myage=currentyear-year
            textView.text= "Your age is $myage"
        }
    }
}
