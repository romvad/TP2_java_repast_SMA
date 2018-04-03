package tP2;

import tP2.Agent;
import repast.simphony.engine.schedule.ScheduledMethod;
import repast.simphony.space.grid.Grid;


public abstract class Agent {
	protected boolean alive;
	protected Grid<Agent> grid;
	
	//protected int abscisse;
	//protected int ordonnee;
	
	public Agent(Grid<Agent> grid){
		this.grid=grid;
		this.alive=true;
	}
	
	@ScheduledMethod(start = 1, interval = 1, shuffle=true)
	public void implement(){
		//Overrided by subclasses
	}

	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	public void die() throws KillOfAnAlreadyDeadAgent {
		// TODO Auto-generated method stub
		
	}
	
	

}
