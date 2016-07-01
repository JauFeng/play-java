name := """play-java"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava, PlayEbean)

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  javaJdbc,
  cache,
  javaWs,

  "redis.clients" % "jedis" % "2.8.1",
  "mysql" % "mysql-connector-java" % "5.1.39",


  "org.avaje.ebeanorm" % "avaje-ebeanorm" % "7.16.2"

//  "org.avaje.ebeanorm" % "querybean-generator" % "2.3.1",
//  "org.avaje.ebeanorm" % "avaje-ebeanorm-querybean" % "7.14.1",
//  "org.avaje.ebeanorm" % "querybean-agent" % "2.2.1",
//
//  "org.avaje.ebeanorm" % "avaje-ebeanorm-typequery-generator" % "1.5.3"

)


//fork in run := true