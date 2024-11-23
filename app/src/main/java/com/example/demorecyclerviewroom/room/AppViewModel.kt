package com.example.demorecyclerviewroom.room

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.demorecyclerview.R
import com.example.demorecyclerviewroom.*
import com.example.demorecyclerviewroom.retrofit.ApiInterface
import com.example.demorecyclerviewroom.retrofit.RetrofitInstance
import kotlinx.coroutines.launch

class AppViewModel(app: Application): AndroidViewModel(app) {

    private val repo = (app as App).appRepo

    private lateinit var apiInterface: ApiInterface

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
            getData()
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
           for (i in 0..10){
                val companyId = addItem(Company(name = "Company ${i + 1}", number = "+3806123456${i + 1}", email = "company${i + 1}@gmail.com"))
           }//add correct getuserdata()
        }
    }
    private suspend fun getUserData(companyId: Long) {
        try {
            apiInterface = RetrofitInstance.getInstance().create(ApiInterface:: class.java)
            val users = apiInterface.getUserData()
                addItem(
                    User(
                        image = R.drawable.user5,
                        name = users.name,
                        email = users.email,
                        companyId = companyId)
                )
        } catch (e: Exception) {
            Log.e("API_ERROR", "Request failed: ${e.message}")
        }
    }

}