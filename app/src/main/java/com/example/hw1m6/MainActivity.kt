package com.example.hw1m6

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import com.example.hw1m6.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding

    companion object{
        const val KEY_SEND = "KEY"
        const val KEY_back = "KEY-result"
    }
    private lateinit var launchResult:ActivityResultLauncher<Intent>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
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
                Toast.makeText(this,"error",Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun intent(text: String) {

        val intent = Intent(this,ResultActivity::class.java)
        intent.putExtra(KEY_SEND,text)
        launchResult.launch(intent)
    }

    private fun result() {
launchResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result ->
    if (result.resultCode ==Activity.RESULT_OK){
        val intent :Intent? = result.data
        val title  = intent?.getStringExtra(KEY_back)
        binding.edit.setText(title)
    }
}

    }
}