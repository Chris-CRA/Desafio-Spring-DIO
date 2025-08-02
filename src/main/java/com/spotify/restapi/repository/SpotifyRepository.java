package com.spotify.restapi.repository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.spotify.restapi.model.Album;

import java.util.Base64;
import java.util.Map;

@Repository
public class SpotifyRepository {

    private final RestTemplate restTemplate;

    @Value("${spotify.api.client-id}")
    private String clientId;

    @Value("${spotify.api.client-secret}")
    private String clientSecret;

    public SpotifyRepository() {
        this.restTemplate = new RestTemplate();
    }

    private String getAccessToken() {
        String authUrl = "https://accounts.spotify.com/api/token";
        String credentials = clientId + ":" + clientSecret;
        String encodedCredentials = Base64.getEncoder().encodeToString(credentials.getBytes());

        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(encodedCredentials);
        headers.add("Content-Type", "application/x-www-form-urlencoded");

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "client_credentials");

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(body, headers);
        ResponseEntity<Map> response = restTemplate.postForEntity(authUrl, request, Map.class);
        return (String) response.getBody().get("access_token");
    }

    public Album findAlbumById(String id) {
        String accessToken = getAccessToken();
        String url = "https://api.spotify.com/v1/albums/" + id;

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);
        HttpEntity<?> entity = new HttpEntity<>(headers);

        ResponseEntity<Album> response = restTemplate.exchange(url, HttpMethod.GET, entity, Album.class);
        return response.getBody();
    }
}