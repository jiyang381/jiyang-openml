# Jiyang OpenML API

[![Build Status](https://travis-ci.com/jiyang381/jiyang-openml.svg?branch=master)](https://travis-ci.com/jiyang381/jiyang-openml)

[![codecov](https://codecov.io/gh/jiyang381/jiyang-openml/branch/master/graph/badge.svg)](https://codecov.io/gh/jiyang381/jiyang-openml)

[![Codacy Badge](https://api.codacy.com/project/badge/Grade/052dc81a4434474da9a4f048c40a52eb?branch=master)](https://www.codacy.com/app/jiyang381/jiyang-openml?utm_source=github.com&amp;amp;utm_medium=referral&amp;amp;utm_content=jiyang381/jiyang-openml&amp;amp;utm_campaign=Badge_Grade)

An extension of Feedzai's Machine Learning API to integrate ML platforms with the data science and runtime environment.

## Usage

Check out the [openml-example project](https://github.com/jiyang381/jiyang-openml/tree/master/openml-example) for a trivial example of implementing a new provider.

While building your OpenML Provider with Maven, you can add dependencies on the artifacts in this repository. Learn more in the following sections.

### OpenML API

[![Maven metadata URI](https://img.shields.io/maven-metadata/v/http/central.maven.org/maven2/com/jiyang/openml-api/maven-metadata.xml.svg)](https://mvnrepository.com/artifact/com.jiyang/openml-api)

The [OpenML API module](https://github.com/jiyang381/jiyang-openml/tree/master/openml-api) contains the primary concepts facilitating the interaction between the platform and any external ML platform.

```xml

<dependency>

  <groupId>com.jiyang</groupId>

  <artifactId>openml-api</artifactId>

  <!-- Check project tags for the latest version -->

  <version>1.2.0</version>

</dependency>

```

### OpenML Utils

[![Maven metadata URI](https://img.shields.io/maven-metadata/v/http/central.maven.org/maven2/com/jiyang/openml-utils/maven-metadata.xml.svg)](https://mvnrepository.com/artifact/com.jiyang/openml-utils)

The [openml-utils](https://github.com/jiyang381/jiyang-openml/tree/master/openml-example) module can help you manipulate some of the core concepts.

```xml

<dependency>

  <groupId>com.jiyang</groupId>

  <artifactId>openml-utils</artifactId>

  <!-- Check project tags for the latest version -->

  <version>1.2.0</version>

</dependency>

```

## Building

Build this Maven project with the following command:

```bash

mvn clean install

```

## Developing

Ensure your provider is identified according to the specification of [Java's Service Loader](https://docs.oracle.com/javase/9/docs/api/java/util/ServiceLoader.html). This means generating a Jar with the code (potentially with all necessary dependencies included in it or a set of Jars), and making sure to include a file `resources/META-INF/services/com.jiyang.openml.MachineLearningProvider` to indicate the providers in your code. Explore [our example in this repository](https://github.com/jiyang381/jiyang-openml/blob/master/openml-example/src/main/resources/META-INF/services/com.jiyang.mlapi.provider.MachineLearningProvider). [Google's Auto Service](https://github.com/google/auto/tree/master/service) might assist in setting this up for you.

### Maven Archetype

To ease the creation of new OpenML Providers, a Maven archetype was created. To get started, run:

```bash

mvn archetype:generate -DarchetypeGroupId=com.jiyang -DarchetypeArtifactId=openml-provider-archetype -DarchetypeVersion=1.2.0

```

After providing all necessary details (your new provider groupId, artifactId, and version), a provider template with some advice will be accessible on your workspace.

### IDE Compatibility

This project uses the [jgitver Maven plugin](https://github.com/jgitver/jgitver). If you are using IntelliJ IDEA, you must configure the project to skip the plugin. [See the related issue](https://github.com/jgitver/jgitver-maven-plugin/wiki/Intellij-IDEA-configuration).