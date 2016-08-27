//import org.gradle.api.plugins.*
//import org.gradle.script.lang.kotlin.*

buildscript {
    extra["springBootVersion"] = "1.4.0.RC1"
    extra["junitPlatformVersion"] = "1.0.0-M2"
    extra["junitJupiterVersion"] = "5.0.0-M2"
    extra["junitVintageVersion"] = "4.12.0-M2"
    extra["cucumberJvmVersion"] = "1.2.4"
    extra["assertjVersion"] = "3.5.2"

    repositories {
        gradleScriptKotlin()
        maven { setUrl("https://repo.spring.io/milestone") }
    }

    dependencies {
        classpath(kotlinModule("gradle-plugin"))
        classpath("org.springframework.boot:spring-boot-gradle-plugin:${extra["springBootVersion"]}")
        classpath("org.junit.platform:junit-platform-gradle-plugin:${extra["junitPlatformVersion"]}")
    }
}

apply {
    plugin("java")
    plugin("kotlin")
    plugin("idea")
    plugin("eclipse")
    plugin("spring-boot")
    plugin<ApplicationPlugin>()
}

configure<ApplicationPluginConvention> {
    mainClassName = "lily.Game"
}

configure<JavaPluginConvention> {
    setSourceCompatibility(1.8)
    setTargetCompatibility(1.8)
}

repositories {
    gradleScriptKotlin()
    jcenter()
    mavenCentral()
    maven { setUrl("https://repo.spring.io/milestone") }
}

dependencies {
    compile("org.springframework.boot:spring-boot-starter")
    compile(kotlinModule("stdlib"))
    testCompile("org.springframework.boot:spring-boot-starter-test")

    // JUnit Jupiter API
    testCompile("org.junit.jupiter:junit-jupiter-api:${extra["junitJupiterVersion"]}")
    testCompile("org.junit.platform:junit-platform-runner:${extra["junitPlatformVersion"]}")

    // Spring extension for JUnit5
    //testCompile(fileTree(mapOf("dir" to "libs", "include" to "*.jar"))

    // TestEngine implementations (only needed at runtime)
    testRuntime("org.junit.jupiter:junit-jupiter-engine:${extra["junitJupiterVersion"]}")    //JUnit5
    testRuntime("org.junit.vintage:junit-vintage-engine:${extra["junitVintageVersion"]}")    //JUnit4

    testCompile("info.cukes:cucumber-java:${extra["cucumberJvmVersion"]}")
    testCompile("info.cukes:cucumber-java8:${extra["cucumberJvmVersion"]}")
    testCompile("info.cukes:cucumber-junit:${extra["cucumberJvmVersion"]}")

    testCompile("org.assertj:assertj-core:${extra["assertjVersion"]}")
}
