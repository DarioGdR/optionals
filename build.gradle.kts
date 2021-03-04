val kotlinVersion = "1.4.0"
val kotlinxVersion = "1.3.9"
val jacksonVersion = "2.11.0"

buildscript {}

plugins {
    kotlin("jvm") version ("1.4.0")
    application
    java
}

application {
    mainClassName = "com.dariogdr.optionals.MainKt"
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
}

repositories {
    mavenCentral()
    jcenter()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib:$kotlinVersion")
    implementation("org.jetbrains.kotlin:kotlin-reflect:$kotlinVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$kotlinxVersion")

    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:$jacksonVersion")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:$jacksonVersion")

}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = "11"
    }
}

tasks.withType<Jar> {
    manifest {
        attributes(mapOf("Main-Class" to application.mainClassName))
    }
}
