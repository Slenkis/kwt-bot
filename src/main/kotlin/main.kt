import com.github.kotlintelegrambot.bot
import com.github.kotlintelegrambot.dispatch
import com.github.kotlintelegrambot.dispatcher.command
import com.github.kotlintelegrambot.dispatcher.text
import io.github.cdimascio.dotenv.dotenv
import org.jsoup.Jsoup
import java.net.URL

fun main() {
    val url = URL(dotenv()["YANDEX_URL"] ?: throw IllegalArgumentException("No url provided"))
    val botToken = dotenv()["BOT_TOKEN"] ?: throw IllegalArgumentException("No token provided")

    val bot = bot {
        token = botToken
        dispatch {
            // "/location" command
            command("location") {
                bot.sendMessage(message.chat.id, "Введите локацию:")
            }
            // any text request
            text {
                val weather = Weather(Jsoup.parse(url, 300))
                val weatherMessage = buildString {
                    appendLine("Время: ${weather.currentTime}")
                    appendLine("--------------")
                    appendLine("Сейчас: ${weather.currentTemp}°, ${weather.currentSky}")
                    appendLine("Ощущается: ${weather.currentTempOrient}°")
                    appendLine("Вчера: ${weather.yesterdayTemp}°")
                    appendLine("--------------")
                    appendLine("Влажность: ${weather.currentHumidity}")
                    appendLine("Ветер: ${weather.currentWindSpeed} м/с")
                    appendLine("--------------")
                    appendLine("Восход: ${weather.todaySunrise}")
                    appendLine("Закат: ${weather.todaySunset}")
                }
                println(weatherMessage) // for debug
                bot.sendMessage(chatId = message.chat.id, text = weatherMessage, disableNotification = true)
            }
        }
    }

    bot.startPolling()
}
