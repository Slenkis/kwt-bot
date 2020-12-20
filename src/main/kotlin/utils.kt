import org.jsoup.nodes.Document
import us.codecraft.xsoup.Xsoup

fun Document.getTagContent(xpath: String): String = Xsoup
    .compile("$xpath/text()")
    .evaluate(this)
    .get()