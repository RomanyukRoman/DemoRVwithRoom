package com.example.demorecyclerviewroom.room

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.demorecyclerview.R
import com.example.demorecyclerviewroom.*
import kotlinx.coroutines.launch

class AppViewModel(app: Application): AndroidViewModel(app) {

    private val repo = (app as App).appRepo

    private val _itemList = MutableLiveData<ArrayList<ItemTypeInterface>>()
    val itemList: LiveData<ArrayList<ItemTypeInterface>> get() = _itemList

    private suspend fun addItem(item: ItemTypeInterface): Long {
        return repo.addItem(item)
    }

    fun getData(){
        viewModelScope.launch {
            val dataList = ArrayList<ItemTypeInterface>()
            dataList.addAll(repo.getCompanies())
            dataList.addAll(repo.getUsers())
            _itemList.value = dataList
        }
    }

    fun clearData(){
        viewModelScope.launch {
            repo.clearData()
        }
    }

    fun deleteItem(item: ItemTypeInterface) {
        viewModelScope.launch {
            repo.deleteItem(item)
            getData()
        }
    }

    fun generateData(){
        viewModelScope.launch {
            for (i in 1..10) {
                val companyId = addItem(Company(name = "Company $i", number = "+3806123456$i", email = "company$i@gmail.com"))
                addItem(User(image = R.drawable.user5, name = "User $i", email = "user$i.gmail.com", companyId = companyId))
            }
        }
    }
}