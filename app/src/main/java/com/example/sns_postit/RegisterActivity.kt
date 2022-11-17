package com.example.sns_postit

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val db = Firebase.firestore

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        var btn : Button = findViewById<Button>(R.id.write_button)  // 등록하기 버튼, 작성한 글 등록됨
        val etTitle:EditText = findViewById<EditText>(R.id.title_et)
        val etContent:EditText = findViewById<EditText>(R.id.content_et)

        btn.setOnClickListener() {
            val intent = Intent (this, ListActivity::class.java)
            startActivity(intent)

            //1.레퍼런스 가져오기
            val col = db.collection("post") //post컬렉션


            //2.접근하기(쓰기)
            val postTitle = etTitle.getText().toString()//게시글 작성에서 쓴 게시글이름
            val itemMap = hashMapOf(
                "title" to postTitle, //title필드에 게시글 이름 저장
                "content" to etContent.getText().toString() //content필드에 게시글 내용 저장
            )
            col.document(postTitle).set(itemMap) // 문서이름으로 document생성


            //3. 접근하기(읽기)
            col.addSnapshotListener{ value,error->
                for(d in value!!.documentChanges){
                    println("${d.type},${d.document.id},${d.document.data["text"]}")
                }
            }



        }
    }
}



