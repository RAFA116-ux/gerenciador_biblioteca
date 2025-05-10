package com.rafael.ituiutaba.gerenciadorBibliotecario.service;

import com.rafael.ituiutaba.gerenciadorBibliotecario.dto.LivroDTO;
import com.rafael.ituiutaba.gerenciadorBibliotecario.entity.Livro;
import com.rafael.ituiutaba.gerenciadorBibliotecario.mapper.LivroMapper;
import com.rafael.ituiutaba.gerenciadorBibliotecario.repository.LivroRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class LivroService {

    private static final Logger logger = LogManager.getLogger(LivroService.class);
    @Autowired
    private LivroRepository livroRepository;
    private final WebClient webClient = WebClient.create();
    private final RestTemplate restTemplate = new RestTemplate();
    public List<LivroDTO> listarTodos() {
        logger.debug("Obtendo todos os livros do repositório");
        return livroRepository.findAll().stream()
                .map(LivroMapper::toDTO)
                .collect(Collectors.toList());
    }
    public LivroDTO buscarPorId(Long id) {
        logger.debug("Buscando livro por ID: {}", id);
        Livro livro = livroRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Livro não encontrado"));
        return LivroMapper.toDTO(livro);
    }
    public LivroDTO salvar(LivroDTO dto) {
        logger.debug("Salvando livro: {}", dto.getTitulo());
        Livro livro = LivroMapper.toEntity(dto);
        return LivroMapper.toDTO(livroRepository.save(livro));
    }
    public LivroDTO atualizar(Long id, LivroDTO dto) {
        logger.debug("Atualizando livro ID: {}", id);
        Livro existente = livroRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Livro não encontrado"));
        existente.setTitulo(dto.getTitulo());
        existente.setAutor(dto.getAutor());
        existente.setIsbn(dto.getIsbn());
        existente.setDataPublicacao(dto.getDataPublicacao());
        return LivroMapper.toDTO(livroRepository.save(existente));
    }
    public void deletar(Long id) {
        logger.debug("Deletando livro ID: {}", id);
        if (!livroRepository.existsById(id)) {
            throw new NoSuchElementException("Livro não encontrado para exclusão");
        }
        livroRepository.deleteById(id);
    }
    public String chamarApiComWebClient() {
        logger.info("Chamando API pública com WebClient");
        return webClient.get()
                .uri("https://api.publicapis.org/entries")
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
    public String chamarApiComRestTemplate() {
        logger.info("Chamando API pública com RestTemplate");
        return restTemplate.getForObject("https://api.publicapis.org/entries", String.class);
    }

}
