plugins {
  kotlin("multiplatform") version "1.3.31"
}

repositories {
  jcenter()
  mavenCentral()
}

kotlin {
  jvm()

  val ktorVersion = "1.2.1"
  val logbackVersion = "1.2.3"

  sourceSets["jvmMain"].dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("io.ktor:ktor-server-netty:$ktorVersion")
    implementation("io.ktor:ktor-html-builder:$ktorVersion")
    implementation("ch.qos.logback:logback-classic:$logbackVersion")
  }

  val run by tasks.creating(JavaExec::class) {
    group = "application"
    dependsOn(targets["jvm"].compilations["main"].compileAllTaskName)
    main = "org.jonnyzzz.kotlin.fractals2.MainKt"
    classpath(
      { targets["jvm"].compilations["main"].output.allOutputs.files },
      { configurations["jvmRuntimeClasspath"] }
    )
    ///disable app icon on macOS
    systemProperty("java.awt.headless", "true")
  }
}
