import org.gradle.api.tasks.JavaExec
import org.gradle.jvm.tasks.Jar
import org.jetbrains.dokka.gradle.DokkaPlugin
import org.jetbrains.dokka.gradle.DokkaTask
import ratpack.gradle.RatpackPlugin

group = "lily"

val mainClass = "lily.console.Game"

buildscript {
    extra["junitPlatformVersion"] = "1.0.0-M3"
    extra["junitJupiterVersion"] = "5.0.0-M3"
    extra["junitVintageVersion"] = "4.12.0-M3"
    extra["cucumberJvmVersion"] = "1.2.5"
    extra["assertjVersion"] = "3.5.2"
    extra["dokkaVersion"] = "0.9.9"
    extra["ratpackVersion"] = "1.4.2"
    extra["rxVersion"] = "0.60.0"
    extra["guiceVersion"] = "4.1.0"

    repositories {
        mavenLocal()
        jcenter()
        gradleScriptKotlin()
    }

    dependencies {
        classpath(kotlinModule("gradle-plugin"))
        classpath("org.junit.platform:junit-platform-gradle-plugin:${extra["junitPlatformVersion"]}")
        classpath("org.jetbrains.dokka:dokka-gradle-plugin:${extra["dokkaVersion"]}")
        classpath("io.ratpack:ratpack-gradle:${extra["ratpackVersion"]}")
    }
}

apply {
    plugin("java")
    plugin("kotlin")
    plugin("idea")
    plugin<RatpackPlugin>()
    plugin("org.junit.platform.gradle.plugin")
    plugin<DokkaPlugin>()
    plugin<ApplicationPlugin>()
}

tasks.withType<DokkaTask> {
    moduleName = "dokka"
    outputFormat = "gfm"
    outputDirectory = "$projectDir/wiki"
}

configure<ApplicationPluginConvention> {
    mainClassName = mainClass
}

tasks.withType<JavaExec> {
    setStandardInput(System.`in`)
}

configure<JavaPluginConvention> {
    setSourceCompatibility(1.8)
    setTargetCompatibility(1.8)
}

tasks.withType<Jar> {
    from(the<JavaPluginConvention>().sourceSets.getByName("main").allSource)
    manifest.attributes.apply {
        put("Implementation-Title", name)
        put("Implementation-Version", "0")
        put("Main-Class", mainClass)
    }
}

repositories {
    mavenLocal()
    gradleScriptKotlin()
    jcenter()
    mavenCentral()
}

dependencies {
    compile(kotlinModule("stdlib"))
    compile("io.reactivex:rxkotlin:${extra["rxVersion"]}")
    compile("com.google.inject:guice:${extra["guiceVersion"]}")


    // Test dependencies

    testCompile(gradleTestKit())

    // JUnit Jupiter API
    testCompile("org.junit.jupiter:junit-jupiter-api:${extra["junitJupiterVersion"]}")
    testCompile("org.junit.platform:junit-platform-runner:${extra["junitPlatformVersion"]}")

    // TestEngine implementations (only needed at runtime)
    testRuntime("org.junit.jupiter:junit-jupiter-engine:${extra["junitJupiterVersion"]}")    //JUnit5
    //testRuntime("org.junit.vintage:junit-vintage-engine:${extra["junitVintageVersion"]}")    //JUnit4

    testCompile("info.cukes:cucumber-java:${extra["cucumberJvmVersion"]}")
    testCompile("info.cukes:cucumber-java8:${extra["cucumberJvmVersion"]}")
    testCompile("info.cukes:cucumber-junit:${extra["cucumberJvmVersion"]}")

    testCompile("org.assertj:assertj-core:${extra["assertjVersion"]}")
}
