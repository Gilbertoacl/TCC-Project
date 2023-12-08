package devandroid.lima.joblyst.extensions

import android.widget.ImageView
import coil.load

fun ImageView.loadImage(url: String?= null){
    load(url){
        fallback(devandroid.lima.joblyst.R.drawable.erro)
        error(devandroid.lima.joblyst.R.drawable.erro)
        placeholder(devandroid.lima.joblyst.R.drawable.placeholder)
    }
}