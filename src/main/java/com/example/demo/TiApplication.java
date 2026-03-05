package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TiApplication {

	public static void main(String[] args) {
		SpringApplication.run(TiApplication.class, args);
	}
}

/*
ORGANIZAÇÃO DO PROJETO POR FEATURE

ESTRUTURA BACKEND (Spring Boot):

com.seuprojeto
│
├── user/
│   ├── User
│   ├── UserRepository
│   ├── UserService
│   ├── UserController
│
├── quiz/
│   ├── Quiz
│   ├── QuizRepository
│   ├── QuizService
│   ├── QuizController
│
├── progress/
│   ├── Progress
│   ├── ProgressService
│   ├── ProgressController
│
└── config
*/