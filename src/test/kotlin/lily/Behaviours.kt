package lily

import cucumber.api.CucumberOptions
import cucumber.runtime.Runtime
import cucumber.runtime.RuntimeOptionsFactory
import cucumber.runtime.io.MultiLoader
import cucumber.runtime.io.ResourceLoaderClassFinder
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.DynamicTest.dynamicTest
import org.junit.jupiter.api.TestFactory
import java.util.stream.Stream

@CucumberOptions(
        format = arrayOf("pretty"),
        features = arrayOf("classpath:features")
)
class Behaviours {
    @TestFactory
    fun loadCucumberTests() : Stream<DynamicTest> {
        val options = RuntimeOptionsFactory(Behaviours::class.java).create()
        val classLoader = Behaviours::class.java.classLoader
        val resourceLoader = MultiLoader(classLoader)
        val classFinder = ResourceLoaderClassFinder(resourceLoader, classLoader)
        val runtime = Runtime(resourceLoader, classFinder, classLoader, options)
        val cucumberFeatures = options.cucumberFeatures(resourceLoader)
        return cucumberFeatures.map { feature ->
            dynamicTest(feature.gherkinFeature.name) {
                feature.run(options.formatter(classLoader), options.reporter(classLoader), runtime)
        } }.stream()
    }
}

