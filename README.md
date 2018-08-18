# The Disco Stochastic Network Calculator (DiscoSNC)

The Disco Stochastic Network Calculator is a Java tool for the stochastic worst case performance analysis of networks, see [its webpage](http://disco.informatik.uni-kl.de/index.php/projects/disco-snc) for a detailed description and previous versions.
The latest release is version 2.0. It can be [downloaded on github](https://github.com/NetCal/DiscoSNC/releases/tag/2.0)

Note that this software is still under development. 
We appreciate any contribution, feel free to fork the project, post bugs and of course post pull requests, too.


# Getting Started with Development

We provide the DiscoSNC as a Maven project.
All dependencies (Jung Java Graph Framework and Apache Commons Math Library) and their dependencies are resolved by Maven and pulled from the Maven Central Repository.

The DiscoSNC can be built with a Java 10 JDK.


# Running the DiscoSNC (TODO: Currently Broken, see Issue #3)

The DiscoSNC comes with a graphical user interface.
You can build an executable `DiscoSNC-${version}.jar` with Maven.
The current `${version}` can be found in Maven's configuration file.

In order to execute the jar-file, locate it in your output folder, open a command line window and enter

```bash
java -jar DiscoSNC-${version}.jar
```
(Don't forget to replace `${version}` with the one you located in pom.xml).