package com.librarian_tool.librarian_tool.repository;

import com.librarian_tool.librarian_tool.model.ReviewsModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewsRepository extends JpaRepository<ReviewsModel, Long> {
}
