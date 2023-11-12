package co.Baraldi.FitLife.model

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Calc::class], version = 1)
abstract class AppDatabase : RoomDatabase () {
    abstract fun calcDao(): CalcDao
}