package com.gauravbora.flashlight

import android.hardware.camera2.CameraManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.getSystemService

class MainActivity : AppCompatActivity() {
    private lateinit var flash:ImageView
    private var isFlash=false
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        flash=findViewById(R.id.flash)

        lightState(isFlash)

        flash.setOnClickListener{
            if(!isFlash){
                flash.setImageResource(R.drawable.ic_baseline_highlight_24)
                isFlash=true
                lightState(isFlash)
            }
            else{
                flash.setImageResource(R.drawable.ic_outline_highlight_24)
                isFlash=false
                lightState(isFlash)
            }
        }




    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun lightState(state: Boolean) {

        val cameraManager:CameraManager=getSystemService(CAMERA_SERVICE) as CameraManager
        var cameraid:String?=null

//ID->      0 for back camera and 1 for front camera

        try{
            cameraid=cameraManager.cameraIdList[0]
            cameraManager.setTorchMode(cameraid,state)
        }catch (e:Exception){
            Toast.makeText(this,e.message,Toast.LENGTH_SHORT).show()
        }

    }
}