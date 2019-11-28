package com.example.databiding

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.databiding.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val myContact = Contact( "See","012345678")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        binding.contact = myContact
        //setContentView(R.layout.activity_main)
        binding.buttonSave.setOnClickListener {
            with(binding){
                contact?.name = editTextName.text.toString()
                contact?.phone = editTextPhone.text.toString()
                invalidateAll()
            }
        }
        binding.buttonSend.setOnClickListener {
            //create an Explicit Intent
            var intent = Intent(this,SecondActivity::class.java)
            //Prepare Extra data for the intent
            intent.putExtra(EXTRA_NAME,binding.contact?.name)
            intent.putExtra(EXTRA_PHONE,binding.contact?.phone)
            //startActivity(intent) // Your parent no return value
            startActivityForResult(intent, REQUEST_REPLY) //PTPTN expects Result

        }



    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode == REQUEST_REPLY){

            if(resultCode == Activity.RESULT_OK){
                val reply = data?.getStringExtra(EXTRA_REPLY)
                textViewReply.text = String.format("Reply : %s",reply )
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    companion object{
        const val EXTRA_NAME = "package com.example.databiding.Name"
        const val EXTRA_PHONE = "package com.example.databiding.Phone"
        const val REQUEST_REPLY = 1
        const val EXTRA_REPLY = "package com.example.databiding.Reply"

    }
}//End of class
