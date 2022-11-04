package com.example.sns_postit

import android.R.attr.password
import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class SecondActivity : AppCompatActivity() {
    //private lateinit var mAuth: FirebaseAuth
    private var mAuth : FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sign_up)
        mAuth = Firebase.auth


        /*private fun createAccount(et_Email: String, password: String) {

            if (et_Email.isNotEmpty() && password.isNotEmpty()) {
                mAuth?.createUserWithEmailAndPassword(et_Email, password)
                    ?.addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(
                                this, "계정 생성 완료.",
                                Toast.LENGTH_SHORT
                            ).show()
                            finish() // 가입창 종료
                        } else {
                            Toast.makeText(
                                this, "계정 생성 실패",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
            }
        }
        */
        var btn : Button = findViewById<Button>(R.id.sign_up_btn)

        btn.setOnClickListener() {
            val intent = Intent (this, MainActivity::class.java)
            startActivity(intent)
            //createAccount(et_Email.text.toString(),et_Password.text.toString())
        }
    }
}