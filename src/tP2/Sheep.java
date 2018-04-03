package tP2;

import repast.simphony.context.Context;
import repast.simphony.engine.environment.RunEnvironment;
import repast.simphony.parameter.Parameters;
import repast.simphony.space.grid.Grid;
import repast.simphony.space.grid.GridPoint;
import repast.simphony.util.ContextUtils;

public class Sheep extends Animal{

	public Sheep(Grid<Agent> grid) {
		super(grid);
		
		// Get the threshold of the breedingfrom the user parameters
		Parameters p = RunEnvironment.getInstance().getParameters();
		this.BREED_THRESHOLD=(int)p.getValue("sheepThreshold");
		this.energy=(int)p.getValue("defaultSheepEnergy");
		
	}
	
	@Override
	public void eat(){
		GridPoint gpt=grid.getLocation(this);
		Context<Object> context=ContextUtils.getContext(this);
		
		// Get the sheep gain from food from the user parameters
		Parameters p = RunEnvironment.getInstance().getParameters();
		int gain = (int)p.getValue("sheepGainFromFood");
		
		// A sheep only eat alived grass
		Grass grassToEat=null;
		if(gpt!=null){
			for(Object o:grid.getObjectsAt(gpt.getX(),gpt.getY())){
				if(o instanceof Grass){
					grassToEat=(Grass)o;
				}
			}
		}
		
		if(grassToEat!=null){
			try {
				
				
				if(grassToEat.isAlive()){
					grassToEat.die();
					energy+=gain;
					System.out.println("mouton mange herbe à "+gpt.getX()+","+gpt.getY());
				}
			} catch (KillOfAnAlreadyDeadAgent e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

}
