package com.mulganov.job.kotlin.list

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.mulganov.test_task.garage_kotlin.MainActivity
import com.mulganov.test_task.garage_kotlin.R
import com.mulganov.test_task.garage_kotlin.model.Element


class MainAdaptor(context: Context, list: ArrayList<Element>) : BaseAdapter() {
    internal var sList = ArrayList<Element>()
    private val mInflator: LayoutInflater

    var activity: MainActivity


    init {
        activity = context as MainActivity
        this.mInflator = LayoutInflater.from(context)
        for (product in list){

            sList.add(product)
        }

    }

    override fun getCount(): Int {
        return sList.size
    }

    override fun getItem(position: Int): Any {
        return sList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
        val view: View?
        val vh: ListRowHolder
        if (convertView == null) {
            view = this.mInflator.inflate(R.layout.item, parent, false)
            vh = ListRowHolder(view)
            view.tag = vh
        } else {
            view = convertView
            vh = view.tag as ListRowHolder
        }

        vh.name.text = sList[position].name
        vh.telephon.text = sList[position].telephon

        vh.edit.setOnClickListener{activity.present.edit(sList[position])}
        vh.delete.setOnClickListener { (activity.present.delete(position)) }

        view?.setOnClickListener{activity.present.edit(sList[position])}
        return view
    }
}

private class ListRowHolder(row: View?) {
    var name: TextView
    var telephon: TextView
    var edit: ImageView
    var delete: View

    init {
        name = row?.findViewById(R.id.iText_name) as TextView
        telephon = row?.findViewById(R.id.iText_telephon) as TextView
        edit = row?.findViewById(R.id.iImage_edit) as ImageView
        delete = row?.findViewById(R.id.iImage_delete) as ImageView
    }
}
