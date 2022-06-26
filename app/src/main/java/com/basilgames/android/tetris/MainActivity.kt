package com.basilgames.android.tetris

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.basilgames.android.tetris.storage.AppPreferences
import com.google.android.material.snackbar.Snackbar
import kotlin.system.exitProcess


class MainActivity : AppCompatActivity() {
    private var textViewMaxScore: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        val buttonNewGame = findViewById<Button>(R.id.button_new_game)
        val buttonResetScore = findViewById<Button>(R.id.button_reset_score)
        val buttonExit = findViewById<Button>(R.id.button_exit)
        textViewMaxScore = findViewById<TextView>(R.id.text_view_max_score)

        buttonNewGame.setOnClickListener(this::onButtonNewGame)
        buttonResetScore.setOnClickListener(this::onButtonResetScore)
        buttonExit.setOnClickListener(this::onButtonExit)

        updateMaxScore()
    }

    private fun onButtonNewGame(view: View)
    {
        val intent = Intent(this, GameActivity::class.java)
        startActivity(intent)
    }

    @SuppressLint("SetTextI18n")
    private fun onButtonResetScore(view: View)
    {
        val preferences = AppPreferences(this)
        preferences.clearMaxScore()
        Snackbar.make(view,"Score reset", Snackbar.LENGTH_SHORT).show()
        textViewMaxScore?.text = "Max Score: ${preferences.getMaxScore()}"
    }

    @SuppressLint("SetTextI18n")
    fun updateMaxScore() {
        val preferences = AppPreferences(this)
        textViewMaxScore?.text = "High score: ${preferences.getMaxScore()}"
    }

    private fun onButtonExit(view: View)
    {
        exitProcess(0)
    }

}