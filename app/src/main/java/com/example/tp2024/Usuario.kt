package com.example.tp2024

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "usuarios_table")
class Usuario(
    @ColumnInfo(name="nombre") var nombre:String,
    @ColumnInfo(name="password")var password:String
){
    @PrimaryKey(autoGenerate = true) var id:Int = 0
}