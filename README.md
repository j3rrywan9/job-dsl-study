# Learning Jenkins Job DSL

## Workflow
* Developer updates DSL scripts locally
* Developer pushes changes
* SCM change triggers seed job
* Seed job runs DSL
* Seed job updates/creates/deletes

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

## Call Stack
```
me.jerrywang.rest.RestApiJobManagement.create

me.jerrywang.rest.RestApiJobManagement.createOrUpdateConfig

javaposse.jobdsl.dsl.JobManagement$createOrUpdateConfig

javaposse.jobdsl.dsl.DslScriptLoader.extractGeneratedJobs

javaposse.jobdsl.dsl.DslScriptLoader.runScriptsWithClassLoader

javaposse.jobdsl.dsl.DslScriptLoader.runScripts

me.jerrywang.rest.RestApiScriptRunner.run

me.jerrywang.rest.RestApiScriptRunner.main
```

## References
[API Viewer](https://jenkinsci.github.io/job-dsl-plugin/)

[Jenkins Job DSL Gradle Example](https://github.com/sheehan/job-dsl-gradle-example)
