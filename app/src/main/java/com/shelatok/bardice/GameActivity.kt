package com.shelatok.bardice

import android.content.Intent
import android.content.IntentSender
import android.media.Image
import android.os.*
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.View.INVISIBLE
import kotlinx.android.synthetic.main.activity_game.*
import android.view.View.VISIBLE
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import android.widget.ToggleButton
import com.shelatok.bardice.R.drawable.d6_1
import java.util.*
import kotlin.math.absoluteValue
import kotlin.system.exitProcess

class GameActivity : AppCompatActivity() {

    var roll = 0
    var highScore = 0
    var currentScore = 0
    var numdice = 0
    var numones = 0
    var highdie = 0
    var allnums = arrayListOf(0,0,0,0,0,0)
    var allDice = arrayListOf(0,0,0,0,0)
    var keptNums = arrayListOf(0,0,0,0,0)
    var totalrolls = 3
    var diffnums = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

    }

    fun rollDice() : Int {
        return Random().nextInt(6) + 1
    }

    fun score() {
        val keepButtonArray = arrayListOf<ToggleButton>(keepButton1, keepButton2, keepButton3, keepButton4, keepButton5)
        for (a in 1..keepButtonArray.count()){
            keepButtonArray[a-1].visibility = INVISIBLE
        }
        for(a in 1..allnums.count()){
            allnums[a-1] = 0
        }
        playAgainButton.visibility = VISIBLE
        newScoreButton.visibility = VISIBLE
        endButton.visibility = VISIBLE
        rollButton.visibility = INVISIBLE
        keepScoreButton.visibility = INVISIBLE
        for (a in 1..allDice.count()){
            if (allDice[a-1] == 1){
                numones++
            }
        }
        for (a in 1..allDice.count()){
            allnums[allDice[a-1]-1]++
        }
        for (a in 2..allnums.count()){
            if (a >= highdie && allnums[a-1] >= numdice){
                highdie = a
                numdice = allnums[a-1]
            }
        }
        numdice = numones + numdice
        currentScore = (numdice * 10) + highdie
        if (!allDice.contains(1)){
            currentScore = 0
        }
        currentScoreText.text = "Your Score: $currentScore in $roll"
        println(allDice)
        if (currentScore > highScore){
            if (highScore != 0) {
                Toast.makeText(this, "You Win!", Toast.LENGTH_SHORT).show()
            }
            highScore = currentScore
            scoreText.text = "High Score: $highScore in $roll"
        }
    }



    fun onRollClicked(view: View){

        val keepButtonArray = arrayListOf<ToggleButton>(keepButton1, keepButton2, keepButton3, keepButton4, keepButton5)
        val imgVArray = arrayListOf<ImageView>(die1, die2, die3, die4, die5)
        val dicePicArray = arrayListOf<Int>(R.drawable.d6_1, R.drawable.d6_2, R.drawable.d6_3, R.drawable.d6_4,
            R.drawable.d6_5, R.drawable.d6_6)

        for (a in 1..keepButtonArray.count()){
            keepButtonArray[a-1].visibility = VISIBLE
        }
        if (highScore == 0) {
            keepScoreButton.visibility = VISIBLE
        }

        for(a in 1..allnums.count()){
            allnums[a-1] = 0
        }

        diffnums = 0

        for (a in 1..keepButtonArray.count()){
            if (keepButtonArray[a-1].isChecked){
                allnums[allDice[a-1]-1]++
            }
        }
        println(allnums)

        for (a in 1..allnums.count()){
            if (allnums[a-1] != 0){
                diffnums++
            }
        }

        if (keptNums.contains(1) && diffnums == 2 || diffnums == 0) {

            if (roll < totalrolls) {

                val countDownTimer = object : CountDownTimer(1000, 100) {
                    override fun onTick(millisUntilFinished: Long) {
                        for (a in 1..keepButtonArray.count()) {
                            if (!keepButtonArray[a - 1].isChecked) {
                                var rollnum = rollDice()
                                imgVArray[a - 1].setImageResource(dicePicArray[rollnum - 1])
                                allDice[a - 1] = rollnum
                            }
                        }
                    }

                    override fun onFinish() {

                    }
                }
                countDownTimer.start()

                roll++
                rollText.text = "Roll: $roll"

                if (roll == totalrolls) {
                    val counttimer = object : CountDownTimer(1000, 100) {
                        override fun onTick(millisFinished: Long) {
                        }

                        override fun onFinish() {
                            score()
                        }
                    }
                    counttimer.start()


                }
            }

        } else {
            Toast.makeText(this, "You can only keep ones with one other number", Toast.LENGTH_SHORT).show()
        }

    }

    fun onKeepClicked(view: View){
        val keepButtonArray = arrayListOf<ToggleButton>(keepButton1, keepButton2, keepButton3, keepButton4, keepButton5)
        if (!allDice.contains(1)){
            Toast.makeText(this, "You need a 1 to keep.", Toast.LENGTH_SHORT).show()
            for (a in 1..keepButtonArray.count()){
                keepButtonArray[a-1].isChecked = false
            }
        }

        for (a in 1..keepButtonArray.count()){
            if(keepButtonArray[a-1].isChecked){
                keptNums[a-1] = allDice[a-1]
            } else {
                keptNums[a-1] = 0
            }
        }
        println(keptNums)
    }

    fun onKeepScoreClick(view: View){
        totalrolls = roll
        score()
    }

    fun onPlayAgainClick (view: View){
        val keepButtonArray = arrayListOf<ToggleButton>(keepButton1, keepButton2, keepButton3, keepButton4, keepButton5)
        numones = 0
        roll = 0
        highdie = 0
        numdice = 0
        for (a in 1..6){
            allnums[a-1] = 0
        }
        for (a in 1..allDice.count()){
            allDice[a-1] = 0
        }
        for (a in 1..5){
            keptNums[a-1] = 0
        }

        currentScoreText.text = "Your Score: Not Set"
        playAgainButton.visibility = INVISIBLE
        newScoreButton.visibility = INVISIBLE
        endButton.visibility = INVISIBLE
        rollButton.visibility = VISIBLE

        for (a in 1..keepButtonArray.count()){
            keepButtonArray[a-1].isChecked = false
            keepButtonArray[a-1].visibility = INVISIBLE
        }
        rollText.text = "Roll: $roll"
    }

    fun onNewScoreClick (view: View){
        onPlayAgainClick(view)
        totalrolls = 3
        highScore = 0
        var numdice = 0
        var highdie = 0
        scoreText.text = "High Score: Not Set"
    }

    fun onEndClick(view: View){
        moveTaskToBack(true)
        exitProcess(-1)
    }



}
