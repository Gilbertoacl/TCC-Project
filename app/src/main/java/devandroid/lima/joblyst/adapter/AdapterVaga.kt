package devandroid.lima.joblyst.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import devandroid.lima.joblyst.R
import devandroid.lima.joblyst.databinding.VagaItemReciclerViewBinding
import devandroid.lima.joblyst.model.Vaga
import java.math.BigDecimal
import java.text.NumberFormat
import java.util.Locale

class AdapterVaga(
    private val context: Context,
    vagas: List<Vaga>
):RecyclerView.Adapter<AdapterVaga.VagaViewHolder>() {

    private val vagas = vagas.toMutableList()
    class VagaViewHolder(private val binding: VagaItemReciclerViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun vincula(vaga: Vaga) {
            val foto = binding.imageViewVagaItem.load(vaga.foto)
            val nome = binding.nomeVagaItem
            val descicao = binding.descricaoVagaItem
            val localizacao = binding.localizacaoVagaItem
            val data = binding.dataVagaItem
            val salario= binding.valorVagaItem
            val valorEmMoeda: String = formataSalario(vaga.salario)
            salario.text = valorEmMoeda
        }
        fun formataSalario(salario: BigDecimal): String {
            val formatador: NumberFormat = NumberFormat.getCurrencyInstance(Locale("pt", "BR"))
            return formatador.format(salario)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            VagaViewHolder {
//        val itemLista = LayoutInflater.from(context).inflate(R.layout.vaga_item_recicler_view, parent, false)
//        val holder = VagaViewHolder(itemLista)
//        return holder
        val inflater = LayoutInflater.from(context)
        val binding = VagaItemReciclerViewBinding.inflate(inflater, parent, false)
        return  VagaViewHolder(binding)
    }
    override fun getItemCount(): Int = vagas.size

    override fun onBindViewHolder(holder: VagaViewHolder, position: Int) {
//        holder.foto.setImageResource(vagas[position].foto)
//        holder.nome.text = vagas[position].cargo
//        holder.descicao.text = vagas[position].descricao
//        holder.localizacao.text = vagas[position].localizacao
//        holder.data.text = vagas[position].dataDePostagem
//        holder.salario.text = vagas[position].salario.toString()
        val vaga = vagas[position]
        holder.vincula(vaga)
    }

    fun atualiza(produtos: List<Vaga>) {
        this.vagas.clear()
        this.vagas.addAll(produtos)
        notifyDataSetChanged()
    }


}