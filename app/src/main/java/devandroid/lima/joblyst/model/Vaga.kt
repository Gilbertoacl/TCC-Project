package devandroid.lima.joblyst.model

import java.math.BigDecimal
import java.time.LocalDate

data class Vaga(
    val foto: String?,
    val cargo: String,
    val descricao: String,
    val localizacao: String,
    val data: LocalDate,
    val salario: BigDecimal
){}
