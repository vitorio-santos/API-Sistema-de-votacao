package controller;

import dto.VotoDTO;
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
    public void votar(@PathVariable Long enqueteId,
                      @RequestHeader VotoDTO votoDTO) {

        votoService.votar(enqueteId,
                votoDTO.getUsuarioId(),
                votoDTO.getOpcaoId());
    }
}
