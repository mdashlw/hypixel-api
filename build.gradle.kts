import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.3.21"
    id("com.jfrog.bintray") version "1.8.4"
    `maven-publish`
}

group = "com.github.mdashl"
version = "1.0.0"

repositories {
    jcenter()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("com.squareup.okhttp3:okhttp:3.13.1")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.9.8")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.9.8")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

tasks.register<Jar>("sourcesJar") {
    from(sourceSets.main.get().allSource)
    archiveClassifier.set("sources")
}

tasks.register<Jar>("javadocJar") {
    from(tasks.javadoc)
    archiveClassifier.set("javadoc")
}

publishing {
    publications {
        create<MavenPublication>("BintrayRelease") {
            artifactId = "hypixel-api"

            from(components["java"])
            artifact(tasks["sourcesJar"])
            artifact(tasks["javadocJar"])

            pom {
                name.set("hypixel-api")
                description.set("Kotlin Hypixel API wrapper")
                licenses {
                    license {
                        name.set("MIT License")
                        url.set("https://choosealicense.com/licenses/mit/")
                    }
                }
                developers {
                    developer {
                        id.set("mdashlw")
                        name.set("Mdashlw")
                        email.set("mdashlw@gmail.com")
                    }
                }
                scm {
                    connection.set("scm:git:https://github.com/mdashl/hypixel-api.git")
                }
            }
        }
    }
}

bintray {
    user = System.getenv("BINTRAY_USER")
    key = System.getenv("BINTRAY_API_KEY")

    setPublications("BintrayRelease")

    publish = true

    with(pkg) {
        repo = "maven"
        name = "hypixel-api"
        desc = "Kotlin Hypixel API wrapper"
        issueTrackerUrl = "https://github.com/mdashl/hypixel-api/issues"
        vcsUrl = "https://github.com/mdashl/hypixel-api.git"
        githubRepo = "https://github.com/mdashl/hypixel-api.git"
        githubReleaseNotesFile = "README.md"
        publicDownloadNumbers = false
        setLicenses("MIT")
        setLabels("Hypixel", "Kotlin")
    }
}
