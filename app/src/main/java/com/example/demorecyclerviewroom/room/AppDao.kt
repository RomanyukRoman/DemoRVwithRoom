package com.example.demorecyclerviewroom.room

import androidx.room.*
import com.example.demorecyclerviewroom.Company
import com.example.demorecyclerviewroom.User

@Dao
interface AppDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCompany(company: Company): Long

    @Delete
    suspend fun deleteUser(user: User)

    @Delete
    suspend fun deleteCompany(company: Company)

    @Query("SELECT * FROM users")
    suspend fun getUsers(): List<User>

    @Query("SELECT * FROM companies")
    suspend fun getCompanies(): List<Company>

    @Query("DELETE FROM users")
    suspend fun clearUsers()

    @Query("DELETE FROM companies")
    suspend fun clearCompanies()
}