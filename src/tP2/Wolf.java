package tP2;

import java.util.ArrayList;
import java.util.List;

import repast.simphony.context.Context;
import repast.simphony.engine.environment.RunEnvironment;
import repast.simphony.parameter.Parameters;
import repast.simphony.space.grid.Grid;
import repast.simphony.space.grid.GridPoint;
import repast.simphony.util.ContextUtils;

public class Wolf extends Animal {

	public Wolf(Grid<Agent> grid) {
		super(grid);
		// Get the duration of grass revival from the user parameters
		Parameters p = RunEnvironment.getInstance().getParameters();
		this.BREED_THRESHOLD=(int)p.getValue("wolfThreshold");
		this.energy=(int)p.getValue("defaultWolfEnergy");
	}
	
	
	@Override
	public void eat(){
		GridPoint gpt=grid.getLocation(this);
		Context<Object> context=ContextUtils.getContext(this);
		
		// Get the wolf gain from food from the user parameters
		Parameters p = RunEnvironment.getInstance().getParameters();
		int gain = (int)p.getValue("wolfGainFromFood");
		
		// A wolf only eat alive sheeps
		//List<Sheep> sheepsToEat=new ArrayList<Sheep>();
		Sheep sheepToEat = null;
		if(gpt!=null){
			for(Object o:grid.getObjectsAt(gpt.getX(),gpt.getY())){
				if(o instanceof Sheep){
					/*try {
					
						//((Sheep) o).die();
					} catch (KillOfAnAlreadyDeadAgent e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					energy+=gain;
				}*/
				
				//sheepsToEat.add((Sheep)o);
				sheepToEat=(Sheep)o;
			}
			}
		}
		
		if(sheepToEat!=null){
			try {
				System.out.println("loup mange mouton à " +gpt.getX()+","+gpt.getY());
				sheepToEat.die();
				energy+=gain;
			} catch (KillOfAnAlreadyDeadAgent e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}


}

