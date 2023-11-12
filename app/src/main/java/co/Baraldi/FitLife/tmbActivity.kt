package co.Baraldi.FitLife

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class tmbActivity : AppCompatActivity() {

    private lateinit var lifestyle: AutoCompleteTextView
    private lateinit var editWeight: EditText
    private lateinit var editHeight: EditText
    private lateinit var editage: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tmb)

        lifestyle = findViewById(R.id.auto_lyfestyle)

        val items = resources.getStringArray(R.array.tbm_lyfestyles)
        lifestyle.setText(items.first())
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, items)
        lifestyle.setAdapter(adapter)



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
            val age = editage.text.toString().toInt()
            val result = calculatetmb(weight, height, age)
            val response = tmbRequest(result)

            Log.d("teste", "resultado: $result")



        }


    }




    private fun calculateImc(weight: Int, height: Int, age: Int): Double {
        //peso / (altura * altura * idade)
        return weight / ((height / 100.0) * (height / 100.0) * age)
    }



    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.imc_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.imc_menu_search){
            finish()
            openListActivity()

        }
        return super.onOptionsItemSelected(item)
    }
    private fun openListActivity(){
        val intent = Intent(this, ListCalcActivity::class.java)
        intent.putExtra("type", "imc")
        startActivity(intent)
    }

    private fun tmbRequest(tmb: Double) : Double {
        val items = resources.getStringArray(R.array.tbm_lyfestyles)
        return when {
            lifestyle.text.toString() == items[0] -> tmb * 1.2
            lifestyle.text.toString() == items[1] -> tmb * 1.375
            lifestyle.text.toString() == items[2] -> tmb * 1.55
            lifestyle.text.toString() == items[3] -> tmb * 1.725
            lifestyle.text.toString() == items[4] -> tmb * 1.2
            else -> 0.0
        }
    }


    private fun calculatetmb(weight: Int, height: Int, age: Int): Double {
        return 66 * (13.8 * weight) + (5 * height) - (6-8 * age)
    }


    private fun validate(): Boolean {
        //Não inserir valor menor ou igual a o
        return (editWeight.text.toString().isNotEmpty()
                && editHeight.text.toString().isNotEmpty()
                && editage.text.toString().isNotEmpty()
                && !editWeight.text.toString().startsWith("0")
                && !editage.text.toString().startsWith("0")
                && !editHeight.text.toString().startsWith("0"))
    }


}
