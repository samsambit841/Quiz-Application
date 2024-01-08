package com.jsp.questionservice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.questionservice.dao.QuestionDao;
import com.jsp.questionservice.dto.Question;
import com.jsp.questionservice.dto.Questionwrapper;
import com.jsp.questionservice.dto.Response;

@Service
public class QuestionService {

	@Autowired
	QuestionDao questiondao;

	public ResponseEntity<List<Question>> getallquestion() {
		try {
			return new ResponseEntity<>(questiondao.findAll(), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_GATEWAY);
	}

	public ResponseEntity<List<Question>> getallquestionByCategory(String category) {
		try {
			return new ResponseEntity<>(questiondao.findByCategory(category), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_GATEWAY);
	}

	public ResponseEntity<String> addquestion(Question question) {
		questiondao.save(question);
		return new ResponseEntity<>("success", HttpStatus.CREATED);

	}

	public ResponseEntity<List<Integer>> getquestionforquiz(String category, Integer numQ) {
		List<Integer> questions = questiondao.findRandomQuestionByCategory(category, numQ);
		return new ResponseEntity<>(questions, HttpStatus.OK);
	}

	public ResponseEntity<List<Questionwrapper>> getquestionFromid(List<Integer> qid) {
		List<Questionwrapper> wrappers = new ArrayList<Questionwrapper>();
		for (Integer i : qid) {
			Question q = questiondao.findById(i).get();
			Questionwrapper qw = new Questionwrapper(q.getId(), q.getQuestionTitle(), q.getOption1(), q.getOption2(),
					q.getOption3(), q.getOption4());
			wrappers.add(qw);
		}
		return new ResponseEntity<>(wrappers, HttpStatus.OK);
	}

	public ResponseEntity<Integer> getscore(List<Response> responses) {
		int right = 0;

		for (Response response : responses) {

			Question questions = questiondao.findById(response.getId()).get();
			if (response.getResponse().equals(questions.getRightAnswer()))
				right++;
		}
		return new ResponseEntity<>(right, HttpStatus.OK);
	}

}
