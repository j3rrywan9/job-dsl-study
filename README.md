# Learning Jenkins Job DSL

## Workflow
* Developer updates DSL scripts locally
* Developer pushes changes
* SCM change triggers seed job
* Seed job runs DSL
* Seed job updates/creates/deletes

## Seed Job
You can create the example seed job via the REST API Runner (see below) using the pattern jobs/seed.groovy.
```
./gradlew rest -Dpattern="jobs/seed.groovy" -DbaseUrl="http://jenkins.example.com"
```
Or manually create a job with the same structure:
* Invoke Gradle script -> Use Gradle Wapper: true
* Invoke Gradle script -> Tasks: clean test
* Process Job DSLs -> DSL scripts: jobs/**/*Jobs.groovy
* Process Job DSLs -> Additional classpath: src/main/groovy
* Publish JUnit test result report -> Test report XMLs: build/test-results/**/*.xml

## REST API Runner
A Gradle task **rest** is configured that can be used to create/update jobs via the Jenkins REST API, if desired. Normally a seed job is used to keep jobs in sync with the DSL, but this runner might be useful if you'd rather process the DSL outside of the Jenkins environment or if you want to create the seed job from a DSL script.

### Inheritance Hierarchy
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

### Call Stack
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
