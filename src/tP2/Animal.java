package tP2;

import repast.simphony.context.Context;
import repast.simphony.space.grid.Grid;
import repast.simphony.space.grid.GridPoint;
import repast.simphony.util.ContextUtils;

public class Animal extends Agent {
	
	public Animal(Grid<Agent> grid) {
		super(grid);
	}

	protected int energy;
	protected int BREED_THRESHOLD=1000;
	
	public int getEnergy() {
		return energy;
	}
	public void setEnergy(int energy) {
		this.energy = energy;
	}
	public int getBreedThreshold() {
		return BREED_THRESHOLD;
	}
	
	@Override
	public void implement() {
		move();
		eat();
		breed();
	}
	
	public void move(){
		GridPoint gpt=grid.getLocation(this);
		Context<Object> context=ContextUtils.getContext(this);
		
		int abscisse= gpt.getX();
		int ordonnee=gpt.getY();
		int horizontalMove;
		int ordonneeMove;
		int width=grid.getDimensions().getWidth();
		int height=grid.getDimensions().getHeight();
		
		if(width-abscisse==0){
			horizontalMove=Math.random()>0.5? -1 : 0;
		} else if (width-abscisse==width){
			horizontalMove=Math.random()>0.5? 1 : 0;
		}else{
			horizontalMove=Math.random()>0.5? -1 : Math.random()>0.5? 0 : 1;
		}
		
		if(height-ordonnee==0){
			ordonneeMove=Math.random()>0.5? -1 : 0;
		}else if (height-ordonnee==height){
			ordonneeMove=Math.random()>0.5? 1 : 0;
		}else{
			ordonneeMove=Math.random()>0.5? -1 : Math.random()>0.5? 0 : 1;
		}
		
		abscisse+=horizontalMove;
		ordonnee+=ordonneeMove;
		
		energy--;
		
		if(energy==0){
			try {
				die();
			} catch (KillOfAnAlreadyDeadAgent e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			context.add(this);
			grid.moveTo(this, abscisse,ordonnee);
			if (this instanceof Sheep){
				System.out.println("sheep bouge vers "+abscisse+","+ordonnee+" et a une energie de "+ energy);
			} else {
				System.out.println("sheep bouge vers "+abscisse+","+ordonnee+" et a une energie de "+ energy);
			}
		}

		//grid.moveTo(this, abscisse,ordonnee);
	};
	//public abstract void kill();
	//public abstract void eatGrass();
	@Override
	public  void die() throws KillOfAnAlreadyDeadAgent {
		
		this.alive=false;
		GridPoint gpt=grid.getLocation(this);
		Context<Object> context=ContextUtils.getContext(this);
		if(this instanceof Sheep){
			System.out.println("mouton (energie: "+energy+") tué à "+gpt.getX()+","+gpt.getY());
		} else{
			System.out.println("loup (energie: "+energy+") tué à "+gpt.getX()+","+gpt.getY());
		}
		context.remove(this);
		
	}
	
	public void breed(){
		if(getEnergy()>=getBreedThreshold()){
			GridPoint gpt=grid.getLocation(this);
			Context<Object> context=ContextUtils.getContext(this);
			
			int newEnergy=getEnergy()/2;
			Animal newAnimal;
			
			if(this instanceof Sheep){
				newAnimal=new Sheep(grid);
				System.out.println("(energie: "+energy+") ->naissance mouton à : "+gpt.getX()+","+gpt.getY() );
			}else{
				newAnimal=new Wolf(grid);
				System.out.println("(energie: "+energy+") ->naissance loup à : "+gpt.getX()+","+gpt.getY() );
			}
			newAnimal.setEnergy(newEnergy);
			setEnergy(newEnergy);
			
			context.add(newAnimal);
			grid.moveTo(newAnimal, gpt.getX(),gpt.getY());
			
			
		}
	
	}
	
	public void eat(){
		
	}
}
