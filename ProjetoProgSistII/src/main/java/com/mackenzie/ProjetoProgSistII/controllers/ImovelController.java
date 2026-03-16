package com.mackenzie.ProjetoProgSistII.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mackenzie.ProjetoProgSistII.exceptions.ResourceNotFoundException;
import com.mackenzie.ProjetoProgSistII.models.Imovel;
import com.mackenzie.ProjetoProgSistII.repository.ImovelRepository;

@RestController
@RequestMapping("/api/imoveis")
public class ImovelController {

    private final ImovelRepository imovelRepository;

    
    public ImovelController(ImovelRepository imovelRepository){
        this.imovelRepository = imovelRepository;
    }

    @GetMapping
    public Iterable<Imovel> listarTodos(){
        return imovelRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Imovel> buscarPorId(@PathVariable Long id){
        return imovelRepository.findById(id);
    }

    @GetMapping("/bairro/{bairro}")
    public Iterable<Imovel> buscarPorBairro(@PathVariable String bairro){
        return imovelRepository.findByBairro(bairro);
    }

    @GetMapping("/cidade/{cidade}")
    public Iterable<Imovel> buscarPorCidade(@PathVariable String cidade){
        return imovelRepository.findByCidade(cidade);
    }

    @GetMapping("/disponiveis")
    public List<Imovel> listarImoveisDisponiveis() {
        return imovelRepository.findByDisponivel(true);
    }

    @PostMapping
    public Imovel cadastrar(@RequestBody Imovel imovel){
        return imovelRepository.save(imovel);
    }

    @PutMapping("/{idImovel}")
    public Optional<Imovel> updateImovel(@RequestBody Imovel imovelRequest, @PathVariable Long idImovel) {
        Optional<Imovel> opt = imovelRepository.findById(idImovel);
        if (opt.isPresent()) {
            Imovel imovel = opt.get();
            imovel.setEndereco(imovelRequest.getEndereco());
            imovel.setArea(imovelRequest.getArea());
            imovel.setQuantidadeDeQuartos(imovelRequest.getQuantidadeDeQuartos());
            imovel.setPreco(imovelRequest.getPreco());
            imovel.setDisponivel(imovelRequest.getDisponivel());
            imovelRepository.save(imovel);
            return Optional.of(imovel);
        } else {
            throw new ResourceNotFoundException("Imovel não encontrado.");
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        imovelRepository.deleteById(id);
    }
}
