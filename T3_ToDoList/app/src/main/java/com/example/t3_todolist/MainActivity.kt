package com.example.t3_todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.t3_todolist.adapter.AdaptadorRecycler
import com.example.t3_todolist.databinding.ActivityMainBinding
import com.example.t3_todolist.model.Tarea
import com.google.android.material.snackbar.Snackbar
import java.util.Date

class MainActivity : AppCompatActivity(), OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adaptadorPrioridad: ArrayAdapter<CharSequence>
    private lateinit var adaptadorEstado: ArrayAdapter<CharSequence>
    private var listaDeTareas: MutableList<Tarea> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        inicializarListaDeTareas() // Inicializa la lista de tareas

        instancias()
        acciones()
    }

    private fun inicializarListaDeTareas() {
        for (i in 1..10) {
            val tarea = Tarea("Tarea $i", "Prioridad", Date(), "Estado")
            listaDeTareas.add(tarea)
        }
    }


    private fun instancias() {
        var listaPrioridades = ArrayList<CharSequence>()
        listaPrioridades.add("Baja");
        listaPrioridades.add("Media");
        listaPrioridades.add("Prioritaria");
        adaptadorPrioridad = ArrayAdapter(applicationContext,android.R.layout.simple_list_item_1,
            listaPrioridades);
        binding.spinnerPrioridad.adapter = adaptadorPrioridad
        binding.spinnerAdd.adapter = adaptadorPrioridad

        var listaEstados = ArrayList<CharSequence>()
        listaEstados.add("Por hacer");
        listaEstados.add("Haciendo");
        listaEstados.add("Hecho");
        adaptadorEstado = ArrayAdapter(applicationContext,android.R.layout.simple_list_item_1,
            listaEstados);
        binding.spinnerEstado.adapter = adaptadorEstado
    }
    private fun acciones() {
        binding.botonAdd.setOnClickListener(this)
        binding.recyclerTareas.layoutManager = LinearLayoutManager(this)
        binding.recyclerTareas.adapter = AdaptadorRecycler(listaDeTareas)
    }



    override fun onClick(v: View?) {
        when(v!!.id){
            binding.botonAdd.id ->{
                if(binding.textoAdd.text.isEmpty()){
                    Snackbar.make(binding.root, "Faltan datos", Snackbar.LENGTH_SHORT)
                        .show()
                }else {
                    val titulo = binding.textoAdd.text.toString()
                    val prioridad = binding.spinnerAdd.selectedItem.toString()
                    val fecha = Date()
                    val estado = binding.spinnerEstado.selectedItem.toString()
                    val tarea = Tarea(titulo, prioridad, fecha, estado)

                    Snackbar.make(binding.root, titulo + prioridad + fecha + estado, Snackbar.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }
}