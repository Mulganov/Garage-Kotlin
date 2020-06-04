package com.mulganov.test_task.garage_kotlin.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlin.random.Random

@Entity
class Element {
    companion object{
        fun random(): Element {
            var el = Element();

            el.name = "name: типо рандомное тут имя"
            el.telephon = "+380";

            for (i in 0..9){
                var r = Random.nextInt(10);
                el.telephon += r
            }

            return el;
        }
    }

    @PrimaryKey(autoGenerate = true)
    var key = 0
    var name: String? = null
    var telephon: String? = null

    constructor() {}


}