package lily

/**
 * // TODO class Javadoc
 *
 * @author tenwit
 */
import cucumber.api.CucumberOptions
import cucumber.api.junit.Cucumber
import org.junit.runner.RunWith

[RunWith(value = javaClass<Cucumber>())]
[CucumberOptions(
format = array("pretty"),
features = array("classpath:features")
)]
class CucumberTest {
}
