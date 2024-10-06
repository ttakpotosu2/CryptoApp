package com.example.shared.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.http.HttpHeaders
import io.ktor.http.headers
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json

//expect fun getKtorEngine(): HttpClientEngine

@OptIn(ExperimentalSerializationApi::class)
fun createHttpClient(): HttpClient {
	return HttpClient(){
		install(Logging){
			logger = Logger.SIMPLE
			level = LogLevel.BODY
		}
		install(ContentNegotiation){
			json(Json {
				ignoreUnknownKeys = true
				prettyPrint = true
				explicitNulls = false
				isLenient = true
			})
		}
		
		
		headers {
			append(HttpHeaders.Accept, "application/json")
		}
	}
}