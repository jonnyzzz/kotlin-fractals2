plugins {
  kotlin("jvm") version "1.3.31"
}

repositories {
  jcenter()
  mavenCentral()
}

val ktorVersion = "1.2.1"
val logbackVersion = "1.2.3"

dependencies {
  implementation(kotlin("stdlib-jdk8"))
  implementation("io.ktor:ktor-server-netty:$ktorVersion")
  implementation("io.ktor:ktor-html-builder:$ktorVersion")
  implementation("ch.qos.logback:logback-classic:$logbackVersion")
}


