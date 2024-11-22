package com.example.demorecyclerviewroom

interface ItemTypeInterface {
    fun getType(): Int
        companion object {
            const val USER_TYPE= 1
            const val COMPANY_TYPE= 2
    }
}