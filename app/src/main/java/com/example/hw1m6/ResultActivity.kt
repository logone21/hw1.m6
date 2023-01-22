package com.example.hw1m6

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import com.example.hw1m6.databinding.ActivityMainBinding
import com.example.hw1m6.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding
    companion object{
        const val KEY_SEND = "KEY"
        const val KEY_back = "KEY-result"
    }
    private lateinit var launchResult: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        start()
        result()
    }
    private fun start() {
        binding.btnNext.setOnClickListener {
            val text = binding.edit.text.toString()
            if (text.isNotEmpty()){
                intent(text)
            }else{
                Toast.makeText(this,"error", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun intent(text: String) {

        val intent = Intent()
        intent.putExtra(KEY_back,text)
        setResult(RESULT_OK,intent)
        finish()

    }

    private fun result() {
val extras :Bundle? = intent.extras
        val title = extras?.getString(KEY_SEND)
        binding.edit.setText(title)

    }
}