package org.quiltmc.gradle.internal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UncheckedIOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.CompletableFuture;

import org.quiltmc.gradle.api.VersionManifestProvider;

public final class V2ManifestProvider implements VersionManifestProvider {
	private static final String URL = "https://launchermeta.mojang.com/mc/game/version_manifest_v2.json";

	@Override
	public CompletableFuture<String> provide() {
		// TODO: Check cache for manifest?

		return CompletableFuture.supplyAsync(() -> {
			StringBuilder destination = new StringBuilder();

			try {
				// TODO: Cache the manifest
				URLConnection connection = new URL(URL).openConnection();
				String line;

				try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
					while ((line = reader.readLine()) != null) {
						destination.append(line);
					}
				}
			} catch (IOException e) {
				throw new UncheckedIOException(e);
			}

			return destination.toString();
		});
	}
}
