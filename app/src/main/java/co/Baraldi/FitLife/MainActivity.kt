package co.Baraldi.FitLife

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import co.Baraldi.FitLife.R.layout.activity_main
import android.content.Intent
import android.graphics.Color
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var rvMain: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mainItems = mutableListOf<MainItem>()
        mainItems.add(
            MainItem(
                id = 1,
                drawableId = R.drawable.ic_regua,
                textStringId = R.string.label_imc,
                color = Color.WHITE
            )
        )
        mainItems.add(
            MainItem(
                id = 2,
                drawableId = R.drawable.ic_ginasio,
                textStringId = R.string.label_tbm,
                color = Color.WHITE
            )
        )

//        mainItems.add(
//            MainItem(
//                id = 3,
//                drawableId = R.drawable.ic_baseline_wb_sunny_24,
//                textStringId = R.string.label_bf,
//                color = Color.WHITE
//            )
//        )
//
//
//        mainItems.add(
//            MainItem(
//                id = 3,
//                drawableId = R.drawable.ic_baseline_wb_sunny_24,
//                textStringId = R.string.label_bf,
//                color = Color.WHITE
//            )
//        )

        val adapter = MainAdapter(mainItems) { id ->
            // METODO 3: IMPL VIA FUNCTIONS
            when (id) {
                1 -> {
                    val intent = Intent(this@MainActivity, ImcActivity::class.java)
                    startActivity(intent)
                }
                2 -> {
                    val intent = Intent(this@MainActivity, tmbActivity::class.java)
                    startActivity(intent)
                    // abrir uma outra activity
                }
            }
            Log.i("Teste", "clicou $id!!")
        }

        rvMain = findViewById(R.id.rv_main)
        rvMain.adapter = adapter
        rvMain.layoutManager = GridLayoutManager(this, 2)
        //LinearLayoutManager


    }
    //override fun onClick(id: Int) {



        private inner class MainAdapter(
    private val mainItems: List<MainItem>,
//        private val onItemClickListener: OnItemClickListener
    private val onItemClickListener: (Int) -> Unit,
) : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {
        // Layout XML da célula - item


            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
                val view = layoutInflater.inflate(R.layout.main_item, parent, false)
                return MainViewHolder(view)
            }

            // 2 - disparado toda vez houver uma rolagem na tela e for necessario trocar o conteudo
            // da celula
            override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
                val itemCurrent = mainItems[position]
                holder.bind(itemCurrent)
            }

            // 3 - informar quantas celulas essa listagem terá
            override fun getItemCount(): Int {
                return mainItems.size
            }

            // é a classe da celula em si!!!
            private inner class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
                fun bind(item: MainItem) {
                    val img: ImageView = itemView.findViewById(R.id.item_img_icon)
                    val name: TextView = itemView.findViewById(R.id.item_txt_name)
                    val container: LinearLayout = itemView.findViewById(R.id.item_container_imc)

                    img.setImageResource(item.drawableId)
                    name.setText(item.textStringId)
                    container.setBackgroundColor(item.color)

                    container.setOnClickListener {
                        // aqui ele é uma ref. function
                        onItemClickListener.invoke(item.id)

                        // aqui ele é uma ref. interface
                        // onItemClickListener.onClick(item.id)
                    }
                }
            }

        }

// 3 maneiras de escutar eventos de click usando celular (viewholder) activities
// !             1. [X] impl interface (nesse video)
// !!            2. objetos anonimos (prox.)
// !!! -> kotlin 3. funcional (prox.)

}