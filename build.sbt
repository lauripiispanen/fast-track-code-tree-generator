organization := "fi.reaktor"

name := "Fast Track Code Tree Generator"

version := "0.1.0-SNAPSHOT"

scalaVersion := "2.9.2"

mainClass in Compile := Some("JettyLauncher")

seq(webSettings :_*)

classpathTypes ~= (_ + "orbit")

libraryDependencies ++= Seq(
  "org.scalatra" % "scalatra" % "2.1.1",
  "org.scalatra" % "scalatra-scalate" % "2.1.1",
  "org.scalatra" % "scalatra-specs2" % "2.1.1" % "test",
  "ch.qos.logback" % "logback-classic" % "1.0.6" % "runtime",
  "org.eclipse.jetty" % "jetty-webapp" % "8.1.7.v20120910" % "compile;container;test",
  "org.eclipse.jetty.orbit" % "javax.servlet" % "3.0.0.v201112011016" % "compile;container;provided;test" artifacts (Artifact("javax.servlet", "jar", "jar"))
)

com.typesafe.sbt.SbtNativePackager.packageArchetype.java_application

