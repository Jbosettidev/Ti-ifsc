package com.example.demo.repository;

import com.example.demo.model.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

// Isso aqui é praticamente mágica do Spring Data: você só declara essa
// interface (nem precisa implementar nada!) e ganha de graça métodos como
// findById, findAll, save, deleteById... tudo isso já sabe conversar com
// o banco de dados sozinho, baseado no tipo Lesson e no tipo do Id (Long).
public interface LessonRepository extends JpaRepository<Lesson, Long> {
}