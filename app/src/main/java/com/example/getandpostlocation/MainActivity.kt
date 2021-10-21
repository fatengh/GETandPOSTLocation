package com.example.getandpostlocation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    val apiInterface = APIClient().getClient()?.create(ApiInterface::class.java)
    private lateinit var edName: EditText
    private lateinit var edLoc: EditText
    private lateinit var edGet: EditText
    private lateinit var btnPost: Button
    private lateinit var btnGetLoc: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnPost.setOnClickListener {
            val newUser = DataItem(edName.text.toString(), edLoc.text.toString())
            if (apiInterface != null) {
                apiInterface.addUser(newUser).enqueue(object : Callback<DataItem?> {
                    override fun onResponse(call: Call<DataItem?>, response: Response<DataItem?>) {
                        edName.text.clear()
                        edLoc.text.clear()
                        Toast.makeText(applicationContext, "user added", Toast.LENGTH_SHORT).show()
                    }

                    override fun onFailure(call: Call<DataItem?>, t: Throwable) {
                        Toast.makeText(applicationContext, "Error ", Toast.LENGTH_SHORT).show()
                    }
                })
            }
        }


        btnGetLoc.setOnClickListener {
            if (apiInterface != null) {
                apiInterface.getUser()?.enqueue(object : Callback<Array<DataItem>?> {
                    override fun onResponse(
                        call: Call<Array<DataItem>?>,
                        response: Response<Array<DataItem>?>
                    ) {
                        for (user in response.body()!!) {
                            if (user.name == edGet.text.toString()) {
                                tv.text = user.location
                            }
                        }
                    }

                    override fun onFailure(call: Call<Array<DataItem>?>, t: Throwable) {
                        Toast.makeText(applicationContext, "${t.message}", Toast.LENGTH_SHORT)
                            .show()
                    }
                })
            }
        }
    }


}