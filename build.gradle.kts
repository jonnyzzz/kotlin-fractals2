import org.jetbrains.kotlin.gradle.targets.js.KotlinJsTarget

plugins {
  kotlin("multiplatform") version "1.3.40"
}

repositories {
  jcenter()
  mavenCentral()
}

kotlin {
  js {
    useCommonJs()
    browser()
  }

  jvm()

  val ktorVersion = "1.2.1"
  val logbackVersion = "1.2.3"
  val kotlinxHtmlVersion = "0.6.12"

  sourceSets["commonMain"].dependencies {
    implementation(kotlin("stdlib-common"))
    implementation("org.jetbrains.kotlinx:kotlinx-html-common:$kotlinxHtmlVersion")
  }

  sourceSets["jsMain"].dependencies {
    implementation(kotlin("stdlib-js"))
    implementation("org.jetbrains.kotlinx:kotlinx-html-js:$kotlinxHtmlVersion")
  }

  sourceSets["jvmMain"].dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("io.ktor:ktor-server-netty:$ktorVersion")
    implementation("io.ktor:ktor-html-builder:$ktorVersion")
    implementation("ch.qos.logback:logback-classic:$logbackVersion")
  }

  val run by tasks.creating(JavaExec::class) {
    group = "application"
    dependsOn(targets["jvm"].compilations["main"].compileAllTaskName)
    (targets["js"] as KotlinJsTarget).browser.webpackTask {}
    dependsOn("jsBrowserWebpack")
    main = "org.jonnyzzz.kotlin.fractals2.MainKt"
    classpath(
      { targets["jvm"].compilations["main"].output.allOutputs.files },
      { configurations["jvmRuntimeClasspath"] }
    )
    ///disable app icon on macOS
    systemProperty("java.awt.headless", "true")
  }
}
