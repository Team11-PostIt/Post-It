package com.example.sns_postit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ListActivity : AppCompatActivity() {
    //private lateinit var binding: ActivityFirestoreBinding
    //private var adapter: MyAdapter? = null
    private val db: FirebaseFirestore = Firebase.firestore
    private val itemsCollectionRef = db.collection("items")
    private var snapshotListener: ListenerRegistration? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        var btn : Button = findViewById<Button>(R.id.LogOut_button)  // 로그아웃 버튼, 로그인 창으로 돌아감

        btn.setOnClickListener() {
            val intent = Intent (this, MainActivity::class.java)
            startActivity(intent)
        }

        var btn2 : Button = findViewById<Button>(R.id.write_button)  // 글쓰기 버튼

        btn2.setOnClickListener() {
            val intent = Intent (this, RegisterActivity::class.java)
            startActivity(intent)
        }

    }
}






