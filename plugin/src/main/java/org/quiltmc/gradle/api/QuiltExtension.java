package org.quiltmc.gradle.api;

import groovy.lang.Closure;
import groovy.lang.DelegatesTo;
import org.gradle.api.Action;
import org.gradle.api.provider.Property;
import org.quiltmc.gradle.api.run.RunConfigurationContainer;

public interface QuiltExtension {
	/**
	 * Gets the Minecraft version to resolve.
	 * This value is empty by default.
	 *
	 * @return the version
	 * @see #version(String)
	 */
	Property<String> version();

	/**
	 * Sets the version of Minecraft to resolve.
	 * The value will not be validated until Minecraft is resolved.
	 *
	 * @param version the version
	 */
	void version(String version);

	/**
	 * Get a property pointing to the version manifest provider used to look up the Minecraft dependency.
	 * This points to <a href="https://launchermeta.mojang.com/mc/game/version_manifest_v2.json">Version 2 of
	 * Minecraft's Version manifest</a> which is resolved when needed.
	 *
	 * <p>Very rarely you will need to change this value except for special spin-off versions such as the Minecraft
	 * combat test snapshots.
	 * A custom version manifest with an improper structure will error when resolved.
	 *
	 * @return the version manifest url
	 */
	Property<VersionManifestProvider> versionManifest();

	//
	// Mappings
	//

	/**
	 * Sets the mappings to use to Mojang's official deobfuscation mappings.
	 *
	 * <p>The earliest this is available is 1.14.4, all Minecraft versions after 19w36a (1.15 snapshots) and all combat
	 * tests except for combat test 3.
	 *
	 * <p>Mojang's obfuscation mappings are licensed under the Minecraft EULA, effectively allowing for their use in a
	 * development environment.
	 * Read the license to fully understand the conditions the obfuscation mappings may be used under.
	 *
	 * <p>At the moment there is no third-party solution for parameter names or documentation as the proguard mappings
	 * do not provide that information.
	 * This method will be changed to include the parameter names and documentation when a third-party solution exists.
	 *
	 * <p>If no Mojang mappings exist for the version, resolving mappings will fail.
	 */
	// TODO: When we adopt the param name and documentation stuff for mojmap, add a `plain mojmap` option as well.
	void mojangMappings();

	/**
	 * Sets the mappings to use to Yarn mappings.
	 *
	 * <p>Yarn mappings are a set of open mappings maintained by the Quilt.
	 * Yarn includes unpick definitions which are used to un-inline constant values inside of methods and fields.
	 *
	 * <p>If yarn does not exist for the specified version or the specified build does not exist, resolving mappings
	 * will fail.
	 *
	 * @param build the build number of yarn to use.
	 */
	void yarnMappings(int build);

	// TODO: Do we want to support other mappings natively?
	//  Considerations are:
	//   Intermediary
	//   Obfuscated
	//   MCP
	//   SRG

	//
	// Run configuration
	//

	/**
	 * Gets the run configurations configured for this project.
	 *
	 * @return the run configuration
	 */
	RunConfigurationContainer getRuns();

	/**
	 * Applies the provided closure on available run configurations.
	 *
	 * @param closure the closure to run on configuration container
	 */
	@SuppressWarnings("rawtypes")
	void runs(@DelegatesTo(value = RunConfigurationContainer.class, strategy = Closure.DELEGATE_FIRST) Closure closure);

	/**
	 * Applies the provided action on available run configurations.
	 *
	 * @param action the action to run on configuration container
	 */
	void runs(Action<RunConfigurationContainer> action);

	/**
	 * Creates the default run configurations.
	 *
	 * <p>This generates the {@code Run client} and {@code Run dedicated server} run configurations using the default
	 * options.
	 * Generally most mod developers should use this method, using the other methods to customize the run configurations
	 * as desired.
	 *
	 * <p>For the dedicated server, the {@code --no-gui} program argument is set by default.
	 */
	void defaultRuns();
}
