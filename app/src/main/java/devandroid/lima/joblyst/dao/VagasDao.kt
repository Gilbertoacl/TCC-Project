package devandroid.lima.joblyst.dao

import android.os.Build
import androidx.annotation.RequiresApi
import devandroid.lima.joblyst.model.Vaga
import java.math.BigDecimal
import java.time.LocalDate

class VagasDao {

    @RequiresApi(Build.VERSION_CODES.O)
    fun adicionaVaga(vaga: Vaga) {
        vagas.add(vaga)
    }


    @RequiresApi(Build.VERSION_CODES.O)
    fun buscaVagas(): List<Vaga> {
        return vagas.toList()
    }

    companion object {

        @RequiresApi(Build.VERSION_CODES.O)
        private val vagas = mutableListOf<Vaga>(
            Vaga(
                foto = null,
                cargo = "Repositor de Mercadoria",
                descricao = "Repor mercadoria",
                localizacao = "Rua dos bobos, 0",
                data = LocalDate.now(),
                salario = BigDecimal("1500.00")
            )
        )
    }
}