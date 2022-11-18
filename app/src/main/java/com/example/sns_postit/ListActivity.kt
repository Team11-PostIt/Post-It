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
        val col = db.collection("post") //post컬렉션

        col.orderBy("title").limit(3).get()
            .addOnSuccessListener { snapshots->
                val lastRef = snapshots.documents[snapshots.size()-1]
                //col.orderBy("title").startAfter(lastRef).limit(10).get()
                for(d in snapshots!!.documentChanges){
                    var dogList = arrayListOf<Dog>(
                        Dog("${d.document.data["title"]}", "${d.document.data["content"]}", "4", "dog00"),
                        Dog("${d.document.data["title"]}", "${d.document.data["content"]}", "1", "dog01"),
                        Dog("${d.document.data["title"]}", "${d.document.data["content"]}", "3", "dog02"),
                        Dog("${d.document.data["title"]}", "${d.document.data["content"]}", "5", "dog06"),
                        Dog("${d.document.data["title"]}", "${d.document.data["content"]}", "5", "dog06"),
                        Dog("${d.document.data["title"]}", "${d.document.data["content"]}", "5", "dog07"),
                        Dog("${d.document.data["title"]}", "${d.document.data["content"]}", "5", "dog06"),
                        Dog("${d.document.data["title"]}", "${d.document.data["content"]}", "5", "dog06"),
                        Dog("${d.document.data["title"]}", "${d.document.data["content"]}", "5", "dog06")
                    )

                    val dogAdapter = MainListAdapter(this, dogList)
                    listView.adapter = dogAdapter
                    println("${d.type},${d.document.id},${d.document.data["content"]}")
                }
            }



        /*
        col.addSnapshotListener{ value,error->
            for(d in value!!.documentChanges){
                var dogList = arrayListOf<Dog>(
                    Dog("${d.document.data["title"]}", "${d.document.data["content"]}", "4", "dog00"),
                    Dog("${d.document.data["title"]}", "${d.document.data["content"]}", "1", "dog01"),
                    Dog("${d.document.data["title"]}", "${d.document.data["content"]}", "3", "dog02"),
                    Dog("${d.document.data["title"]}", "${d.document.data["content"]}", "5", "dog06")
                )
                val dogAdapter = MainListAdapter(this, dogList)
                listView.adapter = dogAdapter
                println("${d.type},${d.document.id},${d.document.data["text"]}")
            }
        }*/

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