package com.yh.jetpack

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yh.jetpack.base.BaseActivity
import com.yh.jetpack.databinding.ActivityMainBinding
import com.yh.jetpack.view_binding.ViewBindingActivity

class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initMenuList()
    }

    /**
     * 初始化菜单列表
     */
    private fun initMenuList() {
        val dataSet = listOf<String>("Compose", "LiveData")
        binding.rvMenu.layoutManager = LinearLayoutManager(this)
        val adapter = CustomAdapter(dataSet.toTypedArray())
        adapter.setOnItemClick(object : CustomAdapter.OnItemClick {
            override fun onItemClick(position: Int) {
                when (position) {
                    0 -> {
                        goToPage()
                    }
                }
            }
        })
        binding.rvMenu.adapter = adapter
    }

    fun goToPage() {
        val intent = Intent(this, ViewBindingActivity::class.java)
        startActivity(intent)
    }

}

class CustomAdapter(private val dataSet: Array<String>) :
    RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    private var onItemClick: OnItemClick? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_rv_title, parent, false);
        return ViewHolder(view)
    }

    override fun getItemCount() = dataSet.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvContent.text = dataSet[position]
        holder.tvContent.setOnClickListener {
            onItemClick!!.onItemClick(position)
        }
    }

    fun setOnItemClick(onItemClick: OnItemClick) {
        this.onItemClick = onItemClick
    }


    interface OnItemClick {
        fun onItemClick(position: Int)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvContent: TextView = itemView.findViewById(R.id.tv_content)
    }
}