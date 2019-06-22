package com.ss.storyboxservice.service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.ss.storyboxservice.model.Journal;

@FeignClient(name="journalCRUD", url="http://localhost:9000/journals")
interface JournalREST {
	
    @PostMapping()
	Resource<Journal> post(Journal journal, @RequestHeader("Authorization") String token);
	
    @GetMapping("/search/findByUsername?sort=date,desc")
    Resources<Journal> get(@RequestHeader("Authorization") String token, @RequestParam("username") String username);

}

@Service
public class JournalService {
	@Autowired
	JournalREST journalREST;
	
	
	public Journal post(Principal principal, Journal journal, String token) {
		journal.setDate(LocalDateTime.now());
		journal.setUsername(principal.getName());
			
		Journal jt = journalREST.post(journal, token).getContent();

		return jt;
	}
	
	public Collection<Journal> get(Principal principal, String token) {
		Resources<Journal> resources = journalREST.get(token, principal.getName());

		return resources.getContent();
	}
	

}
