package com.spotify.restapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.spotify.restapi.model.Album;
import com.spotify.restapi.service.AlbumService;

@RestController
@RequestMapping("/api/albums")
@Tag(name = "Álbuns", description = "Endpoints para gerenciamento de álbuns")
public class AlbumController {

    private final AlbumService albumService;

    @Autowired
    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @Operation(
        summary = "Busca um álbum pelo seu ID",
        description = "Retorna os detalhes de um álbum a partir do seu ID único do Spotify.",
        responses = {
            @ApiResponse(responseCode = "200", description = "Detalhes do álbum encontrados com sucesso"),
            @ApiResponse(responseCode = "404", description = "Álbum não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
        }
    )
    @GetMapping("/{id}")
    public ResponseEntity<Album> getAlbumById(@PathVariable String id) {
        Album album = albumService.getAlbumById(id);
        return (album != null)
            ? ResponseEntity.ok(album)
            : ResponseEntity.notFound().build();
    }

    @Operation(
        summary = "Cria um novo álbum (simulação)",
        description = "Endpoint para demonstrar a criação de um novo recurso.",
        responses = {
            @ApiResponse(responseCode = "201", description = "Álbum criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
        }
    )
    @PostMapping
    public ResponseEntity<Album> createAlbum(@RequestBody Album album) {
        Album newAlbum = albumService.createAlbum(album);
        return ResponseEntity.status(HttpStatus.CREATED).body(newAlbum);
    }

    @Operation(
        summary = "Atualiza um álbum existente (simulação)",
        description = "Endpoint para demonstrar a atualização de um recurso.",
        responses = {
            @ApiResponse(responseCode = "200", description = "Álbum atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Álbum não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
        }
    )
    @PutMapping("/{id}")
    public ResponseEntity<Album> updateAlbum(@PathVariable String id, @RequestBody Album album) {
        Album updatedAlbum = albumService.updateAlbum(id, album);
        return (updatedAlbum != null)
            ? ResponseEntity.ok(updatedAlbum)
            : ResponseEntity.notFound().build();
    }

    @Operation(
        summary = "Deleta um álbum (simulação)",
        description = "Endpoint para demonstrar a exclusão de um recurso.",
        responses = {
            @ApiResponse(responseCode = "204", description = "Álbum deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Álbum não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
        }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAlbum(@PathVariable String id) {
        boolean deleted = albumService.deleteAlbum(id);
        return (deleted)
            ? ResponseEntity.noContent().build()
            : ResponseEntity.notFound().build();
    }
}
