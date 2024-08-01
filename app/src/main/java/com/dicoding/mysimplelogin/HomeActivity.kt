package com.dicoding.mysimplelogin

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.mysimplelogin.databinding.ActivityHomeBinding
import com.dicoding.core.SessionManager
import com.dicoding.core.UserRepository

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var userRepository: UserRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.fab.setOnClickListener {
            try {
                moveToChatActivity()
            } catch (e: Exception) {
                Toast.makeText(this, "Module not found", Toast.LENGTH_SHORT).show()
            }
        }

        val sesi = SessionManager(this)
        userRepository = UserRepository.getInstance(sesi)

        binding.tvWelcome.text = "Welcome ${userRepository.getUser()}"

        binding.btnLogout.setOnClickListener {
            userRepository.logoutUser()
            moveToMainActivity()
        }
    }

    private fun moveToMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    private fun moveToChatActivity() {
        startActivity(Intent(this, Class.forName("com.dicoding.mysimplelogin.chat.ChatActivity")))
    }
}
