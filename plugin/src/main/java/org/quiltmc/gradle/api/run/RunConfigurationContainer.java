package org.quiltmc.gradle.api.run;

import javax.inject.Inject;

import org.gradle.api.Action;
import org.gradle.api.NamedDomainObjectContainer;
import org.gradle.api.NamedDomainObjectProvider;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Nullable;
import org.quiltmc.gradle.internal.run.RunConfigurationTemplates;

public final class RunConfigurationContainer extends AbstractNamedDomainObjectContainer<RunConfiguration> {
	@Inject
	@ApiStatus.Internal
	public RunConfigurationContainer(NamedDomainObjectContainer<RunConfiguration> delegate) {
		super(delegate);
	}

	/**
	 * Configures a run configuration, using the default client run configuration options.
	 */
	public NamedDomainObjectProvider<RunConfiguration> client() {
		return this.client(null);
	}

	/**
	 * Configures a run configuration, using the default client run configuration options.
	 */
	public NamedDomainObjectProvider<RunConfiguration> client(@Nullable Action<RunConfiguration> action) {
		return this.client("client", action);
	}

	/**
	 * Configures a run configuration, using the default client run configuration options.
	 */
	public NamedDomainObjectProvider<RunConfiguration> client(String name, @Nullable Action<RunConfiguration> action) {
		return this.configureOrCreate(name, RunConfigurationTemplates::client, action);
	}

	/**
	 * Configures a run configuration, using the default server run configuration options.
	 */
	public NamedDomainObjectProvider<RunConfiguration> server() {
		return this.server(null);
	}

	/**
	 * Configures a run configuration, using the default server run configuration options.
	 */
	public NamedDomainObjectProvider<RunConfiguration> server(@Nullable Action<RunConfiguration> action) {
		return this.server("server", action);
	}

	/**
	 * Configures a run configuration, using the default server run configuration options.
	 */
	public NamedDomainObjectProvider<RunConfiguration> server(String name, @Nullable Action<RunConfiguration> action) {
		return this.configureOrCreate(name, RunConfigurationTemplates::server, action);
	}

	// TODO: Do we want to generate legacylauncher run configurations given user request? We use Knot by default.

	/**
	 * Tries to configure an existing run configuration with the specified action, or creates a new run configuration
	 * given no existing run configuration using the supplied template.
	 *
	 * @param name name of the run configuration
	 * @param template the template action used to configure the run configuration
	 * @param action the action to configure the run configuration with
	 * @return the run configuration
	 */
	private NamedDomainObjectProvider<RunConfiguration> configureOrCreate(String name, Action<RunConfiguration> template, @Nullable Action<RunConfiguration> action) {
		// Do we have an existing config?
		NamedDomainObjectProvider<RunConfiguration> configuration;

		if (this.getNames().contains(name)) {
			// No, get the existing configuration and apply the action.
			configuration = this.named(name);
		} else {
			// Yes, register with the template and apply the action.
			configuration = this.register(name, template);
		}

		if (action != null) {
			configuration.configure(action);
		}

		return configuration;
	}
}
