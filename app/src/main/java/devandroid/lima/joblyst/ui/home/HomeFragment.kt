package devandroid.lima.joblyst.ui.home

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import devandroid.lima.joblyst.adapter.ListaVagasAdapter
import devandroid.lima.joblyst.dao.VagasDao
import devandroid.lima.joblyst.databinding.FragmentHomeBinding
import devandroid.lima.joblyst.ui.activity.CHAVE_PRODUTO
import devandroid.lima.joblyst.ui.activity.DetalhesVagasActivity
import devandroid.lima.joblyst.ui.activity.FomularioVagasActivity

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val dao = VagasDao()
    private val adapter = ListaVagasAdapter(context = this, vagas = dao.buscaVagas())
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        configuraRecyclerView()
        configuraFab()

        return root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    override fun onResume() {
        super.onResume()
       adapter.atualiza(dao.buscaVagas())
    }

    private fun configuraFab() {
        val fab = binding.floatingActionButtonListVagas
        fab.setOnClickListener {
            chamaFormulario()
        }
    }
    private fun chamaFormulario() {
        val activity = Intent(
            context,
            FomularioVagasActivity::class.java)
        startActivity(activity)
    }
    private fun configuraRecyclerView() {
        val reciclerViewVagas = binding.recViewVagas
        reciclerViewVagas.adapter = adapter

        adapter.quandoClicaNoItem = {
            val intent = Intent(
                context,
                DetalhesVagasActivity::class.java
            ).apply {
                Log.i("QuandoClica", "ta passando a intent $it")
                putExtra(CHAVE_PRODUTO, it)
            }
            startActivity(intent)
        }
    }
}