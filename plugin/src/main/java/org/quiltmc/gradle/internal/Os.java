package org.quiltmc.gradle.internal;

import java.util.Locale;

import org.jetbrains.annotations.Nullable;

public enum Os {
	WINDOWS {
		@Override
		public String nativesName() {
			return "windows";
		}
	},
	MACOS {
		@Override
		public String nativesName() {
			return "osx";
		}
	},
	LINUX {
		@Override
		public String nativesName() {
			return "linux";
		}
	}, // TODO:
	/**
	 * An OS not supported by this plugin.
	 */
	UNSUPPORTED {
		@Override
		public String nativesName() {
			return null;
		}
	};

	private static Os current;

	public static Os current() {
		if (current == null) {
			String s = System.getProperty("os.name").toLowerCase(Locale.ROOT);

			if (s.contains("win")) {
				current = Os.WINDOWS;
			} else if (s.contains("mac")) {
				current = Os.MACOS;
			} else if (s.contains("linux")) {
				current = Os.LINUX;
			} else {
				current = Os.UNSUPPORTED;
			}
		}

		return current;
	}

	/**
	 * @return Gets the name of the artifact used for getting the OS specific LWJGL natives.
	 * Null if the OS cannot determine the name of the natives.
	 */
	@Nullable
	public abstract String nativesName();
}
