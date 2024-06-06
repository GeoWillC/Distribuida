plugins {
    id("java")
}

group = "org.example"
version = "unspecified"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jboss.weld.se:weld-se-core:5.1.2.Final")
    implementation("org.hibernate:hibernate-core:6.5.2.Final")
    implementation("com.h2database:h2:2.2.224")

    implementation("com.sparkjava:spark-core:2.9.4")
    implementation("com.google.code.gson:gson:2.11.0")

    implementation ("org.slf4j:slf4j-api:1.7.30")
    implementation ("ch.qos.logback:logback-classic:1.2.10") // Logback as the SLF4J implementation


}


sourceSets {
    main {
        output.setResourcesDir(file("${buildDir}/classes/java/main"))
    }
}

tasks.jar {
    manifest {
        attributes(
            mapOf("Main-Class" to "com.distribuida.Main",
                "Class-Path" to configurations.runtimeClasspath
                    .get()
                    .joinToString(separator = " ") { file ->
                        "${file.name}"
                    })
        )
    }
}
tasks.test {
    useJUnitPlatform()
}