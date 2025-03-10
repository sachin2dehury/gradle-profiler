plugins {
    id("com.gradle.enterprise").version("3.10.3")
    id("com.gradle.enterprise.gradle-enterprise-conventions-plugin").version("0.7.2")
}

checkIfCurrentJavaIsCompatible()

rootProject.name = "gradle-profiler"

include("profiler-api")
include("profiler-gradle-only-api")
include("profiler-asyncprofiler")
include("profiler-jprofiler")
include("profiler-jfr")
include("profiler-yourkit")
include("profiler-buildscan")
include("profiler-chrometrace")
include("profiler-heapdump")

include("invoker-api")

include("chrome-trace")
include("build-operations")
include("heap-dump")
include("client-protocol")
include("instrumentation-support")
include("studio-agent")
include("studio-plugin")

rootProject.children.forEach {
    it.projectDir = rootDir.resolve( "subprojects/${it.name}")
}

/**
 * Intellij-gradle-plugin requires Java 11.
 */
fun checkIfCurrentJavaIsCompatible() {
    if (!JavaVersion.current().isCompatibleWith(JavaVersion.VERSION_11)) {
        throw GradleException("This project should be run with Java 11 or later, but it was run with Java ${JavaVersion.current()}.")
    }
}
