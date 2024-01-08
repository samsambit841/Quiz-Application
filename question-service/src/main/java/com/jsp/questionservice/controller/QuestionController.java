package com.jsp.questionservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.questionservice.dto.Question;
import com.jsp.questionservice.dto.Questionwrapper;
import com.jsp.questionservice.dto.Response;
import com.jsp.questionservice.service.QuestionService;

@RestController
@RequestMapping("/question")
public class QuestionController {
	
	@Autowired
	QuestionService questionService;
	
	@Autowired
	Environment enviornment;
	
	@GetMapping("/allquestion")
	public ResponseEntity<List<Question>> getall() {
		
		return questionService.getallquestion();
	}
	
	@GetMapping("/category/{category}")
	public ResponseEntity<List<Question>>getallquestionByCategory(@PathVariable String category) {
		
		return questionService.getallquestionByCategory(category);
	}
	
	@PostMapping("/add")
	public ResponseEntity<String> addquestion(@RequestBody Question question) {
		
		return questionService.addquestion(question);
	}
	
	@GetMapping("/generate")
	public ResponseEntity<List<Integer>>getquestionforquiz(@RequestParam String categoryName,@RequestParam Integer numQuestions){
		return questionService.getquestionforquiz(categoryName,numQuestions);
	}
	@PostMapping("/getquestion")
	public ResponseEntity <List<Questionwrapper>>getquestionfromid(@RequestBody List<Integer>Qid){
		System.out.println(enviornment.getProperty("local.server.port"));
		return questionService.getquestionFromid(Qid);
		
	}
	
	@PostMapping("/getscore")
	public ResponseEntity<Integer> getscore(@RequestBody List<Response> responses){
		return questionService.getscore(responses);
	}

}
