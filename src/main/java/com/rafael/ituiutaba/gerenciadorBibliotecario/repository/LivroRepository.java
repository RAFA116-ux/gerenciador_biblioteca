package com.rafael.ituiutaba.gerenciadorBibliotecario.repository;

import com.rafael.ituiutaba.gerenciadorBibliotecario.entity.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LivroRepository   extends JpaRepository<Livro,Long > {
}
