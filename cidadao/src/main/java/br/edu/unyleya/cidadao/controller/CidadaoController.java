package br.edu.unyleya.cidadao.controller;

import br.edu.unyleya.cidadao.model.Cidadao;
import javax.validation.Valid;

import br.edu.unyleya.cidadao.exception.ResourceNotFoundException;
import br.edu.unyleya.cidadao.repository.CidadaoRepository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/v1")
public class CidadaoController {

    @Autowired
    private CidadaoRepository cidadaoRepository;

    @GetMapping("/cidadaos")
    public List<Cidadao> getAllCidadaos() {
        return cidadaoRepository.findAll();
    }

    @GetMapping("/cidadaos/{id}")
    public ResponseEntity<Cidadao> getCidadaosById(@PathVariable(value = "id") Long cidadaoId)
            throws ResourceNotFoundException {
        Cidadao cidadao = cidadaoRepository.findById(cidadaoId)
                .orElseThrow(() -> new ResourceNotFoundException("Cidadão não encontrado! Id: " + cidadaoId));
        return ResponseEntity.ok().body(cidadao);
    }

    @PostMapping("/cidadaos")
    public Cidadao postCidadaos(@Valid @RequestBody Cidadao cidadao) {
        return cidadaoRepository.save(cidadao);
    }

    @PutMapping("/cidadaos/{id}")
    public ResponseEntity<Cidadao> updateCidadaos(@PathVariable(value = "id") Long cidadaoId,
            @Valid @RequestBody Cidadao cidadaoDetais) throws ResourceNotFoundException {
        Cidadao cidadao = cidadaoRepository.findById(cidadaoId)
                .orElseThrow(() -> new ResourceNotFoundException("Cidadão não encontrado! Id: " + cidadaoId));

        cidadao.setCpf(Optional.ofNullable(cidadaoDetais.getCpf()).orElse(cidadao.getCpf()));
        cidadao.setEndereco(Optional.ofNullable(cidadaoDetais.getEndereco()).orElse(cidadao.getEndereco()));
        cidadao.setNome(Optional.ofNullable(cidadaoDetais.getNome()).orElse(cidadao.getNome()));
        cidadao.setSexo(Optional.ofNullable(cidadaoDetais.getSexo()).orElse(cidadao.getSexo()));

        Cidadao updateCidadao = cidadaoRepository.save(cidadao);

        return ResponseEntity.ok(updateCidadao);
    }

    @DeleteMapping("/cidadaos/{id}")
    public Map<String, Boolean> deleteCidadao(@PathVariable(value = "id") Long cidadaoId)
            throws ResourceNotFoundException {
        Cidadao cidadao = cidadaoRepository.findById(cidadaoId)
                .orElseThrow(() -> new ResourceNotFoundException("Cidadão não encontrado! Id: " + cidadaoId));

        cidadaoRepository.delete(cidadao);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
