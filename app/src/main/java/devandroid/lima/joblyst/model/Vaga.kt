package devandroid.lima.joblyst.model

import java.math.BigDecimal

data class Vaga(
    val foto: String? = null,
    val nomeEmpresa: String,
    val cargo: String,
    val descricao: String,
    val localizacao: String,
    val data: String,
    val salario: BigDecimal
){}
