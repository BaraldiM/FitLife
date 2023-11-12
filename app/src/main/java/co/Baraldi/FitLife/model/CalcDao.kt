package co.Baraldi.FitLife.model

import androidx.room.*

@Dao
interface CalcDao {
    @Insert
    fun insert(calc : Calc)

    //@Query Buscar
    //@Update Atualizar
    //@Delete Delete





}