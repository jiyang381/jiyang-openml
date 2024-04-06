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

  <!-- Check proj