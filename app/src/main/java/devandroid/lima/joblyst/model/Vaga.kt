package devandroid.lima.joblyst.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.math.BigDecimal
import java.time.LocalDate

@Parcelize
data class Vaga(
    val foto: String?,
    val cargo: String,
    val descricao: String,
    val localizacao: String,
    val data: LocalDate,
    val salario: BigDecimal
): Parcelable
