import org.jetbrains.kotlin.gradle.tasks.KotlinCompile



plugins {
    kotlin("jvm") version "1.6.20"
    application
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

group = "com.brakezap.KotlinPlugin"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven { url = uri("https://papermc.io/repo/repository/maven-public/") }
    maven { url = uri("https://redempt.dev") }
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.18.2-R0.1-SNAPSHOT")
    implementation("com.github.Redempt:RedLib:6.5.2")
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}

tasks {
    jar {
        manifest {
            attributes(Pair("Main-Class", "com.brakezap.RegionTest"))
        }
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "16"
}




application {
    // Define the main class for the application.
    mainClass.set("com.brakezap.RegionTest")
}














//def targetJavaVersion = 17
//java {
//    def javaVersion = JavaVersion.toVersion(targetJavaVersion)
//    sourceCompatibility = javaVersion
//    targetCompatibility = javaVersion
//    if (JavaVersion.current() < javaVersion) {
//        toolchain.languageVersion = JavaLanguageVersion.of(targetJavaVersion)
//    }
//}
//
//tasks.withType(JavaCompile).configureEach {
//    if (targetJavaVersion >= 10 || JavaVersion.current().isJava10Compatible()) {
//        options.release = targetJavaVersion
//    }
//}
//
//processResources {
//    def props = [version: version]
//    inputs.properties props
//    filteringCharset 'UTF-8'
//    filesMatching('plugin.yml') {
//        expand props
//    }
//}
