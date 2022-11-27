package com.tfe.fournil.repository;

import com.tfe.fournil.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;



public interface FeedbackRepository extends JpaRepository<Feedback,Long> {

}
