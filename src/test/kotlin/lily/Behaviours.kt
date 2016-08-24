package lily

import cucumber.api.CucumberOptions
import cucumber.api.junit.Cucumber
import org.junit.runner.RunWith

//@ExtendWith(Cucumber::class)
@CucumberOptions(
        format = arrayOf("pretty"),
        features = arrayOf("classpath:features")
)
@RunWith(Cucumber::class)
class Cucumber {
}

