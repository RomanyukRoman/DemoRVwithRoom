package com.example.demorecyclerviewroom

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "users",
    foreignKeys = [
        ForeignKey(
            entity = Company::class,
            parentColumns = ["id"],
            childColumns = ["companyId"],
            onDelete = ForeignKey.CASCADE
        )]
)
data class User(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo val image: Int,
    @ColumnInfo val name: String,
    @ColumnInfo val email: String,
    @ColumnInfo val companyId: Long,
): ItemTypeInterface {
    override fun getType(): Int {
        return ItemTypeInterface.USER_TYPE
    }
}
