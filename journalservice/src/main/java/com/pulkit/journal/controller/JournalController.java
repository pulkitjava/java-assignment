package com.pulkit.journal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pulkit.journal.entity.Journal;
import com.pulkit.journal.repository.JournalRepository;

@RestController
@RequestMapping("/api/journals")
public class JournalController {

	@Autowired
	private JournalRepository journalRepository;

	@GetMapping
	public ResponseEntity<List<Journal>> getAllJournals() {
		List<Journal> journals = journalRepository.findAll();
		return ResponseEntity.ok(journals);
	}
}
