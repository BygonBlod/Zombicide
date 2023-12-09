package com.example.games.api;

import java.security.SecureRandom;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.games.Model.Game.Player;
import com.example.games.Services.Util.PlayerList;

@RestController
@RequestMapping("connect")
public class ConnectAPI {
	
	@GetMapping("/{name}")
	public long getId(@PathVariable String name) {
		if(!PlayerList.exist(name)) {
			return PlayerList.newPlayer(name);
		}else {
			return PlayerList.getId(name);
		}
	}
	
	
	
	
	

}
