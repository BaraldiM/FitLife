package co.Baraldi.FitLife

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import co.Baraldi.FitLife.R.layout.activity_main
import android.content.Intent

class MainActivity : AppCompatActivity() {
    private lateinit var btnImc: LinearLayout // BOTÃO IMC

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_main)

        btnImc = findViewById(R.id.btn_imc)
        btnImc.setOnClickListener{
               val i = Intent(this, ImcActivity::class.java)
            startActivity(i)
            //NAVEGAR ENTRE AS TELAS
        }
    }
}