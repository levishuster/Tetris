package Tetronimoes;

import java.awt.Color;
import java.util.Vector;

public class TBlock extends Tetronimo{
	
	TBlock(){
		/*
		 * Set up configurations
		 */
		
		/*
		 * How a piece is positioned
		 * 
		 * 0,0  0,1  0,2
		 * 1,0  1,1  1,2
		 * 2,0  2,1  2,2
		 * 
		 * Note, some later tetronimos will need
		 * a 4 by 4 representation
		 */
		Left= new Vector<Position>();
		Left.add(new Position(2,1));
		Left.add(new Position(1,1));
		Left.add(new Position(1,0));
		Left.add(new Position(0,1));
		
		Under = new Vector<Position>();
		Under.add(new Position(2,1));
		Under.add(new Position(1,1));
		Under.add(new Position(1,0));
		Under.add(new Position(1,2));
		
		Right= new Vector<Position>();
		Right.add(new Position(2,1));
		Right.add(new Position(1,1));
		Right.add(new Position(0,1));
		Right.add(new Position(1,2));
		
		Normal = new Vector<Position>();
		Normal.add(new Position(1,0));
		Normal.add(new Position(1,1));
		Normal.add(new Position(0,1));
		Normal.add(new Position(1,2));
		
		/*
		 * Tetronimo starts out in normal configuration
		 */
		normal();
		
		color = Color.GRAY;
		
	}
	
	@Override
	public Vector<Position> getCurrentPos() {
		// TODO Auto-generated method stub
		return Current;
	}
	
	@Override
	public Position getMaxExtent() {
		// TODO Auto-generated method stub
		return new Position(maxLower, maxRight);
	}
	

	@Override
	public void rotate() {
		if( Current == Normal)
			right();
		else if( Current == Right)
			under();
		else if( Current == Under)
			left();
		else if( Current == Left)
			normal();
	}

	@Override
	void normal() {
		Current = Normal;
		maxLower = 1;
		maxRight = 2;
		
		startRow = 0;
		startCol = 0;
		
	}

	
	@Override
	void right() {
		Current = Right;
		maxLower = 2;
		maxRight = 2;
		
		startRow = 0;
		startCol = 1;
	}

	@Override
	void under() {
		// TODO Auto-generated method stub
		Current = Under;
		maxLower = 2;
		maxRight = 2;
		
		startRow = 1;
		startCol = 0;
	}

	@Override
	void left() {
		// TODO Auto-generated method stub
		Current = Left;
		maxLower = 2;
		maxRight = 1;
		
		startRow = 0;
		startCol = 0;
		
	}

	@Override
	public Position getStartPos() {
		// TODO Auto-generated method stub
		return new Position(startRow, startCol);
	}

	
	
}

