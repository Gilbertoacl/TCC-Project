package devandroid.lima.joblyst.ui.activity

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import devandroid.lima.joblyst.dao.VagasDao
import devandroid.lima.joblyst.databinding.ActivityFomularioVagasBinding
import devandroid.lima.joblyst.extensions.loadImage
import devandroid.lima.joblyst.model.Vaga
import devandroid.lima.joblyst.ui.dialog.FormularioImageDialog
import java.math.BigDecimal
import java.time.LocalDate

class FomularioVagasActivity :
    AppCompatActivity() {
    private val dao = VagasDao()

    private val binding by lazy {
        ActivityFomularioVagasBinding.inflate(layoutInflater)
    }
    private var url: String?= null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        btnSave()
        binding.imageViewFormularioVaga.setOnClickListener(){
            FormularioImageDialog(this)
                .show(url){ imagem ->
                    url = imagem
                    binding.imageViewFormularioVaga.loadImage(url)
                }
        }
    }
    private fun btnSave(){
        val btnSalvar = binding.botaoGravarFormularioProduto
        btnSalvar.setOnClickListener {
            val vagaNova = criaVaga()
            dao.adicionaVaga(vagaNova)
            finish()
        }
    }



    private fun criaVaga(): Vaga {

        val campoCargo = binding.cargoVagaFormularioProduto
        val cargo = campoCargo.text.toString()

        val campoDescricao = binding.descricaoVagaProdutoItem
        val descricao = campoDescricao.text.toString()

        val campoLocalizacao = binding.localizacaoVagaProdutoItem
        val localizacao = campoLocalizacao.text.toString()

        val data = LocalDate.now()

        val campoSalario = binding.salarioVagaProdutoItem
        val valorEmString = campoSalario.text.toString()

        val valor = if (valorEmString.isBlank()) {
            BigDecimal.ZERO
        } else {
            BigDecimal(valorEmString)
        }

        return Vaga(
            foto = url,
            cargo = cargo,
            descricao = descricao,
            localizacao = localizacao,
            data = data,
            salario = valor
        )
    }
}