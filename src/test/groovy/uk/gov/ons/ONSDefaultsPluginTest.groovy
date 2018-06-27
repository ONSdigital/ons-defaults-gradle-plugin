package uk.gov.ons

import spock.lang.Specification
import org.gradle.api.*
import org.gradle.api.plugins.*
import org.gradle.testfixtures.ProjectBuilder

class ONSDefaultsPluginTest extends Specification {
    Project project = ProjectBuilder.builder().build()
    
    def setup(){
        project.apply plugin: ONSDefaultsPlugin
    }

    def "project group name should be set"(){
        expect:
        project.group == "uk.gov.ons"
    }

    def "only allows corporate repo"() {
        expect:
        project.repositories.size() == 1
        
        when:
        project.repositories { mavenCentral() }
        
        then:
        project.repositories.size() == 1
    }
}
