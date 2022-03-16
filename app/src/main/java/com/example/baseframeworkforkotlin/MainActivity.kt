package com.example.baseframeworkforkotlin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.base.BaseActivity
import com.example.base.BaseViewModel
import com.example.baseframeworkforkotlin.databinding.ActivityMainBinding
import java.util.*

class MainActivity : BaseActivity<BaseViewModel,ActivityMainBinding>() {
    lateinit var list: List<String>
    var a = 1
    var b = 2
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val date = Date()

        pascal().take(10).forEach(::println)
        a = b.also {
            b = a
        }
        println("a = $a b = $b") // a = 2 b = 1
    }

    //杨辉三角
    private fun pascal() = generateSequence(listOf(1)) { prev ->
        listOf(1) + (1..prev.lastIndex).map { prev[it - 1] + prev[it] } + listOf(1)
    }

    override fun isNeedEventBus(): Boolean {
        return false
    }

    override fun initView() {

    }

    override fun initClick() {

    }

    override fun initData() {

    }

    override fun initVM() {

    }

}