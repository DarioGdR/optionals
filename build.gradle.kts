

plugins {
    kotlin("jvm") version "1.4.21"
    `maven-publish`
    id("com.jfrog.bintray") version "1.8.4"
}

val sourcesJar by tasks.creating(Jar::class) {
    archiveClassifier.set("sources")
    from(sourceSets.getByName("main").allSource)
}

val artifactName = "optionals"
val artifactGroup = "com.dariogdr"

val pomUrl = "https://github.com/darioGdR/optionals/"
val pomScmUrl = "https://github.com/darioGdR/optionals/"
val pomIssueUrl = "https://github.com/darioGdR/optionals/"
val pomDesc = "Optionals"
val pomScmConnection = "https://github.com/darioGdR/optionals/"
val pomScmDevConnection = "https://github.com/darioGdR/optionals/"

val githubRepo = "https://github.com/darioGdR/optionals/"
val githubReadme = "https://github.com/darioGdR/optionals/README.md"

val pomLicenseName = "The Apache Software License, Version 2.0"
val pomLicenseUrl = "http://www.apache.org/licenses/LICENSE-2.0.txt"
val pomLicenseDist = "repo"

val pomDeveloperId = "dariogdr"
val pomDeveloperName = "Dario GdR"

publishing {
    publications {
        create<MavenPublication>("lib") {
            groupId = artifactGroup
            artifactId = artifactName
            version = "1.0"
            from(components["java"])
            artifact(sourcesJar)

            pom.withXml {
                asNode().apply {
                    appendNode("description", pomDesc)
                    appendNode("name", rootProject.name)
                    appendNode("url", pomUrl)
                    appendNode("licenses").appendNode("license").apply {
                        appendNode("name", pomLicenseName)
                        appendNode("url", pomLicenseUrl)
                        appendNode("distribution", pomLicenseDist)
                    }
                    appendNode("developers").appendNode("developer").apply {
                        appendNode("id", pomDeveloperId)
                        appendNode("name", pomDeveloperName)
                    }
                    appendNode("scm").apply {
                        appendNode("url", pomScmUrl)
                        appendNode("connection", pomScmConnection)
                    }
                }
            }
        }
    }
}

val bintrayUser = findProperty("bintrayUser") as String?
val bintrayKey = findProperty("bintrayKey") as String?
bintray {
    // Getting bintray user and key from properties file or command line
    user = bintrayUser
    key = bintrayKey

    // Automatic publication enabled
    publish = true

    // Set maven publication onto bintray plugin
    setPublications("lib")

    // Configure package
    pkg.apply {
        repo = "optionals"
        name = rootProject.name
        userOrg = "dario-gdr"
        setLicenses("Apache-2.0")
        setLabels("Kotlin")
        vcsUrl = pomScmUrl
        websiteUrl = pomUrl
        issueTrackerUrl = pomIssueUrl
        githubRepo = githubRepo
        githubReleaseNotesFile = githubReadme

        // Configure version
        version.apply {
            name = "1.0"
            desc = pomDesc
            vcsTag = "1.0"
        }
    }
}

repositories {
    jcenter()
    mavenCentral()
}

dependencies {

}