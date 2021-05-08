package org.quiltmc.gradle.api;

import java.util.concurrent.CompletableFuture;

/**
 * Provides the version manifest used to resolve Minecraft.
 */
public interface VersionManifestProvider {
	/**
	 * Provides the contents of the version manifest to be parsed.
	 *
	 * @return a future containing the version manifest.
	 * If the version manifest could not be looked up, return a completed future with an exception.
	 */
	CompletableFuture<String> provide();
}
