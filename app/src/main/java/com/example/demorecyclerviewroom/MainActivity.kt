package com.example.demorecyclerviewroom

import android.os.Bundle
import androidx.activity.*
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.demorecyclerview.databinding.ActivityMainBinding
import com.example.demorecyclerviewroom.room.AppViewModel

class MainActivity : ComponentActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: AppViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val adapter = MultiViewAdapter(viewModel)
        binding.recyclerview.adapter = adapter
        binding.recyclerview.layoutManager = LinearLayoutManager(this)
        viewModel.getData()
        viewModel.itemList.observe(this) { itemList ->
            itemList?.let {
                adapter.submitList(it)
            }
        }
        binding.btnClear.setOnClickListener{
            viewModel.clearData()
        }
        binding.btnAddData.setOnClickListener{
            viewModel.generateData()
        }
    }
}

