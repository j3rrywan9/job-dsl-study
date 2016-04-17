package me.jerrywang.debug

import groovyx.net.http.ContentType
import groovyx.net.http.HttpResponseException
import groovyx.net.http.HttpResponseDecorator
import groovyx.net.http.RESTClient

String baseUrl = System.getProperty('baseUrl')

restClient = new RESTClient(baseUrl)

String getPath(String name, boolean isView) {
    if (name.startsWith('/')) {
        return '/' + getPath(name[1..-1], isView)
    }
    isView ? "view/$name" : "/job/$name"
}

println restClient.getUri()
println getPath('seed', false) + '/config.xml'

String fetchExistingXml(String name, boolean isView) {
    HttpResponseDecorator resp = restClient.get(
            contentType: ContentType.TEXT,
            path: getPath(name, isView) + '/config.xml',
            headers: [Accept: 'application/xml'],
    )
    resp?.data?.text
}

try {
    println fetchExistingXml('seed', false)
} catch (HttpResponseException e) {
    println e.toString()
    println e.getResponse().status
}
