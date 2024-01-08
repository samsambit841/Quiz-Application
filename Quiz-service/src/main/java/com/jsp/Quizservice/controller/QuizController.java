package com.jsp.Quizservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.Quizservice.dto.Questionwrapper;
import com.jsp.Quizservice.dto.Quizdto;
import com.jsp.Quizservice.dto.Response;
import com.jsp.Quizservice.service.Quizservice;

@RestController
@RequestMapping("/quiz")
public class QuizController {

	@Autowired
	Quizservice quizservice;
	
	@PostMapping("/create")
	public ResponseEntity<String> createquiz(@RequestBody Quizdto quizdto){
	return	quizservice.createquiz(quizdto.getCategoryName(),quizdto.getNumQuestions(),quizdto.getTitle());
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<List<Questionwrapper>> getquiz(@PathVariable Integer id){
		return quizservice.getquizquestion(id);
	}
	
	@PostMapping("submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id, @RequestBody List<Response> responses){
        return quizservice.calculateResult(id, responses);
    }
}
