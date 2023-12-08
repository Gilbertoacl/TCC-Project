package devandroid.lima.joblyst.ui.dialog

import android.content.Context
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import devandroid.lima.joblyst.databinding.FormularioImagemBinding
import devandroid.lima.joblyst.extensions.loadImage

class FormularioImageDialog(private val context: Context) {

    fun show(
        standartUrl: String? = null,
        whenLoadImage: (imagem: String) -> Unit
    ) {
        FormularioImagemBinding
            .inflate(LayoutInflater.from(context)).apply {
                standartUrl.let {
                    formularioImagemImageview.loadImage(it)
                    formularioImagemEditTextUrl.setText(it)
                }

                formularioImagemButton.setOnClickListener {
                    val url = formularioImagemEditTextUrl.text.toString()
                    formularioImagemImageview.loadImage(url)
                }
                AlertDialog.Builder(context)
                    .setView(root)
                    .setPositiveButton("Confirmar") { _, _ ->
                        val url = formularioImagemEditTextUrl.text.toString()
                        whenLoadImage(url)
                    }
                    .setNegativeButton("Sair", null)
                    .show()
            }
    }
}