package com.example.sharedpreferences

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private lateinit var editTextName: EditText
    private lateinit var editTextAge: EditText
    private lateinit var `save-btn`: Button
    private lateinit var `load-btn`: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        `save-btn` = findViewById(R.id.savebtn);
        `load-btn` = findViewById(R.id.loadbtn);
        editTextAge = findViewById(R.id.editText2);
        editTextName = findViewById(R.id.editText);

        val sharepref =  getSharedPreferences("file" , Context.MODE_PRIVATE)
        `save-btn`.setOnClickListener {
            val name = editTextName.text.toString()
            val age = editTextAge.text.toString().toIntOrNull()

            if (name.isNotEmpty() && age != null) {
                val editor = sharepref.edit()
                editor.putString("name", name)
                editor.putInt("age", age)
                editor.apply()

                Toast.makeText(this, "Data saved!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Please enter valid data!", Toast.LENGTH_SHORT).show()
            }
        }
        `load-btn`.setOnClickListener {
            val username = sharepref.getString("name", "null")
            val age = sharepref.getInt("age", 0)

            if (username!!.isNotEmpty()) {
                editTextName.setText(username)
                editTextAge.setText(age.toString())
                Toast.makeText(this, "Data retrieved!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "No data found!", Toast.LENGTH_SHORT).show()
            }
        }


    }


}
