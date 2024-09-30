package com.example.tp2024

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
internal interface UsuarioDao {
    @Query("select * from usuarios_table")
    fun getAll(): List<Usuario>
    @Query("select * from usuarios_table where nombre=:nombre")
    fun getUsuarioNombre(nombre:String):Usuario?
    @Insert
    fun insertUsuario(usuario: Usuario)
}