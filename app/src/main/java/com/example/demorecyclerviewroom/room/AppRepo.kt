package com.example.demorecyclerviewroom.room

import com.example.demorecyclerviewroom.Company
import com.example.demorecyclerviewroom.ItemTypeInterface
import com.example.demorecyclerviewroom.User

class AppRepo (
    private val appDao: AppDao
) {
    suspend fun addItem(item: ItemTypeInterface): Long {
        return when (item.getType()) {
            ItemTypeInterface.USER_TYPE -> appDao.insertUser(item as User)
            ItemTypeInterface.COMPANY_TYPE -> appDao.insertCompany(item as Company)
            else -> -1
        }
    }

    suspend fun clearData() {
        appDao.clearUsers()
        appDao.clearCompanies()
    }

    suspend fun deleteItem(item: ItemTypeInterface) {
        when (item.getType()) {
            ItemTypeInterface.USER_TYPE -> appDao.deleteUser(item as User)
            ItemTypeInterface.COMPANY_TYPE -> appDao.deleteCompany(item as Company)
        }
    }

    suspend fun getUsers(): List<User> {
        return appDao.getUsers()
    }

    suspend fun getCompanies(): List<Company> {
        return appDao.getCompanies()
    }
}
