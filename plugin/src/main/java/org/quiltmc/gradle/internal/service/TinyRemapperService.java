package org.quiltmc.gradle.internal.service;

import org.gradle.api.Incubating;
import org.gradle.api.provider.Property;
import org.gradle.api.services.BuildService;
import org.gradle.api.services.BuildServiceParameters;
import org.gradle.api.tasks.Internal;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Nullable;

import net.fabricmc.tinyremapper.TinyRemapper;

/**
 * The remapper service provides access to the remapper used to remap class files.
 */
public abstract class TinyRemapperService implements BuildService<TinyRemapperService.Parameters>, AutoCloseable {
	@Nullable
	private volatile TinyRemapper remapper;

	@ApiStatus.Internal
	public TinyRemapperService() {
	}

	/**
	 * The remapper used to remap class files and Minecraft.
	 *
	 * @return the remapper instance
	 */
	public final TinyRemapper remapper() {
		@Nullable
		TinyRemapper remapper;

		// Double checked locking
		if ((remapper = this.remapper) == null) {
			synchronized (this) {
				if ((remapper = this.remapper) == null) {
					// Instantiate the remapper.
					// TODO:
					throw new UnsupportedOperationException("Implement me!");
				}

				return remapper;
			}
		}

		return remapper;
	}

	@Override
	public void close() throws Exception {
		@Nullable
		TinyRemapper remapper;

		if ((remapper = this.remapper) != null) {
			remapper.finish();
			this.remapper = null;
		}
	}

	/**
	 * Options used to initialize the remapper service.
	 */
	// Note: Gradle will fail to generate the parameters IF we do not have get prefixes
	public interface Parameters extends BuildServiceParameters {
		/**
		 * @return the name of the source namespace to remap from.
		 */
		Property<String> getSourceNamespace();

		/**
		 * @return the name of the target namespace to remap to.
		 */
		Property<String> getTargetNamespace();

		/**
		 * Checks whether the remapper should try to resolve conflicts.
		 *
		 * <p>This may be useful with custom mappings where there may be small conflicts.
		 * If this is set to true, the remapper will attempt to resolve the conlicts.
		 *
		 * @return whether the remapper should try to resolve conflicts.
		 */
		// This is wrongly named in TR as ignoreConflicts as it also tries to resolve any conflicts.
		@Incubating
		Property<Boolean> getTryResolveConflicts();

		// We may consider more options in the future.
	}
}
