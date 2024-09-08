package com.pulkit.journal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.pulkit.journal.entity.Journal;
import com.pulkit.journal.repository.JournalRepository;

@Service
public class JournalKafkaConsumer {
	@Autowired
	private JournalRepository journalRepository;
	@KafkaListener(topics="user-events",groupId = "journal-group")
	public void listen(String message) {
		Journal journal=new Journal(message);
		journalRepository.save(journal);
		System.out.println("Received and saved message: "+message);
		
		
	}
}
