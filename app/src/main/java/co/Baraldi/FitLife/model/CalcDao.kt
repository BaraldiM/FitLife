package co.Baraldi.FitLife.model

import androidx.room.*

@Dao
interface CalcDao {
    @Insert
    fun insert(calc : Calc)

    @Query("select * from Calc where type = :type")
    fun getRegisterByType(type: String) : List<Calc>

    //@Query Buscar
    //@Update Atualizar
    //@Delete Delete





}