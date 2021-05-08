import java.util.stream.Stream;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

@Execution(ExecutionMode.CONCURRENT)
final class TestRunner {
	@TestFactory
	Stream<DynamicTest> runGroovyTests() {

	}

	@TestFactory
	Stream<DynamicTest> runKotlinTests() {

	}
}
