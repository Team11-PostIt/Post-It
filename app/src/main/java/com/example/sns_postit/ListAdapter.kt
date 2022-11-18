package com.example.sns_postit

import android.content.Context
import android.widget.BaseAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

class MainListAdapter (val context: Context, val dogList: ArrayList<ListActivity.Dog>) : BaseAdapter() {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        /* LayoutInflater는 item을 Adapter에서 사용할 View로 부풀려주는(inflate) 역할을 한다. */
        val view: View = LayoutInflater.from(context).inflate(R.layout.activity_list_adapter, null)

        /* 위에서 생성된 view를 res-layout-main_lv_item.xml 파일의 각 View와 연결하는 과정이다. */
        val dogPhoto = view.findViewById<ImageView>(R.id.dogPhotoImg) //사진
        val dogBreed = view.findViewById<TextView>(R.id.dogBreedTv)  //게시글제목
        val dogAge = view.findViewById<TextView>(R.id.dogAgeTv) //게시글 올린 시간
        val dogGender = view.findViewById<TextView>(R.id.dogGenderTv) //내용

        /* ArrayList<Dog>의 변수 dog의 이미지와 데이터를 ImageView와 TextView에 담는다. */
        val dog = dogList[position]
        val resourceId = context.resources.getIdentifier(dog.photo, "drawable", context.packageName)
        dogPhoto.setImageResource(resourceId)
        dogBreed.text = dog.breed
        dogAge.text = dog.age
        dogGender.text = dog.gender

        return view
    }

    override fun getItem(position: Int): Any {
        return dogList[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return dogList.size
    }
}