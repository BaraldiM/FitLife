package co.Baraldi.FitLife

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class tmbActivity : AppCompatActivity() {
    private lateinit var editWeight: EditText
    private lateinit var editHeight: EditText
    private lateinit var editage: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tmb)

        editWeight = findViewById(R.id.edit_tmb_weight)
        editHeight = findViewById(R.id.edit_tmb_height)
        editage = findViewById(R.id.edit_tmb_age)

        val btnSend: Button = findViewById(R.id.btn_tmb_send)
        btnSend.setOnClickListener {
            if (!validate()) {
                Toast.makeText(this, R.string.fields_messages, Toast.LENGTH_SHORT).show()
                return@setOnClickListener

            }
            val weight = editWeight.text.toString().toInt()
            val height = editHeight.text.toString().toInt()
            val age = editHeight.text.toString().toInt()
            val result = calculateImc(weight, height, age)
            Log.d("teste", "resultado: $result")

            val imcResponseId = imcResponse(result)
            val dialog = AlertDialog.Builder(this)

            dialog.setTitle(getString(R.string.imc_response,result))
            dialog.setMessage(R.string.calc)
            dialog.setPositiveButton(android.R.string.ok
            ) { dialog, which -> }

                .create()
                .show()

            val service = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            service.hideSoftInputFromWindow(currentFocus?.windowToken, 0)

        }


    }

    @StringRes
    private fun imcResponse(imc: Double): Int {
        when {
            imc < 15.0 -> return R.string.imc_severamente_abaixo
            imc < 16.0 -> return R.string.imc_muito_baixo
            imc < 18.5 -> return R.string.imc_abaixo
            imc < 25.0 -> return R.string.imc_normal
            imc < 30.0 -> return R.string.imc_acima
            imc < 35.0 -> return R.string.imc_moderadamente_acima
            imc < 40.0 -> return R.string.imc_severamente_acima
            else -> return R.string.imc_extremamente_obeso
        }
    }


    private fun calculateImc(weight: Int, height: Int, age: Int): Double {
        //peso / (altura * altura * idade)
        return weight / ((height / 100.0) * (height / 100.0) * age)
    }

    private fun validate(): Boolean {
        //Não inserir valor menor ou igual a o
        return (editWeight.text.toString().isNotEmpty()
                && editHeight.text.toString().isNotEmpty()
                && !editWeight.text.toString().startsWith("0")
                && !editHeight.text.toString().startsWith("0"))
    }


}
