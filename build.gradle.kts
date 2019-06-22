plugins {
  kotlin("multiplatform") version "1.3.31"
  application
}

repositories {
  jcenter()
  mavenCentral()
}

kotlin {
  jvm()
}

val ktorVersion = "1.2.1"
val logbackVersion = "1.2.3"

kotlin.sourceSets["jvmMain"].dependencies {
  implementation(kotlin("stdlib-jdk8"))
  implementation("io.ktor:ktor-server-netty:$ktorVersion")
  implementation("io.ktor:ktor-html-builder:$ktorVersion")
  implementation("ch.qos.logback:logback-classic:$logbackVersion")
}

application.mainClassName = "org.jonnyzzz.kotlin.fractals2.MainKt"

tasks.run.configure {
  systemProperty("java.awt.headless", "true")
}
