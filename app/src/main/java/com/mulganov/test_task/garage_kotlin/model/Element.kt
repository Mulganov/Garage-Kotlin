package com.mulganov.test_task.garage_kotlin.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlin.random.Random

@Entity
class Element {
    companion object{
        fun random(): Element {
            var el = Element();

            el.name = "типо рандомное имя"
            el.telephon = "";

            for (i in 0..7){
                var r = Random.nextInt(90-65) + 65;
                el.telephon += r.toChar()
            }

            el.telephon = el.telephon!!.toLowerCase() + "@gmail.com";

            return el;
        }
    }

    @PrimaryKey(autoGenerate = true)
    var key = 0
    var name: String? = null
    var telephon: String? = null

    constructor() {}


}