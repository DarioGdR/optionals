val kotlinVersion = "1.4.0"
val kotlinxVersion = "1.3.9"
val jacksonVersion = "2.11.0"

buildscript {}

plugins {
    kotlin("jvm") version ("1.4.0")
    `java-library`
    `maven-publish`
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "com.dariogdr"
            artifactId = "optionals"
            version = "1.0"

            from(components["java"])
        }
        create<MavenPublication>("mavenJava") {
            pom {
                name.set("Kotlin Optionals")
                description.set("Kotlin Optionals")
                url.set("https://github.com/dariogdr/optionals")
                properties.set(
                    mapOf(
                    )
                )
                licenses {
                    license {
                        name.set("The Apache License, Version 2.0")
                        url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                    }
                }
                developers {
                    developer {
                        id.set("dariogdr")
                        name.set("Dario Gdr")
                        email.set("dgdr1991@gmail.com")
                    }
                }
            }
            versionMapping {
                usage("java-api") {
                    fromResolutionOf("runtimeClasspath")
                }
                usage("java-runtime") {
                    fromResolutionResult()
                }
            }
        }
        repositories {
            maven {
                url = uri("https://github.com/dariogdr/optionals")
            }

        }
    }
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
