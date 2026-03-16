package com.mackenzie.ProjetoProgSistII.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.bind.annotation.RequestParam;

import com.mackenzie.ProjetoProgSistII.models.Aluguel;
import com.mackenzie.ProjetoProgSistII.models.Inquilino;
import com.mackenzie.ProjetoProgSistII.repository.AluguelRepository;
import com.mackenzie.ProjetoProgSistII.repository.InquilinoRepository;


@RestController
@RequestMapping("/api/inquilino")
public class InquilinoController {
    private final InquilinoRepository inquilinoRepository;
    private final AluguelRepository aluguelRepository;

    public InquilinoController(InquilinoRepository inquilinoRepository, AluguelRepository aluguelRepository) {
        this.inquilinoRepository = inquilinoRepository;
        this.aluguelRepository = aluguelRepository;
    }

    @PostMapping
    public Inquilino cadastrarInquilino(@RequestBody Inquilino inquilino){
        return inquilinoRepository.save(inquilino);
    }

    @PostMapping("/login")
    public boolean login(@RequestParam Long id, @RequestParam String password){
        Optional<Inquilino> inquilino = inquilinoRepository.findByIdAndSenha(id, password);
        return inquilino.isPresent();
    }

    @GetMapping("/{id}/alugueis")
    public List<Aluguel> listarImoveisAlugados(@PathVariable Long id) {
        Optional<Inquilino> inquilinoOpt = inquilinoRepository.findById(id);
        
        if (inquilinoOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Inquilino de id " + id + " não encontrado!");
        }
        
        return aluguelRepository.findAluguelByInquilino(inquilinoOpt.get());
    }

    @GetMapping("/{id}/alugueis/bairro/{bairro}")
    public List<Aluguel> listarAlugueisPorBairro(@PathVariable Long id, @PathVariable String bairro) {
        return aluguelRepository.findByInquilinoIdAndImovel_Bairro(id, bairro);
    }
    
    @GetMapping("/{id}/alugueis/cidade/{cidade}")
    public List<Aluguel> listarAlugueisPorCidade(@PathVariable Long id, @PathVariable String cidade) {
        return aluguelRepository.findByInquilinoIdAndImovel_Cidade(id, cidade);
    }

    @DeleteMapping("/aluguel/{idAluguel}")
    public void deleteAluguel(@PathVariable Long idAluguel) {
        aluguelRepository.deleteById(idAluguel);
    }
}
