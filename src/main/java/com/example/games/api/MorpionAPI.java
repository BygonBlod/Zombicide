package com.example.games.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.games.Model.Request.PlayMorpionRequest;
import com.example.games.Services.Morpion.ServiceMorpion;

import jakarta.annotation.PostConstruct;

@RestController
@RequestMapping("morpion")
public class MorpionAPI {
	private ServiceMorpion serviceMor = new ServiceMorpion();
	
	@GetMapping("/connect/{name}")
	public long connectGame(@PathVariable String name) {
		return serviceMor.playerExist(name);
	}
	
	@PostMapping(value="/play",consumes = "application/json")
	public String play(@RequestBody PlayMorpionRequest request) {
		try {
			System.out.println("play "+serviceMor.playerTurn(request.getId(),request.getX(),request.getY()));
			return serviceMor.getPlateau();
		} catch (Exception e) {
			return "erreur serveur";
		}
	}
	
	@GetMapping("/plate")
	public String showPlate() {
		try {
			return serviceMor.getPlateau();
		} catch (Exception e) {
			return "erreur serveur";
		}
	}

}
