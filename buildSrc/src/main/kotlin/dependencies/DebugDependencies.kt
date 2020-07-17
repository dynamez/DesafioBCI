package dependencies

/**
 * Project debug dependencies, makes it easy to include external binaries or
 * other library modules to build.
 */
object DebugDependencies {
    const val KOTLINJUNIT =
        "org.jetbrains.kotlin:kotlin-test-junit:${BuildDependenciesVersions.KOTLIN}"
}
