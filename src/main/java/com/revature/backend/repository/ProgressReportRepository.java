package com.revature.backend.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.revature.backend.model.ProgressReport;

public interface ProgressReportRepository extends JpaRepository<ProgressReport, Integer> {
	@Query(value = "select * from progress_report", nativeQuery = true)
	public Set<ProgressReport> findAllProgressReport();
	public ProgressReport findProgressReportById(Integer id);
}
