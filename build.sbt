name := """slick-starting-on-the-right-foot"""

version := "1.0"

scalaVersion := "2.12.6"



libraryDependencies ++= Seq(
  "mysql" % "mysql-connector-java" % "5.1.36",
  "com.typesafe.slick" %% "slick" % "3.2.3",
  "com.typesafe.slick" %% "slick-hikaricp" % "3.2.3",
  "ch.qos.logback" % "logback-classic" % "1.1.3",
  "org.scalatest" %% "scalatest" % "3.0.5" % "test",
  "com.h2database" % "h2" % "1.4.187" % "test"
)


