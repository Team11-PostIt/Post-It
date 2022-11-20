package com.example.sns_postit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sns_postit.databinding.ActivityListBinding
import com.example.sns_postit.databinding.ItemBinding
import com.google.firebase.firestore.FirebaseFirestore


class ListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListBinding
    var firestore : FirebaseFirestore? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // 파이어스토어 인스턴스 초기화
        firestore = FirebaseFirestore.getInstance()

        binding.recyclerview.adapter = RecyclerViewAdapter()
        binding.recyclerview.layoutManager = LinearLayoutManager(this)

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
    inner class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewAdapter.TodoViewHolder>() {
        // Person 클래스 ArrayList 생성성
        var telephoneBook : ArrayList<Person> = arrayListOf()

        init {  // telephoneBook의 문서를 불러온 뒤 Person으로 변환해 ArrayList에 담음
            firestore?.collection("post")?.addSnapshotListener { querySnapshot, firebaseFirestoreException ->
                // ArrayList 비워줌
                telephoneBook.clear()

                for (snapshot in querySnapshot!!.documents) {
                    var item = snapshot.toObject(Person::class.java)
                    telephoneBook.add(item!!)
                }
                notifyDataSetChanged()
            }
        }
        inner class TodoViewHolder(val binding: ItemBinding) : RecyclerView.ViewHolder(binding.root)

        // xml파일을 inflate하여 ViewHolder를 생성
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item, parent, false)
            return TodoViewHolder(ItemBinding.bind(view))
        }

        // onCreateViewHolder에서 만든 view와 실제 데이터를 연결
        override fun onBindViewHolder(viewHolder: TodoViewHolder, position: Int) {
            //var viewHolder = (holder as ViewHolder).itemView

            viewHolder.binding.name.text = telephoneBook[position].name
            viewHolder.binding.content.text = telephoneBook[position].content
        }

        // 리사이클러뷰의 아이템 총 개수 반환
        override fun getItemCount(): Int {
            return telephoneBook.size
        }
    }
}




