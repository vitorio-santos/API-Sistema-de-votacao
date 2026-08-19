package controller;

import dto.CriarEnqueteDTO;
import dto.ResultadoDTO;
import infrastructure.entity.Enquete;
import org.springframework.web.bind.annotation.*;
import service.EnqueteService;

import java.util.List;

@RestController
@RequestMapping("/enquetes")
public class EnqueteController {

    private final EnqueteService enqueteService;

    public EnqueteController(EnqueteService enqueteService) {
        this.enqueteService = enqueteService;
    }

    @PostMapping
    public Enquete criar(@RequestBody CriarEnqueteDTO dto) {
        return enqueteService.criar(dto);
    }

    @GetMapping
    public List<Enquete> listar() {
        return enqueteService.listar();
    }
    @GetMapping("/{enqueteId}")
    public Enquete buscar(@PathVariable Long enqueteId) {
        return enqueteService.buscar(enqueteId);
    }

    @GetMapping("/{enqueteId}/resultado")
    public ResultadoDTO resultado(@PathVariable Long enqueteId) {
        return enqueteService.resultado(enqueteId);
    }

    @PatchMapping("/{enqueteId}/encerrar")
    public void encerrar(@PathVariable Long enqueteId) {
        enqueteService.encerrar(enqueteId);
    }
}
