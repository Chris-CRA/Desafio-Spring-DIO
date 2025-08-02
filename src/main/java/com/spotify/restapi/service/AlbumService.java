package com.spotify.restapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spotify.restapi.model.Album;
import com.spotify.restapi.repository.SpotifyRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AlbumService {

    private final SpotifyRepository spotifyRepository;

    // Simulação de "banco de dados" em memória
    private final List<Album> albums = new ArrayList<>();

    @Autowired
    public AlbumService(SpotifyRepository spotifyRepository) {
        this.spotifyRepository = spotifyRepository;
    }

    public Album getAlbumById(String id) {
        // Busca local simulada
        Optional<Album> localAlbum = albums.stream()
                .filter(a -> a.getId().equalsIgnoreCase(id))
                .findFirst();

        if (localAlbum.isPresent()) {
            return localAlbum.get();
        }

        // Busca externa na API do Spotify
        try {
            return spotifyRepository.findAlbumById(id);
        } catch (Exception e) {
            // Em produção, você deveria logar isso
            return null;
        }
    }

    public Album createAlbum(Album album) {
        // Gera um ID aleatório simulado
        album.setId(UUID.randomUUID().toString());
        albums.add(album);
        return album;
    }

    public Album updateAlbum(String id, Album updatedAlbum) {
        Optional<Album> existingAlbum = albums.stream()
                .filter(a -> a.getId().equalsIgnoreCase(id))
                .findFirst();

        if (existingAlbum.isPresent()) {
            Album album = existingAlbum.get();
            album.setName(updatedAlbum.getName());
            album.setArtists(updatedAlbum.getArtists());
            album.setReleaseDate(updatedAlbum.getReleaseDate());
            return album;
        }

        return null;
    }

    public boolean deleteAlbum(String id) {
        return albums.removeIf(album -> album.getId().equalsIgnoreCase(id));
    }
}
