package tP2;

import java.awt.Color;

import repast.simphony.valueLayer.ValueLayer;
import repast.simphony.visualizationOGL2D.ValueLayerStyleOGL;

public class GrassStateStyle implements ValueLayerStyleOGL{

	protected ValueLayer layer;
	
	
	@Override
	public Color getColor(double... coordinates) {
		double valueLayer=layer.get(coordinates);
		if(valueLayer==1){//Grass is alive
			return Color.GREEN;
		}else{//Grass is dead
			return Color.YELLOW;
		}
	}

	@Override
	public float getCellSize() {
		// TODO Auto-generated method stub
		return 4.0f;
	}

	@Override
	public void init(ValueLayer layer) {
		this.layer=layer;
		
		
	}

}
