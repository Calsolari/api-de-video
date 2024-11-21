package br.senai.sp.jandira.myvideocall

import android.app.Application
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.zegocloud.uikit.prebuilt.call.ZegoUIKitPrebuiltCallService
import com.zegocloud.uikit.prebuilt.call.invite.ZegoUIKitPrebuiltCallInvitationConfig

class LoginActivity : AppCompatActivity() {

    private lateinit var myUserId: EditText
    private lateinit var loginButton: Button

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        myUserId = findViewById(R.id.myUserId)
        loginButton = findViewById(R.id.loginButton)

        loginButton.setOnClickListener {
            val myUserId = myUserId.text.toString()
            if (myUserId.isNotEmpty()){
                val intent = Intent(this@LoginActivity, MainActivity::class.java)
                intent.putExtra("userID", myUserId)
                startActivity(intent)

                setupZegoUIKit(myUserId)
            }
        }


    }

    private fun setupZegoUIKit(userID: String){
        val application: Application = application
        val appID: Long = 1212991992
        val appSign: String = "4d8c3b893a6deef9dafe73b70cd996f99070c0062549dab7ae7bff729225be21"
        val userName: String = userID
        val callInvitationConfig = ZegoUIKitPrebuiltCallInvitationConfig()
        ZegoUIKitPrebuiltCallService.init(application, appID, appSign, userID, userName, callInvitationConfig)

    }

    override fun onDestroy() {
        super.onDestroy()
        ZegoUIKitPrebuiltCallService.unInit()
    }
}