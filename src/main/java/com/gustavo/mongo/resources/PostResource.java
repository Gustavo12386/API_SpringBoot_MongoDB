package com.gustavo.mongo.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.gustavo.mongo.domain.Post;
import com.gustavo.mongo.resources.util.URL;
import com.gustavo.mongo.services.PostService;


@RestController
@RequestMapping(value = "/posts")
public class PostResource {

	@Autowired
	private PostService service;	
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<Post> findPosts(@PathVariable String id){
		Post obj = service.findById(id).get();
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(value="/titlesearch", method = RequestMethod.GET)
	public ResponseEntity<List<Post>> findbyTitle(@RequestParam(value="text", defaultValue="") String text){
		text = URL.decodeParam(text);
		List<Post> list = service.findByTitle(text);
		return ResponseEntity.ok().body(list);
	}
}
