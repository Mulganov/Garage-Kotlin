package com.mulganov.test_task.garage_kotlin.model

import androidx.room.Room
import com.mulganov.test_task.garage_kotlin.MainPresent
import com.mulganov.test_task.garage_kotlin.db.DB
import com.mulganov.test_task.garage_kotlin.db.Element

class Room {

    var elements = ArrayList<Element>()

    var presenter: MainPresent;

    var nameBD = "Garage"

    constructor(present: MainPresent){
        this.presenter = present;

        Thread(Runnable {
            val db: DB = Room.databaseBuilder<DB>(
                presenter.activity,
                DB::class.java, nameBD
            ).build()

            for (el in db.elementDoa?.allElement!!){
                if (el != null) {
                    elements.add(el)
                }
            }

            if (elements.size == 0){
                generateList(5);

                save()
            }

            present.activity.reloadList(elements)
        }).start()
    }

    fun save(){
        Thread(Runnable {
            val db: DB = Room.databaseBuilder<DB>(
                presenter.activity,
                DB::class.java, nameBD
            ).build()

            for (el in db.elementDoa?.allElement!!){
                if (el != null) {
                    db.elementDoa?.delete(el)
                }
            }

            for (el in elements){
                db.elementDoa?.insertAll(el)
            }
        }).start()
    }

    fun generateList(n: Int) {
        for (i in 0..n){
            elements.add(Element.random())
        }

    }
}