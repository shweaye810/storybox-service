package com.ss.storyboxservice.controller;

import java.security.Principal;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ss.storyboxservice.model.Journal;
import com.ss.storyboxservice.service.JournalService;

@RestController
@RequestMapping("/journals")
public class JournalController {
	
	@Autowired
	JournalService journalService;
	
	@PostMapping()
	public ResponseEntity<Journal> postJournal(Principal principal,
			@RequestBody Journal journal,
			@RequestHeader("Authorization") String token) {
		return new ResponseEntity<>(journalService.post(principal, journal, token), HttpStatus.CREATED);
	}
	@GetMapping()
	public ResponseEntity<Collection<Journal>> postJournal(Authentication principal,
			@RequestHeader("Authorization") String token) {
		System.out.println(principal.getName());
		return new ResponseEntity<>(journalService.get(principal, token), HttpStatus.OK);
	}

}
