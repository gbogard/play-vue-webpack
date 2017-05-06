import java.io.File
import java.net.InetSocketAddress

import play.sbt.PlayRunHook
import sbt._
import com.typesafe.config._

object WebpackServer {
  def apply(base: File): PlayRunHook = {
    object WebpackServerScript extends PlayRunHook {
      var process: Option[Process] = None
      val config: Config = ConfigFactory.parseFile(new File("conf/frontend.conf")).resolve()
      val isWin: Boolean = System.getProperty("os.name").toUpperCase().indexOf("WIN") >= 0
      override def afterStarted(add: InetSocketAddress): Unit = {
        val port = config.getInt("webpack.port")
        val npmPath: String = if(!config.getIsNull("npm.path")) config.getString("npm.path") else "npm"
        process = if (isWin)
          Option(Process(s"cmd /c $npmPath run watch -- --port $port", base).run)
        else
          Option(Process(s"$npmPath run watch -- --port $port", base).run)
      }
      override def afterStopped(): Unit = {
        process.foreach(p => { p.destroy() })
        process = None
      }
    }

    WebpackServerScript
  }
}