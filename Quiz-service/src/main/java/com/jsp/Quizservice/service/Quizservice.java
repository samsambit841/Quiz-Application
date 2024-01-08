package com.jsp.Quizservice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.Quizservice.dao.QuizDao;
import com.jsp.Quizservice.dto.Questionwrapper;
import com.jsp.Quizservice.dto.Quiz;
import com.jsp.Quizservice.dto.Response;
import com.jsp.Quizservice.feign.QuizInterface;

@Service
public class Quizservice {

	@Autowired
	QuizDao quizdao;

	@Autowired
	QuizInterface quizinterface;

	public ResponseEntity<String> createquiz(String categoryName, int numQuestions, String title) {

		List<Integer> questions = quizinterface.getquestionforquiz(categoryName, numQuestions).getBody();
		Quiz q = new Quiz();
		q.setTitle(title);
		q.setQuestionids(questions);
		quizdao.save(q);

		return new ResponseEntity<>("success", HttpStatus.CREATED);
	}

	public ResponseEntity<List<Questionwrapper>> getquizquestion(Integer id) {

		Quiz quiz = quizdao.findById(id).get();
		List<Integer> questionids = quiz.getQuestionids();
		ResponseEntity<List<Questionwrapper>> questionforuser = quizinterface.getquestionfromid(questionids);

		return questionforuser;
	}

	public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {

		ResponseEntity<Integer> score = quizinterface.getscore(responses);
		return score;
	}
}
