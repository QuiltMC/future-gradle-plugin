package org.quiltmc.gradle.internal;

import java.util.Objects;

import javax.inject.Inject;

import groovy.lang.Closure;
import groovy.lang.DelegatesTo;
import org.gradle.api.Action;
import org.gradle.api.Project;
import org.gradle.api.invocation.Gradle;
import org.gradle.api.model.ObjectFactory;
import org.gradle.api.provider.Property;
import org.gradle.util.ConfigureUtil;
import org.jetbrains.annotations.ApiStatus;
import org.quiltmc.gradle.api.VersionManifestProvider;
import org.quiltmc.gradle.api.QuiltExtension;
import org.quiltmc.gradle.api.run.RunConfiguration;
import org.quiltmc.gradle.api.run.RunConfigurationContainer;

@ApiStatus.Internal
public class QuiltExtensionImpl implements QuiltExtension {
	private final Gradle gradle;
	private final ObjectFactory factory;
	private final Project project;

	// User configurables.
	private final Property<String> minecraftVersion;
	private final Property<VersionManifestProvider> manifestProvider;
	private final RunConfigurationContainer runConfigurations;

	@Inject
	public QuiltExtensionImpl(Gradle gradle, ObjectFactory factory, Project project) {
		this.gradle = gradle;
		this.factory = factory;
		this.project = project;

		this.minecraftVersion = factory.property(String.class);
		this.manifestProvider = factory.property(VersionManifestProvider.class).convention(new V2ManifestProvider());
		this.runConfigurations = new RunConfigurationContainer(factory.domainObjectContainer(RunConfiguration.class));
	}

	@Override
	public Property<String> version() {
		return this.minecraftVersion;
	}

	@Override
	public void version(String version) {
		this.minecraftVersion.set(version);
	}

	@Override
	public Property<VersionManifestProvider> versionManifest() {
		return this.manifestProvider;
	}

	@Override
	public void mojangMappings() {
		// this.project.getDependencies().add(Constants.Configurations.MAPPINGS, )
	}

	@Override
	public void yarnMappings(int build) {
		// TODO: Add configuration for this
	}

	@Override
	public RunConfigurationContainer getRuns() {
		return this.runConfigurations;
	}

	@Override
	@SuppressWarnings("rawtypes")
	public void runs(@DelegatesTo(value = RunConfigurationContainer.class, strategy = Closure.DELEGATE_FIRST) Closure closure) {
		ConfigureUtil.configure(closure, this.getRuns());
	}

	@Override
	public void runs(Action<RunConfigurationContainer> action) {
		Objects.requireNonNull(action, "Action cannot be null").execute(this.getRuns());
	}

	@Override
	public void defaultRuns() {
		RunConfigurationContainer runs = this.getRuns();
		runs.client();
		runs.server();
	}
}
