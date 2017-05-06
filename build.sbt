import com.typesafe.sbt.packager.MappingsHelper._
mappings in Universal ++= directory(baseDirectory.value / "public")

name := "play-vue-webpack"

version := "1.0"

scalaVersion := "2.11.7"

lazy val `play-vue-webpack` = (project in file(".")).enablePlugins(PlayScala)

libraryDependencies ++= Seq( filters, jdbc , cache , ws   , specs2 % Test )

// Play framework hooks for development
PlayKeys.playRunHooks += WebpackServer(file("./front"))

unmanagedResourceDirectories in Test <+=  baseDirectory ( _ /"target/web/public/test" )  

resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"

// Production front-end build
lazy val frontEndBuild = taskKey[Unit]("Execute the npm build command to build the front-end")

frontEndBuild := {
  println(Process("npm install", file("front")).!!)
  println(Process("npm run build", file("front")).!!)
}

dist <<= dist dependsOn frontEndBuild