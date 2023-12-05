package devandroid.lima.joblyst.view

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.google.android.material.snackbar.Snackbar
import devandroid.lima.joblyst.R

class loginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        window.statusBarColor = Color.parseColor("#000000")

        val button = findViewById<Button>(R.id.btnEntrar)
        val user = findViewById<EditText>(R.id.editUsuario)
        val password = findViewById<EditText>(R.id.editSenha)

        button.setOnClickListener {
            checkCredentials(user, password)
        }
    }

    fun checkCredentials(user: EditText, password: EditText) {
        if (user.text.toString() == "admin" && password.text.toString() == "123456") {
            val intent = Intent(this, homeActivity::class.java)
            startActivity(intent)
            finish()
        }
        else {
            val snackbar = Snackbar.make(findViewById(R.id.container), "Usuário ou senha incorretos.", Snackbar.LENGTH_LONG).setAction("Action", null)
            snackbar.setBackgroundTint(Color.RED)
            snackbar.setTextColor(Color.WHITE)
            snackbar.show()
        }
    }
}