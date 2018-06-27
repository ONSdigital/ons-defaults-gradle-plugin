package uk.gov.ons

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.repositories.*

// ONSDefaultsPlugin applies sensible defaults to Gradle projects
// in the ONS
class ONSDefaultsPlugin implements Plugin<Project> {
    def onsRepoURL = "http://art-p01"
    def group = "uk.gov.ons"

    void apply(Project project) {
        setGroup(project)
        setRepo(project)
    }

    // setRepo removes references to external artifact repositories and 
    // replaces them with internal repos
    def setRepo(Project project) {
        project.repositories {
            all { ArtifactRepository repo ->
                if(!(repo instanceof MavenArtifactRepository) || repo.url.toString() != onsRepoURL) {
                    project.logger.warn "Repository ${repo.url} removed. Only $onsRepoURL is allowed"
                    remove repo
                }
            }
            maven { url onsRepoURL }
        }
    }

    // setGroup ensures the project group id is set to ONS
    // default
    def setGroup(Project project) {
        project.group = this.group
    }
}
