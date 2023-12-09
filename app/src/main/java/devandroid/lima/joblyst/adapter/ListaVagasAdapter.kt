package devandroid.lima.joblyst.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import devandroid.lima.joblyst.databinding.VagaItemReciclerViewBinding
import devandroid.lima.joblyst.extensions.formataParaMoedaBrasileira
import devandroid.lima.joblyst.extensions.loadImage
import devandroid.lima.joblyst.model.Vaga
import devandroid.lima.joblyst.ui.home.HomeFragment
import java.math.BigDecimal
import java.text.NumberFormat
import java.util.Locale

class ListaVagasAdapter(
    private val context: HomeFragment,
    vagas: List<Vaga>,
    var quandoClicaNoItem: (produto: Vaga) -> Unit = {}
):RecyclerView.Adapter<ListaVagasAdapter.ViewHolder>() {

    private val vagas = vagas.toMutableList()
    inner class ViewHolder(private val binding: VagaItemReciclerViewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private lateinit var produto: Vaga
        init {
            itemView.setOnClickListener {
                if (::produto.isInitialized) {
                    quandoClicaNoItem(produto)
                }
            }
        }

        fun vincula(vaga: Vaga) {
            val cargo = binding.nomeVagaItem
            cargo.text = vaga.cargo

            val descicao = binding.descricaoVagaItem
            descicao.text = vaga.descricao

            val localizacao = binding.localizacaoVagaItem
            localizacao.text = vaga.localizacao

            val data = binding.dataVagaItem
            data.text = vaga.data.toString()

            val valor= binding.valorVagaItem
            val valorEmMoeda: String = vaga.salario.formataParaMoedaBrasileira()
            valor.text = valorEmMoeda

            val visibilidade = if (vaga.foto != null) {
                View.VISIBLE
            } else {
                View.GONE
            }
            binding.imageViewVagaItem.visibility = visibilidade
            binding.imageViewVagaItem.loadImage(vaga.foto)

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