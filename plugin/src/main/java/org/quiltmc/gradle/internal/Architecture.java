package org.quiltmc.gradle.internal;

import java.util.Locale;

import org.jetbrains.annotations.Nullable;

public final class Architecture {
	public static boolean is64Bit() {
		// TODO: O glorious hacks - this does not work for everything that is 64 bits.
		return System.getProperty("sun.arch.data.model").contains("64");
	}

	// TODO: This may need some slight adjustments?
	@Nullable
	public static String archName() {
		String arch = System.getProperty("os.arch").toLowerCase(Locale.ROOT);

		if (arch.equals("i386") || arch.equals("x86") || arch.equals("i686")) {
			return "x86";
		} else if (arch.startsWith("amd64") || arch.startsWith("x86_64")) {
			return "x86_64";
		} else if (arch.startsWith("arm64")) {
			return "arm64";
		} else if (arch.startsWith("arm")) {
			return "arm";
		} else if (arch.equals("ppc") || arch.equals("powerpc")) {
			return "ppc";
		} else if (arch.startsWith("ppc")) {
			return "ppc_64";
		} else if (arch.startsWith("sparc")) {
			return "sparc";
		} else if (arch.startsWith("mips64")) {
			return "mips64";
		} else if (arch.startsWith("mips")) {
			return "mips";
		} else if (arch.contains("risc")) {
			return "risc";
		}

		return null;
	}

	private Architecture() {
	}
}
