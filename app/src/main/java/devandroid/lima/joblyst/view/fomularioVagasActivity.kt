package devandroid.lima.joblyst.view

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import coil.imageLoader
import coil.load
import devandroid.lima.joblyst.dao.VagasDao
import devandroid.lima.joblyst.databinding.ActivityFomularioVagasBinding
import devandroid.lima.joblyst.databinding.FormularioImagemBinding
import devandroid.lima.joblyst.model.Vaga
import java.math.BigDecimal

class fomularioVagasActivity :
    AppCompatActivity() {

    private val dao = VagasDao()
    private val binding by lazy {
        ActivityFomularioVagasBinding.inflate(layoutInflater)
    }
    private var url: String?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        btnSalvar()

        binding.imageViewFormularioProduto.setOnClickListener(){
            val bindingFormImagem = FormularioImagemBinding.inflate(layoutInflater)
            bindingFormImagem.formularioImagemButton.setOnClickListener {
                val url = bindingFormImagem.formularioImagemEdittextUrl.text.toString()
                bindingFormImagem.formularioImagemImageview.load(url)
            }
            AlertDialog.Builder(this)
                .setView(bindingFormImagem.root)
                .setPositiveButton("Confirmar"){_, _ ->
                    url = bindingFormImagem.formularioImagemEdittextUrl.text.toString()
                    binding.imageViewFormularioProduto.load(url)
                }
                .setNegativeButton("Sair", null)
                .show()
        }
    }
    private fun btnSalvar(){
        val btnSalvar = binding.botaoGravarFormularioProduto
        btnSalvar.setOnClickListener {
            val vagaNova = criaVaga()
            dao.adicionaVaga(vagaNova)
            finish()
        }
    }

    private fun criaVaga(): Vaga {
        val campoNome = binding.nomeEmpresaFormularioProduto
        val nome = campoNome.text.toString()
        val campoCargo = binding.cargoVagaFormularioProduto
        val cargo = campoCargo.text.toString()
        val campoDescricao = binding.descricaoVagaProdutoItem
        val descricao = campoDescricao.text.toString()
        val campoLocalizacao = binding.localizacaoVagaProdutoItem
        val localizacao = campoLocalizacao.text.toString()
        val data = "22/12/2023"
        val campoSalario = binding.salarioVagaProdutoItem
        val valorEmString = campoSalario.text.toString()

        val valor = if (valorEmString.isBlank()) {
            BigDecimal.ZERO
        } else {
            BigDecimal(valorEmString)
        }

        return Vaga(
            foto = url,
            nomeEmpresa = nome,
            cargo = cargo,
            descricao = descricao,
            localizacao = localizacao,
            data = data,
            salario = valor
        )
    }
}