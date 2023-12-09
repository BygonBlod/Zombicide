package com.example.games.Services.Util;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

import com.example.games.Model.Game.Player;

public class PlayerList {
	public static List<Player> playerList = new ArrayList<>();
	
	public static long newPlayer(String name) {
		if(!exist(name)) {
			long id = getNewId();
			playerList.add(new Player(name,id));
			return id;
		}else {
			return -1;
		}
	}
	
	private static long getNewId() {
		SecureRandom random = new SecureRandom();
		long id = random.nextLong();
		while(existId(id)) {
			id = random.nextLong();
		}
		return id;
	}

	private static boolean existId(long id) {
		for(Player p : playerList) {
			if(p.getId()== id) {
				return true;
			}
		}
		return false;
	}

	public static boolean exist(String name) {
		for(Player p : playerList ) {
			if(p.getName().equals(name)) {
				return true;
			}
		}
		return false;
	}

	public static long getId(String name) {
		for(Player p : playerList ) {
			if(p.getName().equals(name)) {
				return p.getId();
			}
		}
		return -1;
	}

	public static Player getPlayer(String name) {
		for(Player p : playerList ) {
			if(p.getName().equals(name)) {
				return p;
			}
		}
		return null;
	}
}
