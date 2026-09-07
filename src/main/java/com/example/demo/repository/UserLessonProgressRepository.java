package com.example.demo.repository;

import com.example.demo.model.UserLessonProgress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserLessonProgressRepository extends JpaRepository<UserLessonProgress, Long> {

    // "Usuario_Id" com underscore diz ao Spring Data: "desce até o campo
    // usuario, e dentro dele olha o campo id" — é assim que ele navega
    // por uma relação (@ManyToOne) dentro do nome do método.
    boolean existsByUsuario_IdAndLessonId(Long usuarioId, Long lessonId);

    UserLessonProgress findTopByUsuario_IdAndLessonIdOrderByConcluidoEmDesc(Long usuarioId, Long lessonId);
}