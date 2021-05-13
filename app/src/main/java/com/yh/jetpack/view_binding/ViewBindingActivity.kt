package com.yh.jetpack.view_binding

import android.os.Bundle
import com.yh.jetpack.base.BaseActivity
import com.yh.jetpack.databinding.ActivityViewBindingBinding

/**
 * 展示视图绑定库的使用
 * ViewBinding是为了取代findViewById，具有空安全和类型安全的优势
 * 之后如果使用了Compose，这种方式可能会被弃用？
 */
class ViewBindingActivity : BaseActivity() {

    private lateinit var binding: ActivityViewBindingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewBindingBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.tvDemo.text = "ViewBinding 演示"
    }

}