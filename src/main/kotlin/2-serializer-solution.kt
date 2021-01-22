import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encodeToString
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.Json
import java.util.*

enum class WeekType { TRAINING, SESSION, HOLIDAY }

object WeekTypeSerializer : KSerializer<WeekType> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("WeekType", PrimitiveKind.LONG)
    override fun deserialize(decoder: Decoder) =
        when (decoder.decodeString()) {
            "Обучение" -> WeekType.TRAINING
            "Сессия" -> WeekType.SESSION
            else -> WeekType.HOLIDAY
        }

    override fun serialize(encoder: Encoder, value: WeekType) =
        encoder.encodeString(
            when (value) {
                WeekType.TRAINING -> "Обучение"
                WeekType.SESSION -> "Сессия"
                WeekType.HOLIDAY -> "Каникулы"
            }
        )
}

@Serializable
class Week(
    val number: Int,
    @Serializable(with = WeekTypeSerializer::class)
    val type: WeekType
)

fun main() {
    val semester = arrayListOf(
        1 to WeekType.TRAINING,
        2 to WeekType.TRAINING,
        3 to WeekType.SESSION,
        4 to WeekType.HOLIDAY
    )
    val semester2 = semester.map { Week(it.first, it.second) }

    println(Json.encodeToString(semester))
    println(Json.encodeToString(semester2))

}
