import org.jsoup.nodes.Document

class Weather(private val page: Document) {
    private val currentTimePath = "/html/body/div[1]/div[6]/div[1]/div/div[2]/div[1]/div[3]/time"

    private val currentTempPath = "/html/body/div[1]/div[6]/div[1]/div/div[2]/div[1]/div[5]/a/div[1]/span[2]"
    private val currentSkyPath = "/html/body/div[1]/div[6]/div[1]/div/div[2]/div[1]/div[5]/a/div[2]/div[1]"
    private val currentTempOrientPath =
        "/html/body/div[1]/div[6]/div[1]/div/div[2]/div[1]/div[5]/a/div[2]/div[2]/div[2]/div/span[1]"
    private val yesterdayTempPath = "/html/body/div[1]/div[6]/div[1]/div/div[2]/div[1]/div[3]/div/div[2]/div/span[1]"

    private val currentHumidityPath = "/html/body/div[1]/div[6]/div[1]/div/div[2]/div[1]/div[7]/div[2]/div[2]"
    private val currentWindSpeedPath = "/html/body/div[1]/div[6]/div[1]/div/div[2]/div[1]/div[7]/div[1]/div[2]/span[1]"

    private val todaySunsetPath = "/html/body/div[1]/div[6]/div[3]/div/div/div[1]/div[2]/div[1]/div[3]/div[2]"
    private val todaySunrisePath = "/html/body/div[1]/div[6]/div[3]/div/div/div[1]/div[2]/div[1]/div[3]/div[1]"

    val currentTime: String
        get() = page.getTagContent(currentTimePath)
            .replace("Сейчас ", "")
            .dropLast(2)

    val currentTemp: String
        get() = page.getTagContent(currentTempPath)

    val currentSky: String
        get() = page.getTagContent(currentSkyPath)

    val currentTempOrient: String
        get() = page.getTagContent(currentTempOrientPath)

    val yesterdayTemp: String
        get() = page.getTagContent(yesterdayTempPath)

    val currentHumidity: String
        get() = page.getTagContent(currentHumidityPath)

    val currentWindSpeed: String
        get() = page.getTagContent(currentWindSpeedPath)

    val todaySunset: String
        get() = page.getTagContent(todaySunsetPath)

    val todaySunrise: String
        get() = page.getTagContent(todaySunrisePath)

}