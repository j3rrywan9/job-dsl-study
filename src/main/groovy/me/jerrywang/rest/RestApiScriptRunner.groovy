package me.jerrywang.rest

import javaposse.jobdsl.dsl.DslScriptLoader
import me.jerrywang.rest.RestApiJobManagement

String pattern = System.getProperty('pattern')
String baseUrl = System.getProperty('baseUrl')

if (!pattern || !baseUrl) {
    println 'Usage: -Dpattern=<pattern> -DbaseUrl=<baseUrl>'
    System.exit 1
}

println "pattern = $pattern"
println "baseUrl = $baseUrl"

RestApiJobManagement jobManagement = new RestApiJobManagement(baseUrl)

new FileNameFinder().getFileNames('.', pattern).each { String fileName ->
    println "\nProcessing file: $fileName"
    File file = new File(fileName)
    DslScriptLoader.runDslEngine(file.text, jobManagement)
    println "\nFinished processing file: $fileName"
}

