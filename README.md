# NetworkCalculus.org SNC

The Stochastic Network Calculator is a Java tool for the stochastic worst case performance analysis of networks.
It was derived from the [DiscoSNC](http://disco.informatik.uni-kl.de/index.php/projects/disco-snc).
More detailed descriptions can be found on the project's [previous webpage at DISCO](http://disco.informatik.uni-kl.de/index.php/projects/disco-snc) 
and in the technical report on [ArXiv](https://arxiv.org/abs/1707.07739).

Note that this software is still under development. 
We appreciate any contribution, feel free to fork the project, post bugs and of course post pull requests, too.


# Getting Started with Development

We provide the SNC as a Maven project.
All dependencies (JUNG Java Graph Framework and Apache Commons Math Library) and their transitive dependencies are resolved by Maven, i.e., they are pulled from the Maven Central Repository.

The SNC can be built with a Java 10 JDK. It was tested with Oracle's Java HotSpot(TM) 64-Bit Server VM.


# Running the SNC

The SNC comes with a graphical user interface.
Therefore, Maven is configured to build executable `jar` files of the project:
* `SNC-${version}-jar-with-dependencies.jar` that bundles all dependencies, and
* `SNC-${version}.jar` that assumes dependencies are in a `lib/` folder next to it.

The current `${version}` can be found in Maven's configuration file.

In order to execute the `jar`-file, locate it in your output folder, open a command line window and enter

```bash
java -jar SNC-${version}-jar-with-dependencies.jar
```
(Don't forget to replace `${version}` with the one you located in `pom.xml`).

### Academic Attribution

If you use the Stochastic Network Calculator for research, please include at least one of the following reference in any resulting publication:

```plain
@inproceedings{DiscoSNCv1,
  author    = {Michael A. Beck and Jens B. Schmitt},
  title     = {The {DISCO} Stochastic Network Calculator Version 1.0 -- When Waiting Comes to an End},
  booktitle = {Proc. of the International Conference on Performance Evaluation Methodologies and Tools},
  series    = {ValueTools '13},
  pages     = {282--285},
  month     = {December},
  year      = 2013,
  url       = {https://dl.acm.org/citation.cfm?id=2631878}
}
```

```plain
@article{SNCv2,
  author    = {Michael A. Beck and Sebastian A. Henningsen},
  title     = {Technical Report The Stochastic Network Calculator},
  journal   = {CoRR},
  volume    = {abs/1707.07739},
  year      = {2017},
  url       = {http://arxiv.org/abs/1707.07739},
}
```
