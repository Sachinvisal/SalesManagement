package com.project.sales.Repo;

import com.project.sales.Entity.FAQ;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FAQRepo extends JpaRepository<FAQ , Long> {


}
