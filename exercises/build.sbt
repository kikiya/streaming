version in ThisBuild := "1.0-SNAPSHOT"

// the Scala version that will be used for cross-compiled libraries
scalaVersion in ThisBuild := "2.12.4"

def project(id: String) = Project(s"${id}", base = file(id))
  .settings(javacOptions in compile ++= Seq("-encoding", "UTF-8", "-source", "1.8", "-target", "1.8", "-Xlint:unchecked", "-Xlint:deprecation"))

lazy val `chirper-demo` = project("chirper-demo")
  .settings(common: _*)
  .settings(
    libraryDependencies ++= Seq(
      "com.typesafe.akka" %% "akka-http" % "10.1.0",
      "com.typesafe.akka" %% "akka-http-jackson" % "10.1.0",
      "com.typesafe.akka" %% "akka-stream" % "2.5.11",
      "com.fasterxml.jackson.core" % "jackson-databind" % "2.9.4",
      "org.junit.jupiter" % "junit-jupiter-engine" % "5.0.2" % "test"
    ),
    mainClass in (Compile, run)  := Some("demo.ChirperClientSimulator")
  )

run in Compile := (run in Compile in `chirper-demo`).evaluated

def common = Seq(
  javacOptions in compile += "-parameters"
)

aggregateProjects(`chirper-demo`)

