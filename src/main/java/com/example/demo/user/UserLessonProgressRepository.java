package com.example.demo.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserLessonProgressRepository extends JpaRepository<UserLessonProgress, Long> {

    // "Usuario_Id": desce no campo usuario e olha o id dele.
    boolean existsByUsuario_IdAndLessonId(Long usuarioId, Long lessonId);

    UserLessonProgress findTopByUsuario_IdAndLessonIdOrderByConcluidoEmDesc(Long usuarioId, Long lessonId);

    List<UserLessonProgress> findByUsuario_Id(Long usuarioId);

    void deleteByUsuario_Id(Long usuarioId);
}
