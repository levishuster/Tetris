package Tetronimoes;

import java.util.Random;

public class TetronimoFactory {
	Random rand;
	
	public TetronimoFactory(){
		rand = new Random(System.currentTimeMillis());
	}
	
	public Tetronimo getNewTetronimo() throws Exception{
		
//		return new ZBlock();  // For testing
		switch (rand.nextInt(8)){
		case 0:
			return new TBlock();
		case 1:
			return new SBlock();
		case 2:
			return new LBlock();
		case 3:
			return new IBlock(); 
		case 4:
			return new OBlock();
		case 5:
			return new JBlock();
		case 6:
			return new ZBlock();
		/*
		 * reset the seed once in a while for more randomness
		 */
		case 7:
			rand = new Random(System.currentTimeMillis());
			return getNewTetronimo();
		}
		
		throw new Exception("Case statement exited without case");
		
	}
}
