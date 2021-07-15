package com.example.week_7_1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.week_7_1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    lateinit var adapter:MyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerView()
    }

    private fun initRecyclerView(){
        binding.recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        adapter = MyAdapter(ArrayList<MyData>())

        val intent = Intent(Intent.ACTION_MAIN)
        intent.addCategory(Intent.CATEGORY_LAUNCHER)
        val applist = packageManager.queryIntentActivities(intent, 0)
        if(applist.size > 0){
            for(appinfo in applist){
                val applabel = appinfo.loadLabel(packageManager)
                val appclass = appinfo.activityInfo.name
                val apppackname = appinfo.activityInfo.packageName
                val appicon = appinfo.loadIcon(packageManager)
                adapter.items.add(MyData(applabel.toString(), appclass, apppackname, appicon))
            }
        }

        adapter.listener = object:MyAdapter.OnItemClickListener{
            override fun onItemClick(
                holder: RecyclerView.ViewHolder,
                view: View,
                data: MyData,
                position: Int
            ) {
                val intent = packageManager.getLaunchIntentForPackage(data.apppackname)
                startActivity(intent)
            }

        }
        binding.recyclerView.adapter = adapter
    }
}
