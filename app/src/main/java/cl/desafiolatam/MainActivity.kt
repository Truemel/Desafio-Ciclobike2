package cl.desafiolatam

import ListaAdapter
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var lista: ArrayList<Ciclovia>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupCiclovias()
        lista_ciclovias.layoutManager = LinearLayoutManager(this)
        val listaAdapter = ListaAdapter(this)
        lista_ciclovias.adapter = listaAdapter
        listaAdapter.setupData(lista!!)

        button_filtrar.setOnClickListener{
            val listaAux: ArrayList<Ciclovia>? = ArrayList()
            lista!!.forEach {
                when (it.comuna) {
                    "Las Condes" -> listaAux!!.add(it)
                }
            }
            listaAdapter.setupData(listaAux!!)
        }

        button_no_filtrar.setOnClickListener {
            listaAdapter.setupData(lista!!)
        }

        var spinList = arrayListOf<String>("mostrar todo")
        for(comu:Ciclovia in lista!!)
            spinList.add(comu.comuna)

        spinner.adapter = ArrayAdapter<String>(this, R.layout.spiiner_content, spinList)
    }

    private fun setupCiclovias() {
        val setupCiclovias = SetupCiclovias()
        lista = setupCiclovias.init()
    }
}
