package tP2;

import repast.simphony.context.Context;
import repast.simphony.engine.environment.RunEnvironment;
import repast.simphony.parameter.Parameters;
import repast.simphony.space.grid.Grid;
import repast.simphony.space.grid.GridPoint;
import repast.simphony.util.ContextUtils;
import repast.simphony.valueLayer.GridValueLayer;

public class Grass extends Agent {

	protected int remainingTimeToRevive;
	private static int REVIVE_DURATION;
	
	GridValueLayer vl;
	
	private static final int ALIVE = 1;
	private static final int DEAD = 0;
	
	public Grass (Context context, Grid<Agent>grid, boolean alive, int abscisse, int ordonnee){
		super(grid);
		this.alive=alive;
		
			// Get the duration of grass revival from the user parameters
			Parameters p = RunEnvironment.getInstance().getParameters();
			REVIVE_DURATION=(int)p.getValue("grassReviveDuration");
			
			//context=ContextUtils.getContext(this);
			context.add(this);
			grid.moveTo(this, abscisse, ordonnee);
			vl = (GridValueLayer)context.getValueLayer("Grass Field");
			if(!alive){
				remainingTimeToRevive=REVIVE_DURATION;
				vl.set(DEAD, grid.getLocation(this).toIntArray(null));
			}else{
				remainingTimeToRevive=0;
				vl.set(ALIVE, grid.getLocation(this).toIntArray(null));
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
		
		vl.set(DEAD, grid.getLocation(this).toIntArray(null));
		//context.remove(this);
		
		
	}
	
	public void revive() throws RevivalOfAnAliveAgentException, TooEarlyRevivalException {
		if(alive) throw new RevivalOfAnAliveAgentException();
		if(remainingTimeToRevive>0) throw new TooEarlyRevivalException();	
		
		setAlive(true);
		GridPoint gpt=grid.getLocation(this);
		Context<Object> context=ContextUtils.getContext(this);
		System.out.println("herbe  revit à "+gpt.getX()+","+gpt.getY());
		//context.add(this);
		vl.set(ALIVE, grid.getLocation(this).toIntArray(null));
		
		
	}
	
	

}
