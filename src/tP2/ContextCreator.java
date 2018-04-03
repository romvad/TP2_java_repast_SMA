package tP2;


import repast.simphony.context.Context;
import repast.simphony.context.space.continuous.ContinuousSpaceFactoryFinder;
import repast.simphony.context.space.grid.GridFactory;
import repast.simphony.context.space.grid.GridFactoryFinder;
import repast.simphony.dataLoader.ContextBuilder;
import repast.simphony.engine.environment.RunEnvironment;
import repast.simphony.space.continuous.RandomCartesianAdder;
import repast.simphony.space.grid.Grid;
import repast.simphony.space.grid.GridBuilderParameters;
import repast.simphony.space.grid.SimpleGridAdder;
import repast.simphony.space.grid.WrapAroundBorders;
import repast.simphony.valueLayer.GridValueLayer;

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
		
		//Creation continous space to make grassstyle correctly appear
		ContinuousSpaceFactoryFinder.createContinuousSpaceFactory(null)
		.createContinuousSpace("Continuous Space", context, new RandomCartesianAdder<Agent>(),
				new repast.simphony.space.continuous.WrapAroundBorders(), width, height, 1);
		//Value layer creation for Gras that can alternatively have 2 states: DEAD and ALIVE
		GridValueLayer vl = new GridValueLayer("Grass Field", true, 
				new repast.simphony.space.grid.WrapAroundBorders(),width,height);
		
		context.addValueLayer(vl);
		
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
					Agent a=b ? new Grass(context,grid,true,x,y):new Grass(context,grid,false,x,y);
					//context.add(a);
					//grid.moveTo(a, x,y);
				}
				
				
			}
		}
		
		//	CONTEXT FOR DEBUG x=3 y=3
		/*Agent a1=new Sheep(grid);
		Agent a2=new Wolf(grid);
		Agent a3=new Grass(context,grid,true,0,0);
		Agent a4=new Grass(context,grid,true,1,1);
		Agent a5=new Grass(context,grid,true,0,2);
		Agent a6=new Grass(context,grid,true,2,1);
		Agent a7=new Grass(context,grid,false,1,0);
		Agent a8=new Grass(context,grid,false,0,1);
		Agent a9=new Grass(context,grid,true,2,0);
		
		context.add(a1);
		grid.moveTo(a1,2,2);
		context.add(a2);
		grid.moveTo(a2,1,2);
		//context.add(a3);
		//grid.moveTo(a3,0,0);
		//context.add(a4);
		//grid.moveTo(a4,1,1);
		//context.add(a5);
		//grid.moveTo(a5,0,2);
		//context.add(a6);
		//grid.moveTo(a6,2,1);
		//context.add(a7);
		//grid.moveTo(a7,1,0);
		//context.add(a8);
		//grid.moveTo(a8,0,1);
		//context.add(a9);
		//grid.moveTo(a9,2,0);*/
		
		
		
		return context;
	}

}
