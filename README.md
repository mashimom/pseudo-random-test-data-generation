# Pseudo Random Test Data Generation
## A sample project for the article

> :information_source: This project is explained in the article: [When you need to generate data for tests](https://blog.shimomoto.org/)

This project was create using Gradle's init command:
``` shell
gradle init --type java-application --test-framework spock
```

Minor changes to:
- version of [Spock](https://spockframework.org/spock/docs/2.3/all_in_one.html) being used bumped to latest.
- plugins added `idea`, `build-dashboard`, `project-report` and ben manes' [Versions](https://github.com/ben-manes/gradle-versions-plugin)
- added [Lombok](https://projectlombok.org/)

Then using the always :sparkling_heart: [SDKMan](https://sdkman.io/) added recommended jdk, groovy, gradle versions. On how to take advantage of that see https://sdkman.io/usage#env.

It contains a single _production_ class [Book](java/org/shimomoto/blog/pttdg/model/Book.java).  
It is all about testing, Book is tested on [BookSpec](groovy/org/shimomoto/blog/pttdg/BookSpec.groovy); there is a utility data generator at [Generator](groovy/org/shimomoto/blog/pttdg/generator/Generator.groovy) and tests to verify it behaves appropriately [GeneratorSpec](groovy/org/shimomoto/blog/pttdg/generator/GeneratorSpec.groovy)

Special thanks to early reviewers!
