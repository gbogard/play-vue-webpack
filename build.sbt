import com.typesafe.sbt.packager.MappingsHelper._
mappings in Universal ++= directory(baseDirectory.value / "public")

name := "play-vue-webpack"

version := "1.1"

scalaVersion := "2.12.2"

lazy val `play-vue-webpack` = (project in file(".")).enablePlugins(PlayScala)

libraryDependencies ++= Seq(guice, filters, jdbc , cacheApi ,ws , specs2 % Test)

// Play framework hooks for development
PlayKeys.playRunHooks += WebpackServer(file("./front"))

unmanagedResourceDirectories in Test +=  baseDirectory ( _ /"target/web/public/test" ).value

resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"

// Production front-end build
lazy val cleanFrontEndBuild = taskKey[Unit]("Remove the old front-end build")

cleanFrontEndBuild := {
  val d = file("public/bundle")
  if (d.exists()) {
    d.listFiles.foreach(f => {
      if(f.isFile) f.delete
    })
  }
}

lazy val frontEndBuild = taskKey[Unit]("Execute the npm build command to build the front-end")

val buildCommandPrefix = sys.props.get("os.name").filter(_.toUpperCase.contains("WIN")).map(_ => "cmd /c npm").getOrElse("npm")

frontEndBuild := {
  println(Process(s"$buildCommandPrefix install", file("front")).!!)
  println(Process(s"$buildCommandPrefix run build", file("front")).!!)
}

frontEndBuild := (frontEndBuild dependsOn cleanFrontEndBuild).value

stage := (stage dependsOn frontEndBuild).value
dist := (dist dependsOn frontEndBuild).value
