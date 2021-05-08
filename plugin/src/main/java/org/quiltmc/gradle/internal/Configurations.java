package org.quiltmc.gradle.internal;

import org.gradle.api.NamedDomainObjectProvider;
import org.gradle.api.Project;
import org.gradle.api.artifacts.Configuration;

/**
 * Internal class used to create configurations.
 */
final class Configurations {
	/**
	 * Setup configurations specifically related to resolving Minecraft.
	 */
	static NamedDomainObjectProvider<Configuration> setupMinecraftConfiguration(Project project, QuiltExtensionImpl extension) {
		return project.getConfigurations().register(Constants.Configurations.MINECRAFT, configuration -> {
			configuration.setVisible(false);
			configuration.setCanBeConsumed(false);
			configuration.setCanBeResolved(true);

			// Add Minecraft dependency.
			// This should be the only dependency
			configuration.defaultDependencies(set -> {
				extension.version().disallowChanges();

				String notation = String.format(
						"%s:%s:%s",
						Constants.Dependencies.MINECRAFT_GROUP,
						Constants.Dependencies.MINECRAFT_ARTIFACT,
						extension.version().get()
				);

				set.add(project.getDependencies().create(notation));
			});
		});
	}

	/**
	 * Setup configuration used to resolve mappings.
	 */
	static NamedDomainObjectProvider<Configuration> setupMappingsConfiguration(Project project) {
		return project.getConfigurations().register(Constants.Configurations.MAPPINGS, configuration -> {
			configuration.setVisible(true);
			configuration.setCanBeConsumed(false);
			configuration.setCanBeResolved(true);
		});
	}

	private Configurations() {
	}
}
