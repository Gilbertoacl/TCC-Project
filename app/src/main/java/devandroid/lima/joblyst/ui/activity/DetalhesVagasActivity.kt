package devandroid.lima.joblyst.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import devandroid.lima.joblyst.R
import devandroid.lima.joblyst.databinding.ActivityDetalhesVagasBinding
import devandroid.lima.joblyst.extensions.formataParaMoedaBrasileira
import devandroid.lima.joblyst.extensions.loadImage
import devandroid.lima.joblyst.model.Vaga

class DetalhesVagasActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityDetalhesVagasBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        tentaCarregarProduto()
    }

    private fun tentaCarregarProduto() {
        // tentativa de buscar o produto se ele existir,
        // caso contrário, finalizar a Activity
        intent.getParcelableExtra<Vaga>(CHAVE_PRODUTO)?.let { produtoCarregado ->
            preencheCampos(produtoCarregado)
        } ?: finish()
    }

    private fun preencheCampos(produtoCarregado: Vaga) {
        with(binding) {
            activityDetalhesProdutoImagem.loadImage(produtoCarregado.foto)
            activityDetalhesProdutoNome.text = produtoCarregado.cargo
            activityDetalhesProdutoDescricao.text = produtoCarregado.descricao
            activityDetalhesProdutoValor.text =
                produtoCarregado.salario.formataParaMoedaBrasileira()
        }
    }

}
