package com.example.demorecyclerviewroom.room

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.demorecyclerview.R
import com.example.demorecyclerviewroom.*
import com.example.demorecyclerviewroom.retrofit.ApiInterface
import com.example.demorecyclerviewroom.retrofit.RetrofitInstance
import kotlinx.coroutines.launch

class AppViewModel(app: Application): AndroidViewModel(app) {

    private val repo = (app as App).appRepo

    private val apiInterface: ApiInterface = RetrofitInstance.getInstance().create(ApiInterface:: class.java)

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
            val rand = (0..9).random()
            val companyId = addItem(Company(
                                    name = "Company $rand",
                                    number = "+3806123456$rand",
                                    email = "company$rand@gmail.com"))
            getUser(companyId, rand)
            getData()
        }
    }
    private suspend fun getUser(companyId: Long, rand: Int) {
        try {
            val users = apiInterface.getUser()
                addItem(User(
                        image = R.drawable.user5,
                        name = users[rand].name,
                        email = users[rand].email,
                        companyId = companyId)
                )
        } catch (e: Exception) {
            Log.e("API_ERROR", "Request failed: ${e.message}")
        }
    }
}