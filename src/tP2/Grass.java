package tP2;

import repast.simphony.context.Context;
import repast.simphony.engine.environment.RunEnvironment;
import repast.simphony.parameter.Parameters;
import repast.simphony.space.grid.Grid;
import repast.simphony.space.grid.GridPoint;
import repast.simphony.util.ContextUtils;

public class Grass extends Agent {

	protected int remainingTimeToRevive;
	private static int REVIVE_DURATION;
	public Grass (Grid<Agent>grid, boolean alive){
		super(grid);
		this.alive=alive;
		
			// Get the duration of grass revival from the user parameters
			Parameters p = RunEnvironment.getInstance().getParameters();
			REVIVE_DURATION=(int)p.getValue("grassReviveDuration");
			if(!alive){
				remainingTimeToRevive=REVIVE_DURATION;
			}else{
				remainingTimeToRevive=0;
			}
	}
	
	public int getRemainingTimeToRevive() {
		return remainingTimeToRevive;
	}
	public void setRemainingTimeToRevive(int remainingTimeToRevive) {
		this.remainingTimeToRevive = remainingTimeToRevive;
	}
	
	@Override
	public void implement() {
		if(!alive){
			if(remainingTimeToRevive==0){
				try {
					System.out.println("herbe revit!");
					revive();
				} catch (RevivalOfAnAliveAgentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (TooEarlyRevivalException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else {
				remainingTimeToRevive--;
			}
			
		}
	}
	@Override
	public void die() throws KillOfAnAlreadyDeadAgent {
		GridPoint gpt=grid.getLocation(this);
		Context<Object> context=ContextUtils.getContext(this);
		System.out.println("herbe tuée  à "+gpt.getX()+","+gpt.getY());
		if(!alive) throw new KillOfAnAlreadyDeadAgent();
		setAlive(false);
		setRemainingTimeToRevive(REVIVE_DURATION);
		
		
		context.remove(this);
		
		
	}
	
	public void revive() throws RevivalOfAnAliveAgentException, TooEarlyRevivalException {
		if(alive) throw new RevivalOfAnAliveAgentException();
		if(remainingTimeToRevive>0) throw new TooEarlyRevivalException();	
		
		setAlive(true);
		GridPoint gpt=grid.getLocation(this);
		Context<Object> context=ContextUtils.getContext(this);
		System.out.println("herbe  revit à "+gpt.getX()+","+gpt.getY());
		context.add(this);
		
		
	}
	
	

}
