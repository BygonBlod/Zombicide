package com.example.games.Model.Game;


import static java.lang.System.out;

import com.example.games.Model.Game.Morpion.StateMorpion;

public class GameMorpion extends Game {
	StateMorpion[][] tableau ;

    public GameMorpion() {
        super( 0, 2, 2 );
        initializeGame();
    }

    @Override
    protected void initializeGame() {
    	tableau = new StateMorpion[3][3];
        for(int i =0;i<3;i++) {
        	for(int j=0;j<3;j++) {
        		tableau[i][j]=StateMorpion.Vide;
        	}
        }
    }

    @Override
    public void gameOver() {
        out.println("fin du jeu");
        //this.thread.interrupt();
    }

    @Override
    protected boolean gameContinue() {
        return !verificationLigne() && !verificationColonne() && !verificationDiagonale();
    }

    boolean verificationDiagonale() {
    	StateMorpion state = this.playerIndex==0 ? StateMorpion.Croix:StateMorpion.Rond; 
		return (tableau[0][0]==state && tableau[1][1]==state && tableau[2][2]==state)||
				(tableau[0][2]==state && tableau[1][1]==state && tableau[2][0]==state );
	}

	boolean verificationColonne() {
		StateMorpion state = this.playerIndex==0 ? StateMorpion.Croix:StateMorpion.Rond;
		for(int i=0;i<3;i++) {
			int nbState=0;
			for(int j=0;j<3;j++) {
				if(tableau[j][i]==state) nbState++;
			}
			if(nbState==3) return true;
		}
		return false;
	}

	boolean verificationLigne() {
		StateMorpion state = this.playerIndex==0 ? StateMorpion.Croix:StateMorpion.Rond;
		for(int i=0;i<3;i++) {
			int nbState=0;
			for(int j=0;j<3;j++) {
				if(tableau[i][j]==state) nbState++;
			}
			if(nbState==3) return true;
		}
		return false;
	}

	@Override
    protected void takeTurn() {
        out.println("nb tour:"+turn+" au joueur num "+(this.playerIndex+1)+" de jouer");
        out.println(this.toString());
    }

    @Override
    public String getEnd() {
        return null;
    }
    
    @Override
    public String toString() {
    	String res="";
    	for(int i =0;i<3;i++) {
        	for(int j=0;j<3;j++) {
        		res+=tableau[i][j]+" ";
        	}
        	res+="\n";
        }
    	return res;
    }
    
    public boolean checkMove(int x, int y) {
    	if(x<3 && x>=0 && y<3 && y>=0) {
    		return tableau[x][y]==StateMorpion.Vide;
    	}
		return false;
    }
    
    public boolean move(int x, int y, long id) {
    	if(checkMove(x,y)) {
    		int index =getPlayerIndex(id);
    		if(index==0) {
    			tableau[x][y]=StateMorpion.Croix;
    			this.play();
    			return true;
    		}else if(index==1){
    			tableau[x][y]=StateMorpion.Rond;
    			this.play();
    			return true;
    		}
    		return false;
    	}
    	return false;
    }

	private int getPlayerIndex(long id) {
		int res=-1;
		for(int i=0;i<players.size();i++) {
			Player p = players.get(i);
			if(p.getId()==id) {
				res = i;
			}
		}
		return res;
	}

}
