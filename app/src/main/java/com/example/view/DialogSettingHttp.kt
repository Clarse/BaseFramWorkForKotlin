package com.example.view

import android.app.Dialog
import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.TextUtils
import android.view.Gravity
import android.view.WindowManager
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.ListPopupWindow
import androidx.core.content.ContextCompat
import com.example.baseframeworkforkotlin.R
import com.example.utils.Extensions.showShortToast
import com.example.utils.Preference
import com.example.utils.clicks
import java.util.regex.Pattern

/**
 * @author zhaoli.Wang
 * @description:
 * @date :2021/7/26 14:03
 */
class DialogSettingHttp(context: Context, themeResId: Int) : Dialog(context, themeResId) {
    private val PROXY_HTTP_MATCH =
        "(http|ftp|https):\\/\\/[\\w\\-_]+(\\.[\\w\\-_]+)+([\\w\\-\\.,@?^=%&amp;:/~\\+#]*[\\w\\-\\@?^=%&amp;/~\\+#])?" //http正则表达式
    private var apiurl by Preference<String>("ApiUrl", "http://192.168.0.26:3019/")

    constructor(context: Context) : this(context, R.style.MyDialog)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_setting_http)
        window!!.setGravity(Gravity.CENTER)
        window!!.decorView.setPadding(0, 0, 0, 0)
        val lp = window!!.attributes
        lp.width = WindowManager.LayoutParams.MATCH_PARENT
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT
        window!!.attributes = lp
        init()
    }

    private fun init() {
        val et_url = findViewById<EditText>(R.id.et_url)
        val btncancle = findViewById<TextView>(R.id.bt_cancel)
        val buttonok = findViewById<TextView>(R.id.bt_ok)
        val iv_login = findViewById<ImageView>(R.id.iv_login)
        et_url.setText(apiurl)
        val list = arrayOf("http://122.51.182.66:3019/", "http://192.168.0.26:3019/")
        btncancle clicks { dismiss() }
        buttonok clicks {
            if (hasProxy(et_url.text.toString())) {
                apiurl = et_url.text.toString()
            } else {
                context.showShortToast("API服务器地址格式错误")
            }
            dismiss()
        }
        iv_login.clicks {
            showListPopulWindow(et_url, list)
        }
    }

    private fun showListPopulWindow(mEditText: EditText, list: Array<String>) {
        val listPopupWindow = ListPopupWindow(context)
        listPopupWindow.setAdapter(
            ArrayAdapter(
                context,
                R.layout.pop_item,
                list
            )
        ) //用android内置布局，或设计自己的样式
        listPopupWindow.anchorView = mEditText //以哪个控件为基准，在该处以mEditText为基准
        listPopupWindow.isModal = true
        listPopupWindow.setBackgroundDrawable(
            ColorDrawable(
                ContextCompat.getColor(
                    context, R.color.white
                )
            )
        )
        listPopupWindow.setOnItemClickListener { adapterView, view, i, l ->
            //设置项点击监听
            mEditText.setText(list[i]) //把选择的选项内容展示在EditText上
            listPopupWindow.dismiss() //如果已经选择了，隐藏起来
        }
        listPopupWindow.show() //把ListPopWindow展示出来
    }

    private fun hasProxy(proxyHttp: String?): Boolean {
        return !TextUtils.isEmpty(proxyHttp) && Pattern.compile(PROXY_HTTP_MATCH).matcher(proxyHttp)
            .matches()
    }

}