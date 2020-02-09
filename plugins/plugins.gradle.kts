import org.gradle.jvm.tasks.Jar
import groovy.lang.GroovyObject

subprojects {
    afterEvaluate {
        apply(plugin = "java-library")
        dependencies {
            compileOnly(group = "com.google.code.findbugs", name = "annotations", version = "+")
            compile(project(":api"))
        }
        val sourceJar = task<Jar>("sourceJar") {
            description = "Creates a JAR that contains the source code."
            from(project.sourceSets["main"].allSource)
            classifier = "sources"
        }
        val javadocJar = task<Jar>("javadocJar") {
            dependsOn("javadoc")
            description = "Creates a JAR that contains the javadocs."
            from(tasks.named("javadoc"))
            classifier = "javadoc"
        }
    }
}
