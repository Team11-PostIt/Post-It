package com.example.sns_postit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)


        var btn : Button = findViewById<Button>(R.id.reg_button)  // 로그아웃 버튼, 로그인 창으로 돌아감

        btn.setOnClickListener() {
            val intent = Intent (this, ListActivity::class.java)
            startActivity(intent)
        }
    }
}