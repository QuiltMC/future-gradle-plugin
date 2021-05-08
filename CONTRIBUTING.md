# Contributions

The project is still experimental at the moment, contributions are welcome but please discuss on Discord or in an issue
before going forward.

## Testing

Testing the plugin can be done in two ways:

* Test projects in the `test-projects` directory.
* Included build to test in another mod.

### Test projects

The test projects are run on GitHub Actions to test if the plugin is working properly.
These tests are geared towards making sure there are no regressions in the plugin.
Tests for verifying reproducible builds work properly are one example of a test project.

### Testing in an existing project.

Testing in an existing project is possible via a [composite build].
In your settings buildscript (`settings.gradle` or `settings.gradle.kts`), add the plugin as an included build assuming
the plugin's repository is in the same folder as the project's folder:

<!--TODO: Change at rename-->
```gradle
includeBuild(file("../plugin"))
```

You may also need to remove the `version` infix method in your `plugins` closure temporarily.
Then you can modify the plugin as necessary and refresh Gradle to test your changes.

<!--Links-->
[composite build]: https://docs.gradle.org/current/userguide/composite_builds.html
