package com.jsp.questionservice.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.jsp.questionservice.dto.Question;

@Repository
public interface QuestionDao extends JpaRepository<Question, Integer> {

	List<Question>findByCategory(String Category);

	@Query(value = "SELECT q.id FROM Question q WHERE q.category = :category ORDER BY RAND() LIMIT :numQ", nativeQuery = true)
	List<Integer> findRandomQuestionByCategory(@Param("category") String category, @Param("numQ") int numQ);

}
