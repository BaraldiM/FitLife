package co.Baraldi.FitLife

import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import androidx.room.Dao
import co.Baraldi.FitLife.model.Calc

class ImcActivity : AppCompatActivity() {

    private lateinit var editWeight: EditText
    private lateinit var editHeight: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imc)

        editWeight = findViewById(R.id.edit_imc_weight)
        editHeight = findViewById(R.id.edit_imc_height)

        val btnSend: Button = findViewById(R.id.btn_imc_send)

        btnSend.setOnClickListener {
            if (!validate()) {
                Toast.makeText(this, R.string.fields_messages, Toast.LENGTH_SHORT).show()
                return@setOnClickListener

            }
            val weight = editWeight.text.toString().toInt()
            val height = editHeight.text.toString().toInt()

            val result = calculateImc(weight, height)
            Log.d("teste", "resultado: $result")

            val imcResponseId = imcResponse(result)

            AlertDialog.Builder(this)
                .setTitle(getString(R.string.imc_response, result))
                .setMessage(imcResponseId)
                .setPositiveButton(android.R.string.ok){ dialog, which ->

                }
                .setNegativeButton(R.string.save){dialog, which ->
                    Thread{
                        val app = application as App
                        val dao = app.db.calcDao()
                        dao.insert(Calc(type = "imc", res = result ))

                         runOnUiThread{
                         Toast.makeText(this@ImcActivity, R.string.saved, Toast.LENGTH_LONG).show()
                         }

                    }.start()



                }
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


    private fun calculateImc(weight: Int, height: Int): Double {
        //peso / (altura * altura)
        return weight / ((height / 100.0) * (height / 100.0))
    }

    private fun validate(): Boolean {
        //Não inserir valor menor ou igual a o
        return (editWeight.text.toString().isNotEmpty()
                && editHeight.text.toString().isNotEmpty()
                && !editWeight.text.toString().startsWith("0")
                && !editHeight.text.toString().startsWith("0"))
    }


}
