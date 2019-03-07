package com.shelatok.bardice

import android.content.Intent
import android.os.*
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.View.INVISIBLE
import kotlinx.android.synthetic.main.activity_game.*
import android.view.View.VISIBLE
import android.widget.Toast
import java.util.*
import kotlin.math.absoluteValue
import kotlin.system.exitProcess

class GameActivity : AppCompatActivity() {

    var roll = 0
    var highScore = 0
    var numdice = 0
    var highdie = 0
    var allDice = arrayListOf(0,0,0,0,0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
    }

    fun rollDice() : Int {
        return Random().nextInt(6) + 1
    }



    fun onRollClicked(view: View){

        keepButton1.visibility = VISIBLE
        keepButton2.visibility = VISIBLE
        keepButton3.visibility = VISIBLE
        keepButton4.visibility = VISIBLE
        keepButton5.visibility = VISIBLE
        keepScoreButton.visibility = VISIBLE

        if (roll < 3) {
            if (!keepButton1.isChecked) {
                var rollnum = rollDice()
                when (rollnum) {
                    1 -> die1.setImageResource(R.drawable.d6_1)
                    2 -> die1.setImageResource(R.drawable.d6_2)
                    3 -> die1.setImageResource(R.drawable.d6_3)
                    4 -> die1.setImageResource(R.drawable.d6_4)
                    5 -> die1.setImageResource(R.drawable.d6_5)
                    6 -> die1.setImageResource(R.drawable.d6_6)
                }
                allDice[0] = rollnum
            }

            if (!keepButton2.isChecked) {
                var rollnum = rollDice()
                when (rollnum) {
                    1 -> die2.setImageResource(R.drawable.d6_1)
                    2 -> die2.setImageResource(R.drawable.d6_2)
                    3 -> die2.setImageResource(R.drawable.d6_3)
                    4 -> die2.setImageResource(R.drawable.d6_4)
                    5 -> die2.setImageResource(R.drawable.d6_5)
                    6 -> die2.setImageResource(R.drawable.d6_6)
                }
                allDice[1] = rollnum
            }

            if (!keepButton3.isChecked) {
                var rollnum = rollDice()
                when (rollnum) {
                    1 -> die3.setImageResource(R.drawable.d6_1)
                    2 -> die3.setImageResource(R.drawable.d6_2)
                    3 -> die3.setImageResource(R.drawable.d6_3)
                    4 -> die3.setImageResource(R.drawable.d6_4)
                    5 -> die3.setImageResource(R.drawable.d6_5)
                    6 -> die3.setImageResource(R.drawable.d6_6)
                }
                allDice[2] = rollnum
            }

            if (!keepButton4.isChecked) {
                var rollnum = rollDice()
                when (rollnum) {
                    1 -> die4.setImageResource(R.drawable.d6_1)
                    2 -> die4.setImageResource(R.drawable.d6_2)
                    3 -> die4.setImageResource(R.drawable.d6_3)
                    4 -> die4.setImageResource(R.drawable.d6_4)
                    5 -> die4.setImageResource(R.drawable.d6_5)
                    6 -> die4.setImageResource(R.drawable.d6_6)
                }
                allDice[3] = rollnum
            }

            if (!keepButton5.isChecked) {
                var rollnum = rollDice()
                when (rollnum) {
                    1 -> die5.setImageResource(R.drawable.d6_1)
                    2 -> die5.setImageResource(R.drawable.d6_2)
                    3 -> die5.setImageResource(R.drawable.d6_3)
                    4 -> die5.setImageResource(R.drawable.d6_4)
                    5 -> die5.setImageResource(R.drawable.d6_5)
                    6 -> die5.setImageResource(R.drawable.d6_6)
                }
                allDice[4] = rollnum
            }
            roll++
            rollText.text = "Current Roll: $roll"
            if (roll == 3) {
                playAgainButton.visibility = VISIBLE
                newScoreButton.visibility = VISIBLE
                endButton.visibility = VISIBLE
                rollButton.visibility = INVISIBLE
                keepScoreButton.visibility = INVISIBLE

                for (a in 1..allDice.count()){

                }
            }
        }



    }

    fun onKeepClicked(view: View){
        if (!allDice.contains(1)){
            Toast.makeText(this, "You need a 1 to keep.", Toast.LENGTH_SHORT).show()
            keepButton1.isChecked = false
            keepButton2.isChecked = false
            keepButton3.isChecked = false
            keepButton4.isChecked = false
            keepButton5.isChecked = false
        }
    }

    fun onPlayAgainClick (view: View){
        roll = 0
        playAgainButton.visibility = INVISIBLE
        newScoreButton.visibility = INVISIBLE
        endButton.visibility = INVISIBLE
        rollButton.visibility = VISIBLE
        keepButton1.isChecked = false
        keepButton2.isChecked = false
        keepButton3.isChecked = false
        keepButton4.isChecked = false
        keepButton5.isChecked = false
        keepButton1.visibility = INVISIBLE
        keepButton2.visibility = INVISIBLE
        keepButton3.visibility = INVISIBLE
        keepButton4.visibility = INVISIBLE
        keepButton5.visibility = INVISIBLE
        rollText.text = "Current Roll: $roll"
    }

    fun onNewScoreClick (view: View){
        onPlayAgainClick(view)
        var highScore = 0
        var numdice = 0
        var highdie = 0
    }

    fun onEndClick(view: View){
        moveTaskToBack(true)
        exitProcess(-1)
    }



}
