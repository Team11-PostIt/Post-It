package com.example.sns_postit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var btn : Button = findViewById<Button>(R.id.Go_SignUp)

        btn.setOnClickListener() {
            val intent = Intent (this, SecondActivity::class.java)
            startActivity(intent)
        }
    }
}