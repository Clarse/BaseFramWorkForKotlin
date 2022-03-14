package com.example.base

import android.app.Activity
import java.util.*

object AppManager {

    val activityStack = Stack<Activity>()

    fun getCount(): Int {
        return activityStack.size
    }

    fun topActivity(): Activity? {
        if (activityStack.isEmpty()) {
            return null
        }
        return activityStack.lastElement()
    }

    inline fun <reified T : Any> findActivity(clazz: Class<T>): Activity? {
        var activity: Activity? = null
        activityStack.forEach {
            if (it.javaClass == clazz) {
                activity = it
                return@forEach
            }
        }
        return activity
    }

    fun addActivity(activity: Activity) {
        activityStack.add(activity)
    }

    fun finishActivity() {
        finishActivity(activityStack.lastElement())
    }

    fun finishActivity(activity: Activity) {
        activityStack.remove(activity)
        activity.finish()
    }

    fun removeActivity(activity: Activity) {
        activityStack.remove(activity)
    }

    inline fun <reified T : Any> finishActivity(clazz: Class<T>) {
        activityStack.forEach {
            if (it.javaClass == clazz)
                finishActivity(it)
            return@forEach
        }
    }

    fun exit() {
        activityStack.forEach {
            it.finish()
        }
        activityStack.clear()
    }

}