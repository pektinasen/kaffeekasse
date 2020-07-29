@JSExport("SPAMain")
object SPAMain extends JSApp {

  @JSExport
  def main(args: Array[String]): Unit = {
    println("Hello world!")

    React.render(router(), dom.document.getElementById("root"))
  }
}
