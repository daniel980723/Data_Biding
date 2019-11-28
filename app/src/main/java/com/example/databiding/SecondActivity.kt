package com.example.databiding

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val name = intent.getStringExtra(MainActivity.EXTRA_NAME)
        textViewReceivedName.text = name
        val phone = intent.getStringExtra(MainActivity.EXTRA_PHONE)
        textReceivedPhone.text = phone

        buttonDone.setOnClickListener {
            if(editTextReply.text.isEmpty()){
                val reply = editTextReply.text.toString()
                val intent = getIntent()
                intent.putExtra(MainActivity.EXTRA_REPLY,reply)
                setResult(Activity.RESULT_OK,intent)
            }else{

                setResult(Activity.RESULT_CANCELED,intent)
            }
            finish()
        }
        buttonCall.setOnClickListener {
            //Create am Implicit Intent
            val phone = Uri.parse("tel:" + textReceivedPhone.text.toString())
            val intent = Intent(Intent.ACTION_VIEW)
            intent.setData(phone)
            if(intent.resolveActivity(packageManager)!=null){
                startActivity(intent)
            }
            else
                error("You Dont Have APP To Perform This Action")

        }
    }
}
