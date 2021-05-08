package org.quiltmc.gradle.api.task;

import java.util.List;

import org.gradle.api.provider.Property;
import org.gradle.api.tasks.Internal;
import org.gradle.api.tasks.JavaExec;
import org.jetbrains.annotations.ApiStatus;
import org.quiltmc.gradle.api.QuiltExtension;
import org.quiltmc.gradle.api.run.RunConfiguration;

// TODO: Modules?

/**
 * A task which runs the game.
 *
 * <p>Generally you should avoid creating these tasks yourself, instead you should
 * {@link QuiltExtension#getRuns() register a run configuration}.
 */
public abstract class RunGameTask extends JavaExec {
	@ApiStatus.Internal
	public RunGameTask() {
		this.setGroup("quilt");
	}

	/**
	 * The run configuration used to configure how the game is run.
	 *
	 * @return the run configuration
	 */
	// Internal since this task is derived from the run configuration.
	@Internal
	abstract Property<RunConfiguration> runConfiguration();

	@Override
	public final Property<String> getMainClass() {
		// FIXME: This evaluates the configuration?
		return this.runConfiguration()
				.get()
				.mainClass();
	}

	@Override
	public final List<String> getJvmArgs() {
		// TODO
		return super.getJvmArgs();
	}

	@Override
	public final void exec() {
		// Finalize the state of the run configuration
		this.finalizeRunConfiguration();

		// TODO: Test
		// Set launch arguments
		this.args(this.runConfiguration().get().programArgs().get());

		// TODO
		this.jvmArgs(this.runConfiguration().get().vmArgs().get());

		// Launch the jvm
		super.exec();
	}

	private void finalizeRunConfiguration() {
		this.runConfiguration().disallowChanges();
		RunConfiguration configuration = this.runConfiguration().get();

		configuration.vmArgs().disallowChanges();
		configuration.programArgs().disallowChanges();
	}
}
