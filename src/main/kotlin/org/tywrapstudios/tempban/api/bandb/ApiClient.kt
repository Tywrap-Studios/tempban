package org.tywrapstudios.tempban.api.bandb

import org.json.simple.JSONObject
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

    fun getAllBans(): JSONObject {

        TODO()
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

        try {
            val request = HttpRequest.newBuilder(URI("")) // address goes in here
                .POST(HttpRequest.BodyPublishers.ofString(json.toJSONString()))
                .header("Content-type", "application/json")
                .build()

            val response = httpClient.send(request, HttpResponse.BodyHandlers.discarding())
            if(response.statusCode() == 201) return true;
            else return false;
        } catch (e: Exception) {
            // logger here
            return false;
        }

        return false;
    }
}