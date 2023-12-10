package com.example.t3_todolist.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.t3_todolist.R
import com.example.t3_todolist.model.Tarea
import java.text.SimpleDateFormat
import java.util.Locale

class AdaptadorRecycler(private val listaTareas: List<Tarea>) : RecyclerView.Adapter<AdaptadorRecycler.TareaViewHolder>() {

    class TareaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imagenTarea: ImageView = itemView.findViewById(R.id.imagen_tarea)
        var textoTitulo: TextView = itemView.findViewById(R.id.texto_titulo)
        var textoFecha: TextView = itemView.findViewById(R.id.texto_fecha)
        // Añade aquí más variables si tienes más vistas en tu diseño

        fun bind(tarea: Tarea) {
            textoTitulo.text = tarea.titulo
            val formato = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            textoFecha.text = formato.format(tarea.fecha)
            // Aquí puedes configurar la imagen y otros campos si los necesitas
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TareaViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.tareas_recycler, parent, false)
        return TareaViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TareaViewHolder, position: Int) {
        val tareaActual = listaTareas[position]
        holder.bind(tareaActual)
    }

    override fun getItemCount() = listaTareas.size
}

