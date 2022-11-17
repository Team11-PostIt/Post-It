package com.example.sns_postit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

//리스트 activity_list.xml
class ListActivity : AppCompatActivity() {
    //private lateinit var binding: ActivityFirestoreBinding
    //private var adapter: MyAdapter? = null
    private val db: FirebaseFirestore = Firebase.firestore
    private val itemsCollectionRef = db.collection("post")
    private var snapshotListener: ListenerRegistration? = null

    class Dog (val breed: String, val gender: String, val age: String, val photo: String)
    //var dogList = arrayListOf<Dog>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        val listView : ListView = findViewById<ListView>(R.id.listView)


        var dogList = arrayListOf<Dog>(
            Dog("Chow Chow", "Male", "4", "dog00"),
            Dog("Breed Pomeranian", "Female", "1", "dog01"),
            Dog("Golden Retriver", "Female", "3", "dog02"),
            Dog("Yorkshire Terrier", "Male", "5", "dog03"),
            Dog("Pug", "Male", "4", "dog04"),
            Dog("Alaskan Malamute", "Male", "7", "dog05"),
            Dog("Shih Tzu", "Female", "5", "dog06")
        )

        val dogAdapter = MainListAdapter(this, dogList)
        listView.adapter = dogAdapter

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