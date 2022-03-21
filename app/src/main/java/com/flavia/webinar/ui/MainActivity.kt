package com.flavia.webinar.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.flavia.webinar.R
import com.flavia.webinar.databinding.ActivityMainBinding
import com.flavia.webinar.entity.Categories
import com.flavia.webinar.extensions.parseJsonFromResource
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val adapter: Adapter by lazy {
        Adapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bind()
        initRecyclerView()
    }

    private fun bind(){
        this.binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(this.binding.root)
    }

    private fun initRecyclerView(){
        this.binding.rvDemo.adapter = this.adapter
        this.adapter.list = getList()
        this.adapter.categoryClick = {
            Toast.makeText(this, "Su id es ${it.id} y su nombre es ${it.name}", Toast.LENGTH_SHORT).show()
        }
    }

    private fun getList(): List<Categories> {
        val list = mutableListOf<Categories>()
        this.parseJsonFromResource("list.json"){
            val jsonList = JSONArray(it)
            for (i in 0 until jsonList.length()) {
                val jsonObject: JSONObject = jsonList.getJSONObject(i)
                val id      = jsonObject.getInt("id")
                val name    = jsonObject.getString("name")

                list.add(Categories(id, name))
                println(name)
            }
        }
        return list
    }
}