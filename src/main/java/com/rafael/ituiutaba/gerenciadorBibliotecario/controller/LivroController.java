package com.rafael.ituiutaba.gerenciadorBibliotecario.controller;

import com.rafael.ituiutaba.gerenciadorBibliotecario.dto.LivroDTO;
import com.rafael.ituiutaba.gerenciadorBibliotecario.service.LivroService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livros")
public class LivroController {

    private static final Logger logger = LogManager.getLogger(LivroController.class);
    @Autowired
    private LivroService livroService;
    @GetMapping
    public ResponseEntity<List<LivroDTO>> listarTodos() {
        logger.info("Listando todos os livros");
        return ResponseEntity.ok(livroService.listarTodos());
    }

    // Pelo postman deverá ser chamado destamaneira
    //  http://localhost:8081/livros/2
    @GetMapping("/{id}")
    public ResponseEntity<LivroDTO> buscarPorId(@PathVariable Long id) {
        logger.info("Buscando livro por ID: {}", id);
        return ResponseEntity.ok(livroService.buscarPorId(id));
    }

    // No postman tem que usar
    //   http://localhost:8081/livros/salvar
    @PostMapping("/salvar")
    public ResponseEntity<LivroDTO> salvar(@RequestBody LivroDTO dto) {
        logger.info("Salvando novo livro: {}", dto.getTitulo());
        return ResponseEntity.ok(livroService.salvar(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LivroDTO> atualizar(@PathVariable Long id, @RequestBody
    LivroDTO dto) {
        logger.info("Atualizando livro ID: {}", id);
        return ResponseEntity.ok(livroService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        logger.info("Deletando livro ID: {}", id);
        livroService.deletar(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/externo/webclient")
    public ResponseEntity<String> chamarApiComWebClient() {
        logger.info("Chamando API externa com WebClient");
        return ResponseEntity.ok(livroService.chamarApiComWebClient());
    }
    @GetMapping("/externo/resttemplate")
    public ResponseEntity<String> chamarApiComRestTemplate() {
        logger.info("Chamando API externa com RestTemplate");
        return ResponseEntity.ok(livroService.chamarApiComRestTemplate());
    }
}
