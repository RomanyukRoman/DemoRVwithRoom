package com.example.demorecyclerviewroom

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "companies")
data class Company(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo val name: String,
    @ColumnInfo val number: String,
    @ColumnInfo val email: String,
): ItemTypeInterface {
    override fun getType(): Int {
        return ItemTypeInterface.COMPANY_TYPE
    }
}
