package doggy.back.train

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.web.bind.annotation.*
import org.springframework.web.client.RestTemplate
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@RestController
class TrainController {

    @Autowired lateinit var restTemplate: RestTemplate

    private var train: Train? = null

    @GetMapping("/train")
    fun getTrain(): Train? {
        val train = this.train
        if (train != null) {
            val date = LocalDateTime.parse(train.end)
            if (date.toLocalDate() != LocalDate.now()) {
                this.train = null
            }
        }
        return this.train
    }

    @GetMapping("/train/delete")
    fun getTrainDelete() = "yolo".also { train = null }

    @PostMapping("/train")
    fun createTrain(@RequestBody body: CreateTrainBody) {
        train = Train(
            members = mutableListOf(body.chef),
            start = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME),
            end = LocalDateTime.now()
                .withHour(body.end.substringBefore('h').toInt())
                .withMinute(body.end.substringAfter('h').toInt())
                .format(DateTimeFormatter.ISO_DATE_TIME),
            place = body.place

        )
        sendNotif("Le train de la bière vient de partir !", "Monte vite à bord")
        println(train)
    }

    @PutMapping("/train")
    fun updateTrain(@RequestBody body: UpdateTrainBody) {
        println("je suis dans le put avec $body")
        if (train == null) return
        if (!body.isOnboard && !train!!.members.contains(body.name)) {
            train!!.members.add(body.name)
            sendNotif("${body.name} vient de monter dans le train !", "Rejoins le vite !")
            println("j'ai ajouté $train")
        } else if (body.isOnboard.not()){
            train!!.members.remove(body.name)
            sendNotif("${body.name} vient de quitter le train !", "Honte à lui !")
            println("j'ai remove un putin de fdp $train")
        }
    }

    private fun sendNotif(title: String, message: String) {
        val headers = HttpHeaders().apply {
            set("Content-Type", "application/json")
            set("Authorization", "key=AAAA_5iIEEM:APA91bFEL_PDP0nB3RegZN12_rxB2bts2GuHQDStK0p_tj994kMjXVaPERjmSpxqtHWfWJhn0_ZApJ4yVNGnl4fubg00TuEGivzZcjntsWvKx_b0DFXeoTGTyG3Of3ia_j_gn0MiOAV6")
        }
        val reponse = restTemplate.exchange(
            "https://fcm.googleapis.com/fcm/send",
            HttpMethod.POST,
            HttpEntity<Any>(RequestBody2(
                RequestNotification(title, message)
            ), headers),
            String::class.java
        )
    }

    data class RequestBody2(
        val notification: RequestNotification,
        val to: String = "/topics/all"
    )

    data class RequestNotification(
        val title: String,
        val body: String
    )
}

data class Train(
    val members: MutableList<String> = mutableListOf("rdo", "bej", "laal"),
    val start: String = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME),
    val end: String = LocalDateTime.now().plusHours(2).format(DateTimeFormatter.ISO_DATE_TIME),
    val place: String = "au 5"
)

data class CreateTrainBody(
    val chef: String,
    val end: String,
    val place: String
)

data class UpdateTrainBody(
    val name: String,
    val isOnboard: Boolean
)
