package com.heyaiz.heymov.sign.signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.*
import com.heyaiz.heymov.R
import com.heyaiz.heymov.sign.signin.User
import com.heyaiz.heymov.utils.Preferences
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {


    lateinit var sUsername: String
    lateinit var sPassword: String
    lateinit var sNama: String
    lateinit var sEmail: String

    lateinit var mFirebaseInstance: FirebaseDatabase
    lateinit var mDatabase: DatabaseReference
    lateinit var mDatabaseReference: DatabaseReference

    private lateinit var preferences: Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        mFirebaseInstance = FirebaseDatabase.getInstance()
        mDatabase = FirebaseDatabase.getInstance().getReference()
        mDatabaseReference =mFirebaseInstance.getReference("User")

        preferences = Preferences(this)

        btn_lanjutkan.setOnClickListener {

            sUsername = et_username.text.toString()
            sPassword = et_password.text.toString()
            sNama = et_nama.text.toString()
            sEmail = et_email.text.toString()

            if (sUsername.equals("")){
                et_username.error = "Silahkan isi username anda"
                et_username.requestFocus()
            }else if (sPassword.equals("")){
                et_password.error = "Silahkan isi password anda"
                et_password.requestFocus()
            }else if (sNama.equals("")){
                et_nama.error = "Silahkan isi nama anda"
                et_nama.requestFocus()
            }else if (sEmail.equals("")){
                et_email.error = "Silahkan isi email anda"
                et_email.requestFocus()
            }else
                saveUsername (sUsername, sPassword, sNama, sEmail)
        }

    }

    private fun saveUsername(sUsername: String, sPassword: String, sNama: String, sEmail: String) {
        val user = User()
        user.username = sUsername
        user.password = sPassword
        user.nama = sNama
        user.email = sEmail

        if (sUsername != null){
            checkingUsername(sUsername, user)
        }
    }

    private fun checkingUsername(sUsername: String, data: User) {
        mDatabaseReference.child(sUsername).addValueEventListener(object : ValueEventListener{
            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(this@SignUpActivity, ""+databaseError.message, Toast.LENGTH_LONG).show()
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val user = dataSnapshot.getValue(User::class.java)
                if (user == null){
                    mDatabaseReference.child(sUsername).setValue(data)

                    preferences.setValues("nama", data.nama.toString())
                    preferences.setValues("user", data.username.toString())
                    preferences.setValues("saldo", "")
                    preferences.setValues("url", "")
                    preferences.setValues("email", data.email.toString())
                    preferences.setValues("status", "1")

                    val goSignUpPhotoscreen = Intent(this@SignUpActivity,
                        SignUpPhotoscreenActivity::class.java).putExtra("data", data)
                    startActivity(goSignUpPhotoscreen)

                }else {
                    Toast.makeText(this@SignUpActivity, "User sudah digunakan", Toast.LENGTH_LONG).show()
                }
            }
        })

    }
}
