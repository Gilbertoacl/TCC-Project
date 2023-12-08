package devandroid.lima.joblyst.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import devandroid.lima.joblyst.databinding.VagaItemReciclerViewBinding
import devandroid.lima.joblyst.model.Vaga
import devandroid.lima.joblyst.ui.home.HomeFragment
import java.math.BigDecimal
import java.text.NumberFormat
import java.util.Locale

class ListaVagasAdapter(
    private val context: HomeFragment,
    vagas: List<Vaga>
):RecyclerView.Adapter<ListaVagasAdapter.ViewHolder>() {

    private val vagas = vagas.toMutableList()
    class ViewHolder(private val binding: VagaItemReciclerViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun vincula(vaga: Vaga) {

            binding.imageViewVagaItem.load(vaga.foto)
            val cargo = binding.nomeVagaItem
            cargo.text = vaga.cargo

            val descicao = binding.descricaoVagaItem
            descicao.text = vaga.descricao

            val localizacao = binding.localizacaoVagaItem
            localizacao.text = vaga.localizacao

            val data = binding.dataVagaItem
            data.text = vaga.data.toString()

            val valor= binding.valorVagaItem
            val valorEmMoeda: String = formataSalario(vaga.salario)
            valor.text = valorEmMoeda

        }
        fun formataSalario(salario: BigDecimal): String {
            val formatador: NumberFormat = NumberFormat.getCurrencyInstance(Locale("pt", "BR"))
            return formatador.format(salario)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = VagaItemReciclerViewBinding.inflate(inflater, parent, false)
        return  ViewHolder(binding)
    }
    override fun getItemCount(): Int = vagas.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val vaga = vagas[position]
        holder.vincula(vaga)
    }

    fun atualiza(vagas: List<Vaga>) {
        this.vagas.clear()
        this.vagas.addAll(vagas)
        notifyDataSetChanged()
    }


}