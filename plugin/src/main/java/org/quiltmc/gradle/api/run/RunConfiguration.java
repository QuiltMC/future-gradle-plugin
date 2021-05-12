package org.quiltmc.gradle.api.run;

import java.util.Objects;

import org.gradle.api.Named;
import org.gradle.api.model.ObjectFactory;
import org.gradle.api.provider.ListProperty;
import org.gradle.api.provider.Property;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Nullable;
import org.quiltmc.gradle.api.QuiltExtension;
import org.quiltmc.gradle.api.task.RunGameTask;

// TODO: Do we want to consider allowing the user to specify a JavaLauncher?

/**
 * Represents a run configuration used to generate IDE run configurations and {@link RunGameTask the task for running
 * the game}.
 *
 * <p>The run configuration allows you to define how a the game is run in your buildscript, including the vm arguments,
 * program arguments and the main class to use.
 *
 * @see QuiltExtension
 */
public final class RunConfiguration implements Named {
	private final String name;
	private final Property<String> mainClass;
	private final ListProperty<JvmArgument> vmArgs;
	private final ListProperty<String> programArgs;

	@ApiStatus.Internal
	public RunConfiguration(ObjectFactory factory, String name) {
		this.name = name;
		this.mainClass = factory.property(String.class);
		this.vmArgs = factory.listProperty(JvmArgument.class);
		this.programArgs = factory.listProperty(String.class);
	}

	@Override
	public String getName() {
		return this.name;
	}

	/**
	 * @return the main class to use as the program entrypoint for this run configuration
	 */
	public Property<String> mainClass() {
		return this.mainClass;
	}

	/**
	 * Sets the main class to use as the program entrypoint for this run configuration.
	 *
	 * @param mainClass the main class to use
	 */
	public void setMainClass(String mainClass) {
		Objects.requireNonNull(mainClass, "Main class cannot be null");
		this.mainClass.set(mainClass);
	}

	/**
	 * Adds a property to be used when running the game.
	 * This can be looked up using {@link System#getProperty(String)} in game.
	 *
	 * <p>Only the name of the property should be used, the {@code -D} prefix used to denote properties to the java
	 * virtual machine is added automatically.
	 *
	 * @param key the key of the property
	 */
	public void property(String key) {
		this.vmArg("-D" + key);
	}

	/**
	 * Adds a property to be used when running the game.
	 * This can be looked up using {@link System#getProperty(String)} in game.
	 *
	 * <p>Only the name of the property should be used, the {@code -D} prefix used to denote properties to the java
	 * virtual machine is added automatically.
	 *
	 * @param key the key of the property
	 * @param value the value of the property
	 */
	public void property(String key, @Nullable String value) {
		this.vmArg("-D" + key, value);
	}

	/**
	 * Adds a VM argument used to start the JVM.
	 * The JVM will not start given an unsupported argument.
	 *
	 * @param key the key of the argument
	 */
	public void vmArg(String key) {
		this.vmArg(key, null);
	}

	/**
	 * Adds a VM argument used to start the JVM.
	 * The JVM will not start given an unsupported argument.
	 *
	 * @param key the key of the argument
	 * @param value the value of the argument
	 */
	public void vmArg(String key, @Nullable String value) {
		this.vmArgs.add(new JvmArgument(key, value));
	}

	/**
	 * @return a list of VM arguments used to start the JVM.
	 */
	public ListProperty<JvmArgument> vmArgs() {
		return this.vmArgs;
	}

	/**
	 * Adds a program argument to be passed to the application being launched.
	 * The arguments are passed in order of first to last registered.
	 *
	 * @param argument the argument to pass
	 */
	public void programArg(String argument) {
		this.programArgs.add(argument);
	}

	/**
	 * Adds multiple a program argument to be passed to the application being launched.
	 * The arguments are passed in order of first to last registered.
	 *
	 * @param arguments the arguments to pass
	 */
	public void programArgs(String... arguments) {
		this.programArgs.addAll(arguments);
	}

	/**
	 * Adds multiple a program argument to be passed to the application being launched.
	 * The arguments are passed in order of first to last registered.
	 *
	 * @param arguments the arguments to pass
	 */
	public void programArgs(Iterable<String> arguments) {
		this.programArgs.addAll(arguments);
	}

	/**
	 * @return a list of all program arguments
	 */
	public ListProperty<String> programArgs() {
		return this.programArgs;
	}

	/**
	 * Represents a VM argument passed to the Java virtual machine.
	 * A VM argument always has a key and may optionally have a value.
	 */
	public static final class JvmArgument {
		private final String key;
		@Nullable
		private final String value;

		JvmArgument(String key, @Nullable String value) {
			this.key = key;
			this.value = value;
		}

		public String key() {
			return this.key;
		}

		@Nullable
		public String value() {
			return this.value;
		}
	}
}
