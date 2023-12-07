package devandroid.lima.joblyst.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import devandroid.lima.joblyst.R
import devandroid.lima.joblyst.adapter.AdapterVaga
import devandroid.lima.joblyst.dao.VagasDao
import devandroid.lima.joblyst.databinding.ActivityHomeBinding
import devandroid.lima.joblyst.model.Vaga

class homeActivity : AppCompatActivity() {
    private val dao = VagasDao()
    private val adapter = AdapterVaga(this, dao.buscaVagas())
    private val binding by lazy {
        ActivityHomeBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        configuraRecyclerView()
        configuraFab()
    }

    override fun onResume() {
        super.onResume()
        adapter.atualiza(dao.buscaVagas())
    }

    private fun configuraRecyclerView(): RecyclerView {
        val reciclerViewVagas = binding.recViewVagas
        reciclerViewVagas.adapter = adapter
        return reciclerViewVagas
    }

    private fun configuraFab() {
        val fab = binding.floatingActionButtonListVagas
        fab.setOnClickListener {
            chamaFormulario()
        }
    }

    private fun chamaFormulario() {
        val activity = Intent(this, fomularioVagasActivity::class.java)
        startActivity(activity)
    }
}