package com.kotlin.a03_mysecretdiary

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.core.widget.addTextChangedListener

class DiaryActivity : AppCompatActivity() {
    private val handler = Handler(Looper.getMainLooper())
    private val diaryEditText : EditText by lazy{
        findViewById(R.id.diaryEditText)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_diary)

        initViews()
        bindViews()
    }

    private fun initViews() {
        val memoPreference = getSharedPreferences("diary",MODE_PRIVATE)
        diaryEditText.setText(memoPreference.getString("memo", ""))

    }

    private fun bindViews() {
        val runnable = Runnable{
            val memoPreference = getSharedPreferences("diary",MODE_PRIVATE)
            memoPreference.edit(commit = true){
                putString("memo",diaryEditText.text.toString())
            }
        }
        diaryEditText.addTextChangedListener {
            handler.apply{
               removeCallbacks(runnable)
               postDelayed(runnable, 500)
            }
        }
    }


}

