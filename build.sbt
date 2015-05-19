enablePlugins(ScalaJSPlugin)

name := "LoveScalaJS"

scalaVersion := "2.11.6"

scalaJSStage in Global := FastOptStage

libraryDependencies += "be.doeraene" %%% "scalajs-jquery" % "0.8.0"

skip in packageJSDependencies := false