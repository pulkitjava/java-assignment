package com.pulkit.journal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pulkit.journal.entity.Journal;

public interface JournalRepository extends JpaRepository<Journal, Long> {

	List<Journal> findAll();

}
