package devandroid.lima.joblyst.extensions

import android.widget.ImageView
import coil.load
import devandroid.lima.joblyst.R

fun ImageView.loadImage(
    url: String?= null,
    fallback: Int = R.drawable.imagem_padrao
){
    load(url){
        fallback(fallback)
        error(R.drawable.erro)
        placeholder(R.drawable.placeholder)
    }
}