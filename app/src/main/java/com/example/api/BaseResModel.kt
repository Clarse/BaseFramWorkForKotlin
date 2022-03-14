package com.example.api

class BaseResModel<T> {
    var code = 0
    var msg: String? = null
    var data: DataBean<T>? = null

    open class DataBean<T> {
        var count = 0
        var list: ArrayList<T>? = null
    }
}