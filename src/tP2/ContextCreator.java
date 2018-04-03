package tP2;

import repast.simphony.context.Context;
import repast.simphony.context.space.grid.GridFactory;
import repast.simphony.context.space.grid.GridFactoryFinder;
import repast.simphony.dataLoader.ContextBuilder;
import repast.simphony.engine.environment.RunEnvironment;
import repast.simphony.space.grid.Grid;
import repast.simphony.space.grid.GridBuilderParameters;
import repast.simphony.space.grid.SimpleGridAdder;
import repast.simphony.space.grid.WrapAroundBorders;

public class ContextCreator implements ContextBuilder<Agent> {
	public Context<Agent> build(Context<Agent> context){
		context.setId("TP2");
		int width=RunEnvironment.getInstance().getParameters().getInteger("gridWidth");
		int height=RunEnvironment.getInstance().getParameters().getInteger("gridHeight");
		int grassReviveDuration=RunEnvironment.getInstance().getParameters().getInteger("grassReviveDuration");
		int sheepThreshold=RunEnvironment.getInstance().getParameters().getInteger("sheepThreshold");
		int sheepGainFromFood=RunEnvironment.getInstance().getParameters().getInteger("sheepGainFromFood");
		int wolfGainFromFood=RunEnvironment.getInstance().getParameters().getInteger("wolfGainFromFood");
		int wolfThreshold= RunEnvironment.getInstance().getParameters().getInteger("wolfThreshold");
		
		
		
		GridFactory gridFactory=GridFactoryFinder.createGridFactory(null);
		Grid<Agent> grid = gridFactory.createGrid("grid",context,new GridBuilderParameters<Agent>(new WrapAroundBorders(),new SimpleGridAdder<Agent>(),true,width,height));
		
		for(int x=0;x<width;x++){
			for(int y=0;y<height;y++){
				
				if(Math.random()>0.5){
					//We add an animal
					boolean b=Math.random()>0.5 ? true:false;
					Agent a=b ? new Sheep(grid):new Wolf(grid);
					context.add(a);
					grid.moveTo(a, x,y);
				}else{
					boolean b=Math.random()>0.5 ? true:false;
					Agent a=b ? new Grass(grid,true):new Grass(grid,false);
					context.add(a);
					grid.moveTo(a, x,y);
				}
				
			}
		}
		
		return context;
	}

}
