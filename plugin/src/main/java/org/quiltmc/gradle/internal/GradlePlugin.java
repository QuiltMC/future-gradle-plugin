package org.quiltmc.gradle.internal;

import java.util.Collections;

import org.gradle.api.NamedDomainObjectProvider;
import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.artifacts.Configuration;
import org.gradle.api.provider.Provider;
import org.quiltmc.gradle.api.QuiltExtension;
import org.quiltmc.gradle.internal.Configurations;
import org.quiltmc.gradle.internal.service.TinyRemapperService;

/**
 * Main entrypoint for the plugin.
 */
// This is can be package private.
final class GradlePlugin implements Plugin<Project> {
	@Override
	public void apply(Project project) {
		// TODO: In future we may need to also be a settings plugin?
		//  Who knows
		applyToProject(project);
	}

	private static void applyToProject(Project project) {
		// Preparatory work
		applyRequiredPlugins(project);
		QuiltExtensionImpl extension = createExtension(project);

		// Add Minecraft repository.
		Constants.Repositories.applyRepositories(project.getRepositories());

		// Create configurations.
		NamedDomainObjectProvider<Configuration> minecraftConfiguration = Configurations.setupMinecraftConfiguration(project, extension);
		NamedDomainObjectProvider<Configuration> mappingsConfiguration = Configurations.setupMappingsConfiguration(project);

		// Setup build services.
		Provider<TinyRemapperService> tinyRemapper = project.getGradle().getSharedServices().registerIfAbsent("tinyRemapper", TinyRemapperService.class, params -> {
		});
	}

	/**
	 * Setup required plugins.
	 */
	private static void applyRequiredPlugins(Project project) {
		// I mean this is required?
		project.apply(Collections.singletonMap("plugin", "java"));

		// IDE plugins
		project.apply(Collections.singletonMap("plugin", "eclipse"));
		project.apply(Collections.singletonMap("plugin", "idea"));
	}

	/**
	 * Setup extensions.
	 */
	private static QuiltExtensionImpl createExtension(Project project) {
		return (QuiltExtensionImpl) project.getExtensions().create(QuiltExtension.class, "quiltPlugin", QuiltExtensionImpl.class, project);
	}
}
