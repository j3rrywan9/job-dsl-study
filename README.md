# Learning Jenkins Job DSL

## Inheritance Hierarchy
```groovy
class RestApiJobManagement extends MockJobManagement
```

Related classes/interfaces defined in Job DSL:
```groovy
package javaposse.jobdsl.dsl

class MockJobManagement extends AbstractJobManagement

class AbstractJobManagement implements JobManagement

interface JobManagement
```

Methods that **RestApiJobManagement** needs to implement:
```groovy
String getConfig(String jobName)

boolean createOrUpdateConfig(Item item, boolean ignoreExisting)

boolean createOrUpdateView(String viewName, String config, boolean ignoreExisting)

InputStream streamFileInWorkspace(String filePath)

String readFileInWorkspace(String filePath)
```

## References
[API Viewer](https://jenkinsci.github.io/job-dsl-plugin/)

[Jenkins Job DSL Gradle Example](https://github.com/sheehan/job-dsl-gradle-example)
