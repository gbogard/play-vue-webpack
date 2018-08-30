import com.typesafe.sbt.packager.MappingsHelper._

import scala.sys.process.Process

mappings in Universal ++= directory(baseDirectory.value / "public")

name := "play-vue-webpack"

version := "1.2"

scalaVersion := "2.12.4"

lazy val `play-vue-webpack` = (project in file(".")).enablePlugins(PlayScala)

libraryDependencies ++= Seq(guice, filters, specs2 % Test)

// Play framework hooks for development
PlayKeys.playRunHooks += WebpackServer(file("./front"))

unmanagedResourceDirectories in Test += baseDirectory(_ / "target/web/public/test").value

// Production front-end build
lazy val cleanFrontEndBuild = taskKey[Unit]("Remove the old front-end build")

cleanFrontEndBuild := {
  val d = file("public/bundle")
  if (d.exists()) {
    d.listFiles.foreach(f => {
      if (f.isFile) f.delete
    })
  }
}

lazy val frontEndBuild = taskKey[Unit]("Execute the npm build command to build the front-end")

frontEndBuild := {
  println(Process("npm install", file("front")).!!)
  println(Process("npm run build", file("front")).!!)
}

frontEndBuild := (frontEndBuild dependsOn cleanFrontEndBuild).value

dist := (dist dependsOn frontEndBuild).value
