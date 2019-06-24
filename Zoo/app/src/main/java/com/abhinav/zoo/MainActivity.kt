package com.abhinav.zoo

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.abhinav.zoo.model.Animal
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.animal_ticket.view.*

class MainActivity : AppCompatActivity() {

    var animalList= ArrayList<Animal>()
    var adapter:AnimalAdapter?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //load data
        animalList.add(Animal("Baboon","It as a breed of monkey",R.drawable.baboon))
        animalList.add(Animal("Bulldog","It as a breed of dog",R.drawable.bulldog))
        animalList.add(Animal("Panda","A friendly animal",R.drawable.panda))
        animalList.add(Animal("White Tiger","It is a dangerous animal",R.drawable.white_tiger))
        animalList.add(Animal("Swallow bird","It is a breed of birds",R.drawable.swallow_bird))

        adapter= AnimalAdapter(applicationContext,animalList)
        tv_list.adapter= adapter

    }


    fun loaddata(){

    }

    class AnimalAdapter:BaseAdapter{
        var animalList=ArrayList<Animal>()
        var context:Context?=null
        constructor(context: Context, animalList: ArrayList<Animal>):super(){
            this.animalList=animalList
            this.context=context
        }
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            var animal=animalList[position]
            var layoutInflater=context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            var myview= layoutInflater.inflate(R.layout.animal_ticket,null)
            myview.textView.text=animal.name!!
            myview.textView2.text=animal.desc!!
            myview.imageView.setImageResource(animal.image!!)
            return myview
        }

        override fun getItem(position: Int): Any {
            return animalList[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return  animalList.size
        }

    }
}
