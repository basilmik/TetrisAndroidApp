package com.basilgames.android.tetris.storage

import android.content.Context
import android.content.SharedPreferences

class AppPreferences(ctx: Context) {

    var data: SharedPreferences = ctx.getSharedPreferences("APP_PREFERENCES", Context.MODE_PRIVATE)

    fun saveMaxScore(maxScore: Int)
    {
        data.edit().putInt("MAX_SCORE",maxScore).apply()
    }
    fun getMaxScore(): Int
    {
        return data.getInt("MAX_SCORE",0)
    }

    fun clearMaxScore()
    {
        data.edit().putInt("MAX_SCORE", 0).apply()
    }

}