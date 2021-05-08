package org.quiltmc.gradle.internal;

import org.gradle.api.artifacts.dsl.RepositoryHandler;

public final class Constants {
	/**
	 * Auxiliary maven repositories used to configure Minecraft.
	 */
	public static final class Repositories {
		/**
		 * The Minecraft repository.
		 */
		public static final String MINECRAFT = "https://libraries.minecraft.net";
		public static final String MINECRAFT_NAME = "minecraft";

		public static void applyRepositories(RepositoryHandler handler) {
			handler.maven(repo -> {
				repo.setUrl(Constants.Repositories.MINECRAFT);
				repo.setName(Constants.Repositories.MINECRAFT_NAME);
			});
		}

		private Repositories() {
		}
	}

	/**
	 * Names of configurations used to download Minecraft and other dependencies.
	 */
	public static final class Configurations {
		/**
		 * Configuration used to resolve Minecraft itself.
		 */
		public static final String MINECRAFT = "minecraft";

		/**
		 * Configuration used to resolve Minecraft's native dependencies such as LWJGL natives.
		 */
		public static final String MINECRAFT_NATIVES = "minecraftNatives";

		/**
		 * Configuration used to resolve mappings to deobfuscate Minecraft.
		 */
		public static final String MAPPINGS = "mappings";

		private Configurations() {
		}
	}

	/**
	 * Dependencies used in the project.
	 */
	public static final class Dependencies {
		/**
		 * The maven group Minecraft belongs to.
		 */
		public static final String MINECRAFT_GROUP = "com.mojang";
		/**
		 * Artifact name of Minecraft.
		 */
		// This could change if we want to provide a server only distribution for example in the future.
		public static final String MINECRAFT_ARTIFACT = "minecraft";

		private Dependencies() {
		}
	}

	private Constants() {
	}
}
