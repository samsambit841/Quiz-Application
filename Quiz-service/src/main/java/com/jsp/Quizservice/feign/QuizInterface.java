package com.jsp.Quizservice.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.jsp.Quizservice.dto.Questionwrapper;
import com.jsp.Quizservice.dto.Response;



@FeignClient("QUESTION-SERVICE")
public interface QuizInterface {
	@GetMapping("/question/generate")
	public ResponseEntity<List<Integer>>getquestionforquiz(@RequestParam String categoryName,@RequestParam Integer numQuestions);
	@PostMapping("/question/getquestion")
	public ResponseEntity <List<Questionwrapper>>getquestionfromid(@RequestBody List<Integer>Qid);
	
	@PostMapping("question/getscore")
	public ResponseEntity<Integer> getscore(@RequestBody List<Response> responses);

}
