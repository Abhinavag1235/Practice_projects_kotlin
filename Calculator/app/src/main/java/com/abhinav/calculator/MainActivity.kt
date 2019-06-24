package com.abhinav.calculator

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
var isNew=true
    fun buttonNumber(view: View){
        if(isNew){
            editText.setText(" ")
        }
        isNew=false
        val buttonSelected = view as Button
        var buttonclicked:String =editText.text.toString()
        when(buttonSelected.id){
            button0.id->{buttonclicked +="0"}
            button1.id->{buttonclicked +="1"}
            button2.id->{buttonclicked +="2"}
            button3.id->{buttonclicked +="3"}
            button4.id->{buttonclicked +="4"}
            button5.id->{buttonclicked +="5"}
            button6.id->{buttonclicked +="6"}
            button7.id->{buttonclicked +="7"}
            button8.id->{buttonclicked +="8"}
            button9.id->{buttonclicked +="9"}
            buttondot.id->{buttonclicked +="."}
            buttonpm.id->{buttonclicked ="-"+ buttonclicked}
        }
        editText.setText(buttonclicked)
    }

    var op="*"
    var oldnumber=""

    fun buttonEvent(view:View){
        val buttonSelected = view as Button
        var buttonclicked:String =editText.text.toString()
        when(buttonSelected.id) {
            buttonmulti.id -> {
                op="*"
            }
            buttondivide.id -> {
                op="/"
            }
            buttonplus.id -> {
                op="+"
            }buttonminus.id -> {
            op="- "
            }

        }
        oldnumber=editText.text.toString()
        isNew=true
    }
    fun buttonEqual(view:View){
        var newNo=editText.text.toString()
        var finalnumber:Double?=null
        when(op){
            "*"->{
                finalnumber=oldnumber.toDouble()*newNo.toDouble()
            }
            "/"->{
                finalnumber=oldnumber.toDouble()/newNo.toDouble()
            }
            "+"->{
                finalnumber=oldnumber.toDouble()+newNo.toDouble()
            }
            "-"->{
                finalnumber=oldnumber.toDouble()-newNo.toDouble()
            }
        }
        editText.setText(finalnumber.toString())
        isNew=true
    }
    fun buttonPercent(view:View){
        val number=editText.text.toString().toDouble()/100
        editText.setText(number.toString())
        isNew=true
    }
    fun buttonAC(view: View){
        editText.setText("0")
        isNew=true
    }
}
