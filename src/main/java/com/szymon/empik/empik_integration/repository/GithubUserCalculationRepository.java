package com.szymon.empik.empik_integration.repository;

import com.szymon.empik.empik_integration.model.UserStats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GithubUserCalculationRepository extends JpaRepository<UserStats, String> {
}
