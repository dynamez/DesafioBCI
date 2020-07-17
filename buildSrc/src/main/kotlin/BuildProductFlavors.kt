import com.android.build.gradle.internal.dsl.ProductFlavor
import org.gradle.api.NamedDomainObjectContainer

interface BuildProductFlavor {
    val name: String

    fun libraryCreate(
        namedDomainObjectContainer: NamedDomainObjectContainer<ProductFlavor>
    ): ProductFlavor

    fun appCreate(
        namedDomainObjectContainer: NamedDomainObjectContainer<ProductFlavor>
    ): ProductFlavor
}

object ProductFlavorDevelop : BuildProductFlavor {
    override val name = "dev"

    override fun appCreate(
        namedDomainObjectContainer: NamedDomainObjectContainer<ProductFlavor>
    ): ProductFlavor {
        return namedDomainObjectContainer.create(name) {
            applicationIdSuffix = ".dev"
            versionNameSuffix = "-dev"
            dimension = BuildProductDimensions.ENVIRONMENT
            buildConfigField(
                "String",
                "API_URL",
                "\"https://pokeapi.co/\""
            )
            buildConfigField(
                "String",
                "IMG_URL",
                "\"https://pokeres.bastionbot.org/images/pokemon/\""
            )
            buildConfigField("String", "ENDPOINT_VERSION", "\"api/v2/\"")
        }
    }

    override fun libraryCreate(
        namedDomainObjectContainer: NamedDomainObjectContainer<ProductFlavor>
    ): ProductFlavor {
        return namedDomainObjectContainer.create(name) {
            versionNameSuffix = "-dev"
            dimension = BuildProductDimensions.ENVIRONMENT
            buildConfigField(
                "String",
                "API_URL",
                "\"https://pokeapi.co/\""
            )
            buildConfigField(
                "String",
                "IMG_URL",
                "\"https://pokeres.bastionbot.org/images/pokemon/\""
            )
            buildConfigField("String", "ENDPOINT_VERSION", "\"api/v2/\"")
        }
    }
}

object ProductFlavorQA : BuildProductFlavor {
    override val name = "qa"

    override fun appCreate(
        namedDomainObjectContainer: NamedDomainObjectContainer<ProductFlavor>
    ): ProductFlavor {
        return namedDomainObjectContainer.create(name) {
            applicationIdSuffix = ".qa"
            versionNameSuffix = "-qa"
            dimension = BuildProductDimensions.ENVIRONMENT
            buildConfigField(
                "String",
                "API_URL",
                "\"https://pokeapi.co/\""
            )
            buildConfigField(
                "String",
                "IMG_URL",
                "\"https://pokeres.bastionbot.org/images/pokemon/\""
            )
            buildConfigField("String", "ENDPOINT_VERSION", "\"api/v2/\"")
        }
    }

    override fun libraryCreate(
        namedDomainObjectContainer: NamedDomainObjectContainer<ProductFlavor>
    ): ProductFlavor {
        return namedDomainObjectContainer.create(name) {
            versionNameSuffix = "-qa"
            dimension = BuildProductDimensions.ENVIRONMENT
            buildConfigField(
                "String",
                "API_URL",
                "\"https://pokeapi.co/\""
            )
            buildConfigField(
                "String",
                "IMG_URL",
                "\"https://pokeres.bastionbot.org/images/pokemon/\""
            )
            buildConfigField("String", "ENDPOINT_VERSION", "\"api/v2/\"")
        }
    }
}

object ProductFlavorProduction : BuildProductFlavor {
    override val name = "prod"

    override fun appCreate(
        namedDomainObjectContainer: NamedDomainObjectContainer<ProductFlavor>
    ): ProductFlavor {
        return namedDomainObjectContainer.create(name) {
            dimension = BuildProductDimensions.ENVIRONMENT
            buildConfigField(
                "String",
                "API_URL",
                "\"https://pokeapi.co/\""
            )
            buildConfigField(
                "String",
                "IMG_URL",
                "\"https://pokeres.bastionbot.org/images/pokemon/\""
            )
            buildConfigField("String", "ENDPOINT_VERSION", "\"api/v2/\"")
        }
    }

    override fun libraryCreate(
        namedDomainObjectContainer: NamedDomainObjectContainer<ProductFlavor>
    ): ProductFlavor {
        return namedDomainObjectContainer.create(name) {
            dimension = BuildProductDimensions.ENVIRONMENT
            buildConfigField(
                "String",
                "API_URL",
                "\"https://pokeapi.co/\""
            )
            buildConfigField(
                "String",
                "IMG_URL",
                "\"https://pokeres.bastionbot.org/images/pokemon/\""
            )
            buildConfigField("String", "ENDPOINT_VERSION", "\"api/v2/\"")
        }
    }
}
