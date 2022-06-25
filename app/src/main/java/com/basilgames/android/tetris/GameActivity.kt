package com.basilgames.android.tetris

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.basilgames.android.tetris.storage.AppPreferences

class GameActivity : AppCompatActivity() {
    var textViewMaxScore: TextView? = null
    var textViewCurrentScore: TextView? = null
    var appPreferences: AppPreferences? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        appPreferences = AppPreferences(this)


        val buttonRestart = findViewById<Button>(R.id.button_restart)
        textViewMaxScore = findViewById(R.id.text_view_max_score)
        textViewCurrentScore = findViewById(R.id.text_view_current_score)

        updateCurrentScore()
        updateMaxScore()

    }

    private fun updateMaxScore(){
        textViewMaxScore?.text="${appPreferences?.getMaxScore()}"
    }

    private fun updateCurrentScore(){
        textViewCurrentScore?.text = "0"
    }

}