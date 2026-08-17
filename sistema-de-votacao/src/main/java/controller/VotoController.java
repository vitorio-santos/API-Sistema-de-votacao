package controller;

import dto.VotoDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.VotoService;

@RestController
@RequestMapping("/enquetes")
public class VotoController {

    private final VotoService votoService;

    public VotoController(VotoService votoService) {
        this.votoService = votoService;
    }

    @PostMapping("/{enqueteId}/votos")
    public ResponseEntity<Void> votar(
            @PathVariable Long enqueteId,
            @RequestBody VotoDTO votoDTO) {

        votoService.votar(
                enqueteId,
                votoDTO.getUsuarioId(),
                votoDTO.getOpcaoId()
        );

        return ResponseEntity.noContent().build();
    }
}
