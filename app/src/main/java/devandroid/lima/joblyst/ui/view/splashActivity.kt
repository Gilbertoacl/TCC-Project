package devandroid.lima.joblyst.ui.view

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import devandroid.lima.joblyst.R

class splashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()
        window.statusBarColor = Color.parseColor("#000127")

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, loginActivity::class.java)
            startActivity(intent)
            finish()
        },3000)
    }
}