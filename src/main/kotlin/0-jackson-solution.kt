import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import java.text.SimpleDateFormat
import java.util.*

val mapper = jacksonObjectMapper().apply {
    dateFormat = SimpleDateFormat("yyyy.MM.dd")
}

data class Lesson(
    val name: String = "",
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    val date: Date
)

fun main() {
    val lesson = Lesson("Java Date", Date(System.currentTimeMillis()))
    val jsonLesson = mapper.writeValueAsString(lesson)

    println(jsonLesson)
}