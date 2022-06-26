package com.basilgames.android.tetris

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.basilgames.android.tetris.storage.AppPreferences
import com.basilgames.android.tetris.view.TetrisView

class GameActivity : AppCompatActivity() {
    var textViewMaxScore: TextView? = null
    var textViewCurrentScore: TextView? = null
    var appPreferences: AppPreferences? = null


    private lateinit var tetrisView: TextView
    private val appModel: AppModel = AppModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        appPreferences = AppPreferences(this)
        appModel.setPreferences(appPreferences)


        val buttonRestart = findViewById<Button>(R.id.button_restart)
        textViewMaxScore = findViewById(R.id.text_view_max_score)
        textViewCurrentScore = findViewById(R.id.text_view_current_score)
        tetrisView = findViewById(R.id.view_tetris)

        tetrisView.setActivity(this)
        tetrisView.setModel(appModel)
        tetrisView.setOnClickListener(this::onTetrisViewTouch)
        buttonRestart.setOnClickListener(this::buttonRestartClick)
        updateCurrentScore()
        updateMaxScore()

    }

    private fun buttonRestartClick(view: View)
    {
        appModel.restartGame()
    }

    private fun onTetrisViewTouch(view: View, event: MotionEvent): Boolean
    {
        if (appModel.isGameOver() || appModel.isGameAwaitingStart())
        {
            appModel.startGame()
            tetrisView.setGameCommandWithDelay(AppModel.Motions.DOWN)
        }
        else if (appModel.isGameActive())
        {
            when (resolveTouchDirection(view, event))
            {
                0 -> moveTetromino(AppModel.Motions.LEFT)
                1 -> moveTetromino(AppModel.Motions.ROTATE)
                2 -> moveTetromino(AppModel.Motions.DOWN)
                3 -> moveTetromino(AppModel.Motions.RIGHT)

            }
        }
        return true
    }

    private fun resolveTouchDirection(view: View, event: MotionEvent): Int
    {
        val x = event.x / view.width
        val y = event.y / view.height
        val direction: Int

        direction = if(y>x)
        {
            if (x > 1- y) 2 else 0
        }
        else
        {
            if (x > 1 - y) 3 else 1
        }
        return direction
    }

    private fun moveTetromino(motion: AppModel.Motions)
    {
        if (appModel.isGameActive())
        {
            tetrisView.setGameCommand(motion)
        }
    }


    private fun updateMaxScore(){
        textViewMaxScore?.text="${appPreferences?.getMaxScore()}"
    }

    private fun updateCurrentScore(){
        textViewCurrentScore?.text = "0"
    }

}