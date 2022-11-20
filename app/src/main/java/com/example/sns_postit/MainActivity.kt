package com.example.sns_postit

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.example.sns_postit.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

//첫화면 activity_main.xml
class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        /*
        binding.GoSignUp.setOnClickListener {
            val intent = Intent (this, SecondActivity::class.java)
            startActivity(intent)
        } */
        var btn : Button = findViewById<Button>(R.id.Go_SignUp)  //회원가입 버튼 누르면 회원가입 창 이동

        btn.setOnClickListener() {
            val intent = Intent (this, SecondActivity::class.java)
            startActivity(intent)
        }


        var btn2 : Button = findViewById<Button>(R.id.Loginbtn)
        btn2.setOnClickListener {
            val email = binding.EmailLogin.text.toString().trim()
            val password = binding.PasswordLogin.text.toString().trim()
            SignIn(email, password)

            //val intent = Intent (this, SuccessActivity2::class.java)
            //startActivity(intent)
        }
    }
    private fun SignIn(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithEmail:success")
                    Toast.makeText(this, "로그인 성공", Toast.LENGTH_SHORT).show()
                    println("############222222222################")
                    //val user = auth.currentUser
                    //updateUI(user)
                    val intent = Intent (this, ListActivity::class.java)
                    startActivity(intent)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                    //Toast.makeText(baseContext, "Authentication failed.", Toast.LENGTH_SHORT).show()
                    Toast.makeText(this, "로그인 실패", Toast.LENGTH_SHORT).show()
                    println("############3333333333################")
                    updateUI(null)
                }
            }
    }

    private fun updateUI(user: FirebaseUser?) {
        user?.let {
            println("############000000000000000################")
        }
    }
}