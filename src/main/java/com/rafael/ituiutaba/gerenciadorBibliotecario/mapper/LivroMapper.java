package com.rafael.ituiutaba.gerenciadorBibliotecario.mapper;

import com.rafael.ituiutaba.gerenciadorBibliotecario.dto.LivroDTO;
import com.rafael.ituiutaba.gerenciadorBibliotecario.entity.Livro;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Builder
@Data
public class LivroMapper {

    public static LivroDTO toDTO(Livro livro) {
        LivroDTO dto = new LivroDTO();
        dto.setId(livro.getId());
        dto.setTitulo(livro.getTitulo());
        dto.setAutor(livro.getAutor());
        dto.setIsbn(livro.getIsbn());
        dto.setDataPublicacao(livro.getDataPublicacao());
        return dto;
    }
    public static Livro toEntity(LivroDTO dto) {
        Livro livro = new Livro();
        livro.setId(dto.getId());
        livro.setTitulo(dto.getTitulo());
        livro.setAutor(dto.getAutor());
        livro.setIsbn(dto.getIsbn());
        livro.setDataPublicacao(dto.getDataPublicacao());
        return livro;
    }
}
