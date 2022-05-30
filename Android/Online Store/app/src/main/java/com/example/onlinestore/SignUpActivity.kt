package com.example.onlinestore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        // Changing activity when clicking on login
        activity_sign_up_btnLogin.setOnClickListener {
            finish()
        }

        // SignUp the user
        activity_sign_up_btnSignUp.setOnClickListener {
            if (activity_sign_up_edtPassword.text.toString() == activity_sign_up_edtConfirm.text.toString()){

                // Registration Process
                val signUpURL = "http://192.168.83.48/OnlineStoreApp/join_new_user.php?email=" +
                        activity_sign_up_edtEmail.text.toString() +
                        "&username=" + activity_sign_up_edtUsername.text.toString() +
                        "&pass=" + activity_sign_up_edtPassword.text.toString()

                val requestQ = Volley.newRequestQueue(this@SignUpActivity)
                val stringRequest = StringRequest(Request.Method.GET, signUpURL, { response->
                    if (response.equals("A user with this e-mail address already exists")){
                        val dialogBuilder = AlertDialog.Builder(this)
                        dialogBuilder.setTitle("Alert")
                        dialogBuilder.setMessage(response)
                        dialogBuilder.create().show()
                    } else {
                        val dialogBuilder = AlertDialog.Builder(this)
                        dialogBuilder.setTitle("Alert")
                        dialogBuilder.setMessage(response)
                        dialogBuilder.create().show()
                    }
                    
                }, { error ->
                    val dialogBuilder = AlertDialog.Builder(this)
                    dialogBuilder.setTitle("Alert")
                    dialogBuilder.setMessage(error.message)
                    dialogBuilder.create().show()
                })

                requestQ.add(stringRequest)

            } else{
                val dialogBuilder = AlertDialog.Builder(this)
                dialogBuilder.setTitle("Alert")
                dialogBuilder.setMessage("Password Mismatch")
                dialogBuilder.create().show()
            }
        }
    }
}