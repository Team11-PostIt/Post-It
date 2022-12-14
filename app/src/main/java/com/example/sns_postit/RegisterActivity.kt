package com.example.sns_postit

import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.sns_postit.databinding.ActivityMainBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

//글 작성하기 화면 activity_register.xml
class RegisterActivity : AppCompatActivity() {

    private val GET_GALLERY_IMAGE = 200
    private var imageview: ImageView? = null
    val selectedImageUri =null

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == GET_GALLERY_IMAGE && resultCode == RESULT_OK && data != null && data.data != null) {
            val selectedImageUri = data.data
            imageview!!.setImageURI(selectedImageUri)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        val db = Firebase.firestore

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        imageview = findViewById<View>(R.id.image_upload) as ImageView
        imageview!!.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*")
            startActivityForResult(intent, GET_GALLERY_IMAGE)
        }

        //val uploadBtn : ImageView = findViewById<ImageView>(R.id.image_upload)
        //val imageView : ImageView = findViewById(R.id.image_upload)


        var btn : Button = findViewById(R.id.write_button)  // 등록하기 버튼, 작성한 글 등록됨
        val etTitle : EditText = findViewById(R.id.title_et)
        val etContent : EditText = findViewById(R.id.content_et)


        btn.setOnClickListener{
            val intent = Intent (this, ListActivity::class.java)
            startActivity(intent)

            //1.레퍼런스 가져오기
            val col = db.collection("post") //post컬렉션


            //2.접근하기(쓰기)
            val postTitle = etTitle.getText().toString()//게시글 작성에서 쓴 게시글이름
            val image_uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI.toString()

            val itemMap = hashMapOf(
                "name" to postTitle, //title필드에 게시글 이름 저장
                "content" to etContent.getText().toString(), //content필드에 게시글 내용 저장
                "image" to image_uri
            )
            col.document(postTitle).set(itemMap) // 문서이름으로 document생성


            //3. 접근하기(읽기) ->ListActivity로
            /*
            col.addSnapshotListener{ value,error->
                for(d in value!!.documentChanges){
                    println("${d.type},${d.document.id},${d.document.data["text"]}")
                }
            }*/
        }
    }
}



