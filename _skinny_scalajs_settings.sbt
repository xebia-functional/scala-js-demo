lazy val scalajs = (project in file("src/main/webapp/WEB-INF/assets")).settings(
  name := "application", // JavaScript file name
  scalaVersion := "2.11.5",
  unmanagedSourceDirectories in Compile <+= baseDirectory(_ / "scala"),
  libraryDependencies ++= Seq(
    "org.scala-js" %%% "scalajs-dom"    % "0.8.0",
    "be.doeraene"  %%% "scalajs-jquery" % "0.8.0",
    "org.monifu"   %%  "minitest"       % "0.11" % "test"
  ),
  crossTarget in Compile <<= baseDirectory(_ / ".." / ".." / "assets" / "js")
).enablePlugins(ScalaJSPlugin)
