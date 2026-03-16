package com.mackenzie.ProjetoProgSistII.controllers;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.mackenzie.ProjetoProgSistII.models.Aluguel;
import com.mackenzie.ProjetoProgSistII.models.Inquilino;
import com.mackenzie.ProjetoProgSistII.models.Imovel;
import com.mackenzie.ProjetoProgSistII.repository.AluguelRepository;
import com.mackenzie.ProjetoProgSistII.repository.InquilinoRepository;
import com.mackenzie.ProjetoProgSistII.repository.ImovelRepository;


@RestController
@RequestMapping("/api/aluguel")
public class AluguelController {
    private final InquilinoRepository inquilinoRepository;
    private final AluguelRepository aluguelRepository;
    private final ImovelRepository imovelRepository;

    public AluguelController(InquilinoRepository inquilinoRepository, AluguelRepository aluguelRepository, ImovelRepository imovelRepository) {
        this.inquilinoRepository = inquilinoRepository;
        this.aluguelRepository = aluguelRepository;
        this.imovelRepository = imovelRepository;
    }

    @PostMapping
    public Aluguel cadastrarAluguel(@RequestBody Aluguel aluguel) {
        Optional<Inquilino> inquilinoOpt = inquilinoRepository.findById(aluguel.getInquilino().getId());
        if (inquilinoOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Inquilino não encontrado.");
        }
        Optional<Imovel> imovelOpt = imovelRepository.findById(aluguel.getImovel().getIdImovel());
        if (imovelOpt.isEmpty() || !imovelOpt.get().getDisponivel()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Imóvel não encontrado ou não está disponível.");
        }
        Imovel imovel = imovelOpt.get();

        imovel.setDisponivel(false);
        imovelRepository.save(imovel);

        aluguel.setInquilino(inquilinoOpt.get());
        aluguel.setImovel(imovel);
        aluguel.setDataInicio(LocalDate.now());
        aluguel.setDataFim(null);

        return aluguelRepository.save(aluguel);
    }
}