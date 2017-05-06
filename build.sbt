name := "play-vue-webpack"

version := "1.0"

scalaVersion := "2.11.7"

lazy val `frontend` = (project in file(".")).enablePlugins(PlayScala)

libraryDependencies ++= Seq( jdbc , cache , ws   , specs2 % Test )

PlayKeys.playRunHooks += WebpackServer(file("./front"))

unmanagedResourceDirectories in Test <+=  baseDirectory ( _ /"target/web/public/test" )  

resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"  