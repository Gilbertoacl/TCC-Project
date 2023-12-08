package devandroid.lima.joblyst.view

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import coil.ImageLoader
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.load
import devandroid.lima.joblyst.dao.VagasDao
import devandroid.lima.joblyst.databinding.ActivityFomularioVagasBinding
import devandroid.lima.joblyst.databinding.FormularioImagemBinding
import devandroid.lima.joblyst.model.Vaga
import java.math.BigDecimal
import java.text.SimpleDateFormat
import java.time.LocalDate

class fomularioVagasActivity :
    AppCompatActivity() {
    private val dao = VagasDao()

    private val binding by lazy {
        ActivityFomularioVagasBinding.inflate(layoutInflater)
    }

    val sdf = SimpleDateFormat("dd/MM/yyyy")

    private var url: String?= null

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        btnSalvar()

        val imageLoader = ImageLoader.Builder(this)
            .components {
                if (Build.VERSION.SDK_INT >= 28) {
                    add(ImageDecoderDecoder.Factory())
                } else {
                    add(GifDecoder.Factory())
                }
            }
            .build()

        binding.imageViewFormularioProduto.setOnClickListener(){
            val bindingFormImagem = FormularioImagemBinding.inflate(layoutInflater)
            bindingFormImagem.formularioImagemButton.setOnClickListener {
                val url = bindingFormImagem.formularioImagemEdittextUrl.text.toString()
                bindingFormImagem.formularioImagemImageview.load(
                    url,
                    imageLoader = imageLoader
                )
            }
            AlertDialog.Builder(this)
                .setView(bindingFormImagem.root)
                .setPositiveButton("Confirmar"){_, _ ->
                    url = bindingFormImagem.formularioImagemEdittextUrl.text.toString()
                    binding.imageViewFormularioProduto.load(
                        url,
                        imageLoader
                    )
                }
                .setNegativeButton("Sair", null)
                .show()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun btnSalvar(){
        val btnSalvar = binding.botaoGravarFormularioProduto
        btnSalvar.setOnClickListener {
            val vagaNova = criaVaga()
            dao.adicionaVaga(vagaNova)
            finish()
        }
    }


    @RequiresApi(Build.VERSION_CODES.O)
    private fun criaVaga(): Vaga {
        val campoNome = binding.nomeEmpresaFormularioProduto
        val nome = campoNome.text.toString()

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