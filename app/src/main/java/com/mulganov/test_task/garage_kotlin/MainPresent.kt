package com.mulganov.test_task.garage_kotlin

import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.LinearLayout
import android.widget.PopupWindow
import android.widget.TextView
import androidx.core.content.ContextCompat.getSystemService
import com.mulganov.test_task.garage_kotlin.db.Element
import com.mulganov.test_task.garage_kotlin.model.Room
import kotlinx.android.synthetic.main.activity_main.*


class MainPresent {

    var activity: MainActivity;
    var room: Room;

    var clickableList = true;

    constructor(a: MainActivity){
        activity = a;
        room = Room(this)


        activity.findViewById<View>(R.id.background).visibility = View.INVISIBLE
    }

    fun edit(el : Element){
        if (!clickableList) return

        val inflater =
            activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val popupView: View = inflater.inflate(R.layout.contact, null)

        val width = LinearLayout.LayoutParams.WRAP_CONTENT
        val height = LinearLayout.LayoutParams.WRAP_CONTENT

        popupView.findViewById<TextView>(R.id.contact_name).text = el.name
        popupView.findViewById<TextView>(R.id.contact_telephon).text = el.telephon

        val popupWindow = PopupWindow(popupView, width, height, true)

        popupWindow.showAtLocation(activity.content, Gravity.CENTER, 0, 0)

        popupView.findViewById<View>(R.id.contact_back)
            .setOnClickListener { view: View? ->
                popupWindow.dismiss()
            }

        popupView.findViewById<View>(R.id.contact_accept)
            .setOnClickListener { view: View? ->
                var name = popupView.findViewById<TextView>(R.id.contact_name).text.toString()
                var tel = popupView.findViewById<TextView>(R.id.contact_telephon).text.toString()

                for (e in room.elements){
                    if (e.key == el.key){
                        e.telephon = tel;
                        e.name = name;
                    }
                }
                popupWindow.dismiss()
                room.save()
                activity.reloadList(room.elements)
            }

        activity.findViewById<View>(R.id.background).visibility = View.VISIBLE

        clickableList = false;

        popupWindow.setOnDismissListener {
            activity.findViewById<View>(R.id.background).visibility = View.INVISIBLE

            clickableList = true;

            activity.content.setOnClickListener {  }
        }
    }

    fun delete(position: Int){
        if (!clickableList) return

        room.elements.removeAt(position)
        room.save()

        activity.reloadList(room.elements)
    }

    fun reload() {
        room.elements = ArrayList();

        room.generateList(5)

        room.save();

        activity.reloadList(room.elements)
    }

}

