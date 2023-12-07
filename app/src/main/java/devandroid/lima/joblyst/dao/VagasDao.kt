package devandroid.lima.joblyst.dao

import devandroid.lima.joblyst.model.Vaga
import java.math.BigDecimal

class VagasDao {
    fun adicionaVaga(vaga: Vaga) {
        vagas.add(vaga)
    }

    fun buscaVagas(): List<Vaga> {
        return vagas.toList()
    }

    companion object {
        private val vagas = mutableListOf<Vaga>(
            Vaga(
                foto = " ",
                nomeEmpresa = "Mercado do Joao",
                cargo = "Repositor de Mercadoria",
                descricao = "Repor mercadoria",
                localizacao = "Rua dos bobos, 0",
                data = "10/10/2022",
                salario = BigDecimal("1500.00")
            )
        )
    }
}