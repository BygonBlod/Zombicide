package com.example.games.Services.Morpion;

import com.example.games.Model.Game.GameMorpion;
import com.example.games.Model.Game.Player;
import com.example.games.Services.Util.PlayerList;

public class ServiceMorpion {
	 private GameMorpion game;
	 
	 public ServiceMorpion() {
		game = new GameMorpion();
	}
	 
	 public boolean playerTurn(long id, int x, int y) {
		 if(game.canPlay() && id == game.getPlayers().get(game.getPlayerIndex()).getId()) {
			  return game.move(x, y, id);
		 }
		 return false;
	 }
	 
	 public String getPlateau() {
		 return game.toString();
	 }
	 
	 public long playerExist(String name) {
		 boolean exist = false;
		 long res = -1;
		 for(Player p: game.getPlayers()) {
			 if(p.getName().equals(name)) {
				 exist = true;
				 res = p.getId();
			 }
		 }
		 if(!exist) {
			 Player player = PlayerList.getPlayer(name);
			 if(player!=null) {
				 res = player.getId();
				 game.getPlayers().add(player);
			 }else {
				 PlayerList.newPlayer(name);
				 res = PlayerList.getPlayer(name).getId();
				 game.getPlayers().add(PlayerList.getPlayer(name));
			 }
			 return res;
		 }else {
			 return res;
		 }
	 }

}
