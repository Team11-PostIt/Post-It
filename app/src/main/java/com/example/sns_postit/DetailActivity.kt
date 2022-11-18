package com.example.sns_postit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import com.example.sns_postit.databinding.ActivityDetailBinding
import com.example.sns_postit.databinding.ActivityMainBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

//디테일(내가 작성한 글이보이는 화면) activity_detail.xml
class DetailActivity : AppCompatActivity() {

    private val db: FirebaseFirestore = Firebase.firestore
    private val itemsCollectionRef = db.collection("post")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val col = db.collection("post") //post컬렉션
        val title_tv : TextView = findViewById<TextView>(R.id.title_tv)

        col.addSnapshotListener{ value,error->
            for(d in value!!.documentChanges){
                binding.titleTv.text="${d.document.data["title"]}"
                binding.contentTv.text="${d.document.data["content"]}"
                //title_tv.text.toString().trim()
                //println("${d.type},${d.document.id},${d.document.data["content"]}")
            }
        }


        var btn : Button = findViewById<Button>(R.id.write_button)  // 로그아웃 버튼, 로그인 창으로 돌아감

        btn.setOnClickListener() {
            val intent = Intent (this, ListActivity::class.java)
            startActivity(intent)
        }
    }
}