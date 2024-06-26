import com.typesafe.sbt.site.SitePlugin.autoImport._
import com.typesafe.sbt.site.SiteScaladocPlugin.autoImport._
import mdoc.MdocPlugin.autoImport._
import microsites.MicrositesPlugin.autoImport._
import sbt.Keys._
import sbt._
import sbtunidoc.ScalaUnidocPlugin.autoImport._

object BuildSupport {
  lazy val commonSettings = Seq(
    crossScalaVersions := List("2.12.18", "2.13.14"),
    scalacOptions := Seq(
      "-release",
      "11",
      "-encoding",
      "UTF-8",
      "-feature",
      "-language:existentials",
      "-language:higherKinds",
      "-language:implicitConversions",
      "-language:experimental.macros",
      "-unchecked",
      "-Ywarn-dead-code",
      "-Ywarn-value-discard",
      "-Xfatal-warnings",
      "-deprecation",
      "-Xlint:-unused,_"
    ),
    Test / scalacOptions --= Seq("-Ywarn-dead-code", "-Ywarn-value-discard")
  )

  lazy val micrositeSettings = Seq(
    micrositeName := "slog4s",
    micrositeDescription := "Structured and contextual logging for Scala",
    micrositeAuthor := "Jan Strnad",
    micrositeGithubOwner := "avast",
    micrositeGithubRepo := "slog4s",
    micrositeUrl := "https://avast.github.io",
    micrositeDocumentationUrl := "api/latest",
    micrositeBaseUrl := "/slog4s",
    micrositeFooterText := None,
    micrositeGitterChannel := false,
    micrositeTheme := "pattern",
    mdoc / fork := true,
    mdocIn := file("site") / "docs",
    mdocVariables := Map("VERSION" -> version.value),
    mdocAutoDependency := false,
    micrositeDataDirectory := file("site"),
    ScalaUnidoc / siteSubdirName := "api/latest",
    addMappingsToSiteDir(
      ScalaUnidoc / packageDoc / mappings,
      ScalaUnidoc / siteSubdirName
    )
  )

}
