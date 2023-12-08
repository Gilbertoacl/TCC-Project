package devandroid.lima.joblyst.ui.view

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import devandroid.lima.joblyst.dao.VagasDao
import devandroid.lima.joblyst.databinding.ActivityHomeBinding

class homeActivity : AppCompatActivity() {
    private val dao = VagasDao()
//    @RequiresApi(Build.VERSION_CODES.O)
//    private val adapter = ListaVagasAdapter(context = this, vagas = dao.buscaVagas())
    private val binding by lazy {
        ActivityHomeBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)
//        configuraRecyclerView()
        configuraFab()

    }
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onResume() {
        super.onResume()
//        adapter.atualiza(dao.buscaVagas())
    }

    private fun configuraFab() {
        val fab = binding.floatingActionButtonListVagas
        fab.setOnClickListener {
            chamaFormulario()
        }
    }
    private fun chamaFormulario() {
        val activity = Intent(this, FomularioVagasActivity::class.java)
        startActivity(activity)
    }
    private fun configuraRecyclerView() {
//        val reciclerViewVagas = binding.recViewVagas
//        reciclerViewVagas.adapter = adapter
    }


}