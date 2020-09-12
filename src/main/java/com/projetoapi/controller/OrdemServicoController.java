package com.projetoapi.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.projetoapi.IRepository.IOrdemServicoRepository;
import com.projetoapi.domain.service.GestaoOrdemServicoService;
import com.projetoapi.model.OrdemServico;

@RestController
@RequestMapping("/ordens-servico")
public class OrdemServicoController {
	
	@Autowired
	private GestaoOrdemServicoService gestaoOrdemServico;
	
	@Autowired
	private IOrdemServicoRepository ordemServicoRepository;
	
	private ModelMapper modelMapper;

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public OrdemServico criar(@Valid @RequestBody OrdemServico ordemServico){
	return gestaoOrdemServico.criar(ordemServico);
	}
	
	@GetMapping
	public List<OrdemServico> listar(){
		return ordemServicoRepository.findAll();
	}
	
	public ResponseEntity<OrdemServico> buscar(Long ordemServicoId){
		Optional<OrdemServico> ordemServico = ordemServicoRepository.findById(ordemServicoId);
		
		if(ordemServico.isPresent()){
			return ResponseEntity.ok(ordemServico.get());
			}
		return ResponseEntity.notFound().build();
		}

}