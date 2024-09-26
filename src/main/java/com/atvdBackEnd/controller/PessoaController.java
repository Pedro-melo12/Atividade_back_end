package com.atvdBackEnd.controller;

import com.atvdBackEnd.dto.PessoaDto;
import com.atvdBackEnd.model.Pessoa;
import com.atvdBackEnd.repository.PessoaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class PessoaController {

    @Autowired
    private PessoaRepository pessoaRepository;


    @PostMapping("/pessoas")
    public ResponseEntity<Pessoa> createPessoa(@RequestBody PessoaDto pessoaDto) {
        Pessoa pessoa = new Pessoa();

        BeanUtils.copyProperties(pessoaDto, pessoa);

        Pessoa savedPessoa = pessoaRepository.save(pessoa);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedPessoa);
    }

    @GetMapping("/pessoas/{id}")
    public ResponseEntity<Pessoa> getPessoaById(@PathVariable Long id) {
        Optional<Pessoa> optionalPessoa = pessoaRepository.findById(id);
        if (optionalPessoa.isPresent()) {
            return ResponseEntity.ok(optionalPessoa.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
