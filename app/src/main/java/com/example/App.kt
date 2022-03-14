package com.example

import android.app.Application
import com.example.event.Event
import com.example.event.EventMessage

/**
 *@author: YH
 *@date: 2022/3/14
 *@desc:
 */
class App : Application() {

    companion object {
        var DEBUG: Boolean = false
        lateinit var instance: App

        fun post(eventMessage: EventMessage) {
            Event.getInstance().post(eventMessage)
        }
    }

}