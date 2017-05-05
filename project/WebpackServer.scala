import java.io.File
import java.net.InetSocketAddress

import play.sbt.PlayRunHook
import sbt._

object WebpackServer {
  def apply(base: File): PlayRunHook = {
    object WebpackServerScript extends PlayRunHook {
      var process: Option[Process] = None
      override def afterStarted(add: InetSocketAddress): Unit = {
        process = if (System.getProperty("os.name").toUpperCase().indexOf("WIN") >= 0)
          Option(Process("cmd /c npm watch", base).run)
        else
          Option(Process("npm watch", base).run)
      }

      override def afterStopped(): Unit = {
        process.foreach(p => { p.destroy() })
        process = None
      }
    }

    WebpackServerScript
  }
}