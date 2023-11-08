package co.Baraldi.FitLife

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import co.Baraldi.FitLife.R.layout.activity_main
import android.content.Intent
import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var rvMain: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(activity_main)
        val mainItems = mutableListOf<MainItem>()
        mainItems.add(
            MainItem(
                id = 1,
                drawableId = R.drawable.ic_baseline_wb_sunny_24,
                textStringId = R.string.label_imc,
                color = Color.YELLOW
            )
        )
        mainItems.add(
            MainItem(
                id = 2,
                drawableId = R.drawable.ic_baseline_wb_sunny_24,
                textStringId = R.string.label_tbm,
                color = Color.GREEN
            )
        )

        mainItems.add(
            MainItem(
                id = 3,
                drawableId = R.drawable.ic_baseline_wb_sunny_24,
                textStringId = R.string.label_bf,
                color = Color.MAGENTA
            )
        )


        mainItems.add(
            MainItem(
                id = 3,
                drawableId = R.drawable.ic_baseline_wb_sunny_24,
                textStringId = R.string.label_bf,
                color = Color.CYAN
            )
        )

        val adapter = MainAdapter(mainItems){ id ->
            when (id) {
                1 -> {
                    val intent = Intent(this@MainActivity, ImcActivity::class.java)
                    startActivity(intent)
                }
                2 -> 
            }
        }


        rvMain = findViewById(R.id.rv_main)
        rvMain.adapter = adapter
        rvMain.layoutManager = GridLayoutManager(this, 2)
        //LinearLayoutManager


    }

    private inner class MainAdapter(private val mainItens: List<MainItem>) :
        RecyclerView.Adapter<MainViewHolder>() {
        // Layout XML da célula - item

        private val onItemClickListener: (Int) -> Unit,
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
            val view = layoutInflater.inflate(R.layout.main_item, parent, false)
            return MainViewHolder(view)
        }

        //disparado quando houver rolagem e for trocar conteudo
        override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
            val itemCurrent = mainItens[position]
            holder.bind(itemCurrent)

        }

        // informar a quantidade de células
        override fun getItemCount(): Int {
            return mainItens.size
        }

    }

    // classe célula
    private class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: MainItem) {
            val img: ImageView = itemView.findViewById(R.id.item_img_icon)
            val name: TextView = itemView.findViewById(R.id.item_txt_name)
            val container: LinearLayout = itemView.findViewById(R.id.item_container_imc)

            img.setImageResource(item.drawableId)
            name.setText(item.textStringId)
            container.setBackgroundColor(item.color)

            container.setOnClickListener{
                onItemClickListener.invoker(item.id)
            }


        }

    }
}