package com.yushika.colok

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.yushika.colok.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainActivity : AppCompatActivity() {
    lateinit var bind:ActivityMainBinding
    private lateinit var timeformat:SimpleDateFormat
    private var handler=Handler()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind=ActivityMainBinding.inflate(layoutInflater)

        setContentView(bind.root)



        timeformat= SimpleDateFormat("HH:mm:ss", Locale.getDefault())

        handler.post(object :Runnable{
            override fun run() {
             updateUI()
                handler.postDelayed(this,1000)
            }
        })
    }

    private fun updateUI() {
        val currenttime=System.currentTimeMillis()
        val formattedtime=timeformat.format(Date(currenttime))
        bind.degetalclock.text=formattedtime
       var hr=formattedtime.substring(0,2).toInt()
        var min=formattedtime.substring(3,5).toInt()
        var sec=formattedtime.substring(6).toInt()
        rotateimagewhithshift(bind.hr,tomap(hr,12),0.5f,1.0f)
        rotateimagewhithshift(bind.min,tomap(min,60),0.5f,1.0f)
        rotateimagewhithshift(bind.sec,tomap(sec,60),0.5f,1.0f)

    }
    private fun rotateimagewhithshift(imageview:ImageView,degrees:Float,pivotxvalue:Float,pivotyvalue:Float)
    {
        imageview.pivotX=imageview.width*pivotxvalue
        imageview.pivotY=imageview.height*pivotyvalue
        imageview.animate().rotation(degrees)

    }
    private fun tomap(value:Int,max:Int):Float{
        return (value%max).toFloat()/max*360f
    }

}