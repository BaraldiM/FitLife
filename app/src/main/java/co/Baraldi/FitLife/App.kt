package co.Baraldi.FitLife

import android.app.Application
import co.Baraldi.FitLife.model.AppDatabase

class App : Application() {

    lateinit var  db: AppDatabase

    override fun onCreate(){
        super.onCreate()
        db = AppDatabase.getDatabase(this)
    }


}