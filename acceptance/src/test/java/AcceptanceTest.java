import io.cucumber.java.BeforeAll;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectDirectories;
import org.junit.platform.suite.api.Suite;

@Suite
@IncludeEngines("cucumber")
@SelectDirectories("src/main")
public class AcceptanceTest {
    @BeforeAll
    public static void banner() {
        var sutUrl = System.getProperty("app.sut");
        var banner = """
-------------------------------------
RUNNING THE ACCEPTANCE TESTS
-------------------------------------

SUT URL = %s

(C) Keksipurkki technologies 2024
-------------------------------------
                     """.formatted(sutUrl);

        System.out.println(banner);
    }
}
