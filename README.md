# Name will be decided later

<!--TODO: Remove the experimental stuff when we migrate over officially-->

An experimental [Gradle] plugin to set up an environment suitable for developing Minecraft mods.
This is the future replacement for Loom.

See the [TODO] file for what needs to be done.

## Features

[ ] Supports multiple types of mappings, including [Yarn] and Mojang's mappings.
[ ] Supports decompiling Minecraft with source documentation from mappings.
[ ] Support for several IDEs, including generating run configurations to launch the game within an IDE:
    [ ] [IntelliJ IDEA]
    [ ] [Eclipse]
    [ ] [Visual Studio Code]*
[ ] Supports Java 8 through 16.
[ ] Supports the latest version of Gradle.

\* With Java language support installed.

## Building

You can build the gradle plugin by running `./gradlew build`.
To test there is a better solution detailed in the testing section below.

## Testing

We have a section for testing in the [contribution guidelines].

<!--Links-->
[TODO]: TODO.md

[Gradle]: https://gradle.org
[Yarn]: https://github.com/QuiltMC/yarn

[IntelliJ IDEA]: doc/IDEA.md
[Eclipse]: doc/ECLIPSE.md
[Visual Studio Code]: doc/VSCODE.md

[contribution guidelines]: ./CONTRIBUTING.md
