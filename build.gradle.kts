val kotlinVersion = "1.4.0"
val kotlinxVersion = "1.3.9"
val jacksonVersion = "2.11.0"

buildscript {}

plugins {
    kotlin("jvm") version ("1.4.0")
    `java-library`
    `maven-publish`
    id("com.jfrog.bintray") version "1.8.4"
}

val artifactName = project.name
val artifactGroup = project.group.toString()
val artifactVersion = project.version.toString()

val pomUrl = "https://github.com/dariogdr/optionals"
val pomScmUrl = "https://github.com/dariogdr/optionals"
val pomIssueUrl = "https://github.com/dariogdr/optionals/issues"
val pomDesc = "https://github.com/dariogdr/optionals"

val githubRepo = "dariogdr/optionals"
val githubReadme = "README.md"

val pomLicenseName = "MIT"
val pomLicenseUrl = "https://opensource.org/licenses/mit-license.php"
val pomLicenseDist = "repo"

val pomDeveloperId = "dariogdr"
val pomDeveloperName = "Dario GdR"

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
                name.set("My Library")
                description.set("A concise description of my library")
                url.set("http://www.dariogdr.com/")
                properties.set(mapOf())
                artifact(sourcesJar)
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
                scm {
                    url.set("https://github.com/dariogdr/optionals")
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

bintray {
    user = project.findProperty("bintrayUser").toString()
    key = project.findProperty("bintrayKey").toString()
    publish = true

    setPublications("optionals")

    pkg.apply {
        repo = "optionals"
        name = artifactName
        userOrg = "dariogdr"
        githubRepo = githubRepo
        vcsUrl = pomScmUrl
        description = "Kotlin Optionals"
        setLicenses("MIT")
        desc = description
        websiteUrl = pomUrl
        issueTrackerUrl = pomIssueUrl
        githubReleaseNotesFile = githubReadme

        version.apply {
            name = artifactVersion
            desc = pomDesc
            vcsTag = artifactVersion
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

val sourcesJar by tasks.creating(Jar::class) {
    archiveClassifier.set("sources")
    from(sourceSets.getByName("main").allSource)
}
