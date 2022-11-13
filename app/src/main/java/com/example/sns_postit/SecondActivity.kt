package com.example.sns_postit

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.sns_postit.databinding.SignUpBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser


class SecondActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    private var _binding: SignUpBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val binding = SignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        _binding = SignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        binding.SignUpButton.setOnClickListener {
            val email = binding.EditTextEmail.text.toString().trim()
            val password = binding.EditTextPassword.text.toString().trim()

            createUser(email, password)
        }

        var btn : Button = findViewById<Button>(R.id.GoBack)  // 로그인 화면으로 이동

        btn.setOnClickListener() {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
    private fun createUser(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "회원가입 성공", Toast.LENGTH_SHORT).show()
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    Toast.makeText(this, "회원가입 실패", Toast.LENGTH_SHORT).show()
                    updateUI(null)
                }
            }
            .addOnFailureListener {
                Toast.makeText(this, "회원가입 실패", Toast.LENGTH_SHORT).show()
            }
    }

    private fun updateUI(user: FirebaseUser?) {
        user?.let {
            binding.textView.text = "Email: ${user.email}\nUid: ${user.uid}"
            println("#############11111111###############")
            binding.SignUpButton.setOnClickListener {
                val intent = Intent (this, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}