package core.network

import TestViewModel
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import logger.AppLogger
import org.koin.dsl.module

val provideHttpClient = module {
    single {
        HttpClient(CIO) {
            expectSuccess = true
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                })
            }
            install(Logging) {
                logger = object: Logger {
                    override fun log(message: String) {
                        AppLogger.log(message)
                    }
                }
                level = LogLevel.HEADERS
            }
        }
    }
}

val provideViewModel = module {
    factory {
        TestViewModel(get())
    }
}