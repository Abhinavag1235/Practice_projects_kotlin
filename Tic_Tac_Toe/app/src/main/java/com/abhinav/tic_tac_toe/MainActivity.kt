package com.abhinav.tic_tac_toe


import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }

    fun blacked(view:View){
        val buttonselected = view as Button
        var cellID=0
        when(buttonselected.id){
            R.id.button1->cellID=1
            R.id.button2->cellID=2
            R.id.button3->cellID=3
            R.id.button4->cellID=4
            R.id.button5->cellID=5
            R.id.button6->cellID=6
            R.id.button7->cellID=7
            R.id.button8->cellID=8
            R.id.button9->cellID=9
        }
       // Toast.makeText(this,"You clicked button $cellID",Toast.LENGTH_SHORT).show()
        PlayGame(cellID,buttonselected)
    }
    var player1= ArrayList<Int>()
    var player2= ArrayList<Int>()
    var activeplayer=1
    fun PlayGame(cellID:Int,buttonSelected:Button){
        if(activeplayer==1){
            buttonSelected.text="X"
            buttonSelected.setBackgroundColor(Color.GREEN)
            player1.add(cellID)
            AutoPlay()
            activeplayer=2
        }else{
            buttonSelected.text="0"
            buttonSelected.setBackgroundColor(Color.BLUE)
            player1.add(cellID)
            activeplayer=1
        }
        buttonSelected.isEnabled=false

        CheckWinner()
    }

    fun CheckWinner(){
        var winner=-1
        //row1
        if(player1.contains(1) && player1.contains(2) && player1.contains(3)){
            winner=1;
        }
        if(player2.contains(1) && player2.contains(2) && player2.contains(3)){
            winner=2;
        }
        //row2
        if(player1.contains(4) && player1.contains(5) && player1.contains(6)){
            winner=1;
        }
        if(player2.contains(4) && player2.contains(5) && player2.contains(6)){
            winner=2;
        }
        //row3
        if(player1.contains(7) && player1.contains(8) && player1.contains(9)){
            winner=1;
        }
        if(player2.contains(7) && player2.contains(8) && player2.contains(9)){
            winner=2;
        }
        //col1
        if(player1.contains(1) && player1.contains(4) && player1.contains(7)){
            winner=1;
        }
        if(player2.contains(1) && player2.contains(4) && player2.contains(7)){
            winner=2;
        }
        //col2
        if(player1.contains(2) && player1.contains(5) && player1.contains(8)){
            winner=1;
        }
        if(player2.contains(2) && player2.contains(5) && player2.contains(8)){
            winner=2;
        }
        //col3
        if(player1.contains(3) && player1.contains(6) && player1.contains(9)){
            winner=1;
        }
        if(player2.contains(3) && player2.contains(6) && player2.contains(9)){
            winner=2;
        }
        //diag1
        if(player1.contains(3) && player1.contains(5) && player1.contains(7)){
            winner=1;
        }
        if(player2.contains(3) && player2.contains(5) && player2.contains(7)){
            winner=2;
        }
        //diag2
        if(player1.contains(1) && player1.contains(5) && player1.contains(9)){
            winner=1;
        }
        if(player2.contains(1) && player2.contains(5) && player2.contains(9)){
            winner=2;
        }

        if(winner != -1){
            Toast.makeText(this,"$winner Won the game",Toast.LENGTH_LONG).show()
        }
    }

    fun AutoPlay(){
        var emptycells=ArrayList<Int>()
        for(cellID in 1..9){
            if(!(player1.contains(cellID) || player2.contains(cellID))){
                emptycells.add(cellID)
            }
        }

        val r = java.util.Random()
        val randIndex=r.nextInt(emptycells.size-0)+1
        val cellID=emptycells[randIndex]
        var buSelected:Button?
        when(cellID){
            1-> buSelected=button1
            2-> buSelected=button2
            3-> buSelected=button3
            4-> buSelected=button4
            5-> buSelected=button5
            6-> buSelected=button6
            7-> buSelected=button7
            8-> buSelected=button8
            9-> buSelected=button9
            else->{
                buSelected=button1
            }
        }

        PlayGame(cellID,buSelected)
    }
}
