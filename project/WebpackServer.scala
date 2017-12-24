import java.io.File
import java.net.InetSocketAddress

import play.sbt.PlayRunHook
import scala.sys.process._
import com.typesafe.config._

object WebpackServer {
  def apply(base: File): PlayRunHook = {
    object WebpackServerScript extends PlayRunHook {
      var process: Option[Process] = None
      val config: Config = ConfigFactory.parseFile(new File("conf/frontend.conf")).resolve()
      val isWin: Boolean = System.getProperty("os.name").toUpperCase().indexOf("WIN") >= 0
      override def afterStarted(add: InetSocketAddress): Unit = {
        val port = config.getInt("webpack.port")
        process = if (isWin)
          Option(Process(s"cmd /c npm run watch -- --port $port", base).run)
        else
          Option(Process(s"npm run watch -- --port $port", base).run)
      }
      override def afterStopped(): Unit = {
        process.foreach(p => { p.destroy() })
        process = None
      }
    }

    WebpackServerScript
  }
}
