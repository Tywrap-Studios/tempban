package org.tywrapstudios.tempban.api.bandb

import org.json.JSONArray
import org.json.JSONObject
import org.tywrapstudios.tempban.Tempban
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import java.time.Instant
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

object ApiClient {

    private val httpClient = HttpClient.newHttpClient()

    const val serverAddress = "" // get from config

    // TODO: HANDLE EXCEPTIONS WITH TRY-CATCH

    fun getAllBans(): JSONArray {
        val request = HttpRequest.newBuilder(URI("$serverAddress/api/ban"))
            .GET().build()
        val response = httpClient.send(request, HttpResponse.BodyHandlers.ofString())
        val jsonRes = JSONArray(response.body())
        return jsonRes
    }

    fun addBan(playerName: String, uuid: String, reason: String, bannedBy: String, bannedAt: Instant, expiresAt: Instant?): Boolean {
        val json = JSONObject().apply {
            put("playerName", playerName)
            put("uuid", uuid)
            put("reason", reason)
            put("bannedBy", bannedBy)
            put("bannedAt", bannedAt.atOffset(ZoneOffset.UTC).format(DateTimeFormatter.ISO_INSTANT))
            put("expiresAt", expiresAt?.atOffset(ZoneOffset.UTC)?.format(DateTimeFormatter.ISO_INSTANT))
        }

        val request = HttpRequest.newBuilder(URI("$serverAddress/api/ban"))
            .POST(HttpRequest.BodyPublishers.ofString(json.toString()))
            .header("Content-type", "application/json")
            .build()
            val response = httpClient.send(request, HttpResponse.BodyHandlers.discarding())
            return response.statusCode() == 201;
    }

    fun getBan(uuid: String): JSONObject? {
        val request = HttpRequest.newBuilder(URI("$serverAddress/api/ban/$uuid"))
            .GET().build()
        val response = httpClient.send(request, HttpResponse.BodyHandlers.ofString())
        if(response.statusCode() == 200) {
            val jsonRes = JSONObject(response.body())
            return jsonRes
        }
        return null
    }

    fun deleteBan(uuid: String): Boolean {
        val request = HttpRequest.newBuilder(URI("$serverAddress/api/ban/$uuid"))
            .DELETE().build()
        val response = httpClient.send(request, HttpResponse.BodyHandlers.discarding())
        return response.statusCode() == 204
    }
}