package com.mulganov.test_task.garage_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ListView
import com.mulganov.job.kotlin.list.MainAdaptor
import com.mulganov.test_task.garage_kotlin.db.Element

class MainActivity : AppCompatActivity() {

    lateinit var content: View;
    lateinit var present: MainPresent;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        content = findViewById(R.id.main);

        present = MainPresent(this);

        findViewById<View>(R.id.reload).setOnClickListener { present.reload() }

    }

    override fun onResume() {
        super.onResume()

//        hide();
    }

    private fun hide() {
        content.systemUiVisibility = (View.SYSTEM_UI_FLAG_LOW_PROFILE
                or View.SYSTEM_UI_FLAG_FULLSCREEN
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION)
    }

    fun reloadList(list: ArrayList<Element>){
        var lv = findViewById(R.id.list) as ListView

        lv.post { lv.adapter = MainAdaptor(this, list) }
    }

}
