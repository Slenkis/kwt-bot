import com.github.kotlintelegrambot.bot
import com.github.kotlintelegrambot.dispatch
import com.github.kotlintelegrambot.dispatcher.command
import com.github.kotlintelegrambot.dispatcher.text
import io.github.cdimascio.dotenv.dotenv
import org.jsoup.Jsoup
import java.net.URL

fun main() {
    val page = Jsoup.parse(URL("https://yandex.ru/weather/moscow"), 300)
    println("Температура: ${page.getTagContent(Weather.currentTemp)}°")
    println("Ощущается как: ${page.getTagContent(Weather.currentTempOrient)}°")
    println("Влажность: ${page.getTagContent(Weather.currentHumidity)}")
    println("Ветер: ${page.getTagContent(Weather.currentWindSpeed)} м/с")

    val bot = bot {
        token = dotenv()["BOT_TOKEN"] ?: throw IllegalArgumentException("No token provided")
        dispatch {
            command("location") {
                bot.sendMessage(message.chat.id, "Введите локацию:")
            }
            text {
                val weather = buildString {
                    appendLine(
                        "Погода на ${
                            page.getTagContent(Weather.currentTime)
                                .replace("Сейчас ", "")
                                .dropLast(2)
                        }"
                    )
                    appendLine("--------------")
                    append("Сейчас: ${page.getTagContent(Weather.currentTemp)}°, ")
                    appendLine(page.getTagContent(Weather.currentSky))
                    appendLine("Ощущается: ${page.getTagContent(Weather.currentTempOrient)}°")
                    appendLine("Вчера: ${page.getTagContent(Weather.yesterdayTemp)}°")
                    appendLine("--------------")
                    appendLine("Влажность: ${page.getTagContent(Weather.currentHumidity)}")
                    appendLine("Ветер: ${page.getTagContent(Weather.currentWindSpeed)} м/с")
                    appendLine("--------------")
                    appendLine("Восход: ${page.getTagContent(Weather.todaySunrise)}")
                    appendLine("Закат: ${page.getTagContent(Weather.todaySunset)}")
                }
                bot.sendMessage(chatId = message.chat.id, text = weather, disableNotification = true)
            }
        }
    }
    bot.startPolling()
}
