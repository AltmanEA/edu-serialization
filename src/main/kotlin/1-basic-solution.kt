import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Serializable
data class Course(
    val name: String = "",
    @SerialName("tutor") val person: Person? = null
)

fun main(){
    val course = Course("Math", Person("Leonard"))
    val mathJson = Json.encodeToString(course)
    val physJson = Json.decodeFromString<Course>("{\"name\": \"Phys\"}")

    println(mathJson)
    println(physJson)
}