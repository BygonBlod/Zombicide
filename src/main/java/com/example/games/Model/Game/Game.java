package com.example.games.Model.Game;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public abstract class Game extends Observable implements Runnable{
    protected int turn;
    protected int maxturn;
    protected boolean haveTurn;
    protected boolean isRunning;
    protected Thread thread;
    protected long sleep = 500;
    protected int nbPlayerMin = 1;
    protected int nbPlayerMax = 1;
    protected List<Player> players;
    protected int playerIndex = 0;

    public Game(int maxTurn, int nbPlayersMin, int nbPlayersMax) {
        this.maxturn = maxTurn;
        if(this.maxturn>0) {
        	this.haveTurn = true;
        }else {
        	this.haveTurn = false;
        }
        this.nbPlayerMin = nbPlayersMin;
        this.nbPlayerMax = nbPlayersMax;
        this.players = new ArrayList<>();
    }

    public void launch() {
        isRunning = true;
        thread = new Thread(this);
        thread.start();
    }

    public void init() {
        turn = 0;
        isRunning = true;
        initializeGame();
    }

    protected abstract void initializeGame();

    public void step() {
        if (maxturn == 0 && haveTurn) {
            isRunning = false;
            gameOver();
        } else {
            turn += 1;
            takeTurn();
            if (!gameContinue() || (turn == maxturn && haveTurn)) {
                isRunning = false;
                gameOver();
            }
            setChanged();
            notifyObservers();
        }
    }

    public void run() {// fonctionne pas car tourne sans s'arreter
    	/*
        while (isRunning == true) {
            step();
            try {
                thread.sleep(sleep);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        thread.stop();
        */
    	while (isRunning == true) {
    		
    	}
    }
    
    public void play() {
    	if(canPlay()) {
    		this.step();
        	this.changeIndex();
    	}
    }
    

    public boolean canPlay() {
    	if(players.size()<=this.nbPlayerMax && players.size()>=this.nbPlayerMin) {
    		return true;
    	}else {
    		return false;
    	}
    }

	protected void changeIndex() {
    	if(this.playerIndex==players.size()-1) {
    		this.playerIndex = 0;
    	}else {
    		this.playerIndex++;
    	}
    }

	public int getMaxturn() {
        return maxturn;
    }

    public void setMaxturn(int maxturn) {
        this.maxturn = maxturn;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }

    public long getSleep() {
        return sleep;
    }

    public void setSleep(long sleep) {
        this.sleep = sleep;
    }

    public void pause() {
        isRunning = false;
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public boolean isHaveTurn() {
		return haveTurn;
	}

	public void setHaveTurn(boolean haveTurn) {
		this.haveTurn = haveTurn;
	}

	public int getNbPlayerMin() {
		return nbPlayerMin;
	}

	public void setNbPlayerMin(int nbPlayerMin) {
		this.nbPlayerMin = nbPlayerMin;
	}

	public int getNbPlayerMax() {
		return nbPlayerMax;
	}

	public void setNbPlayerMax(int nbPlayerMax) {
		this.nbPlayerMax = nbPlayerMax;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	public int getPlayerIndex() {
		return playerIndex;
	}

	public void setPlayerIndex(int playerIndex) {
		this.playerIndex = playerIndex;
	}

	public abstract void gameOver();

    protected abstract boolean gameContinue();

    protected abstract void takeTurn();

    public abstract String getEnd();
}
