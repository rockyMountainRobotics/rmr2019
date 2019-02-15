//added the update functions
//Written by 

public class TapeMathSlope extends Component
{
	public void update()
	{

		int x1 = 9999;
		int y1 = 9999;
		int pos1 = 0;


		int x2 = -9999;
		int y2 = 9999;
		int pos2 = 0;


		int x3 = -9999;
		int y3 = 0;
		int pos3 = 0;
		
		int x4 = 0;
		int y4 = 0;

		double line12;
		double line14;

		double xTopAvg;
		double xBottomAvg;
		double yTopAvg;
		double yBottomAvg;
		double slope;

		//Placeholder turn values
		double turnValue;
		double turnConstant=1;


		//Find the first point

		for(int i = 0; i<7; i+=2){
			if((positions[i+1] < y1) || (positions[i+1] == y1 && positions[i] < x1)){
				x1 = positions[i];
				y1 = positions[i+1];
				pos1 = i;
			}
		}

		//Find the second point
		for(int i = 0; i<7;i+=2){
			if(i!=pos1 && (positions[i] > x2 || (positions[i] == x2 && positions[i+1] < y2))){
				x2 = positions[i];
				y2 = positions[i+1];
				pos2 = i;
			}
		}

		//Find the third point
		for(int i = 0; i < 7; i+=2){
			if((i!=pos1 && i!=pos2) && positions[i] > x3){
				x3 = positions[i];
				y3 = positions[i+1];
				pos3 = i;
			}
		}

		//Find the fourth point
		for(int i = 0; i < 7; i+=2){
			if(i!=pos1 && i!=pos2 && i!=pos3){
				x4=positions[i];
				y4=positions[i+1];
			}
		}

		//Calculating inital avgeraged points
		if(y4<y2) //If true y4 and y1 are top points if false y1 and y2 are top points
		{
			xTopAvg=(x1+x4)/2;
			yTopAvg=(y1+y4)/2;
			xBottomAvg=(x3+x2)/2;
			yBottomAvg=(y3+y2)/2;
			turnConstant=turnConstant*-1;
		}
		else
		{
			xTopAvg=(x1+x2)/2;
			yTopAvg=(y1+y2)/2;
			xBottomAvg=(x3+x4)/2;
			yBottomAvg=(y3+y4)/2;
		}


		//Loop to turn until correct
		while(xtopAvg-xBottomAvg!=0)
		{
			//Get slope
			slope=(yTopAvg-yBottomAvg)/(xTopAvg-xBottomAvg);

			//Test Output
			System.out.print(slope);

			//The robot will turn an amount inversly proportional to the slope.
			if(slope!=0)
			{
				turnValue=turnConstant/slope;
			}
			else
			{
				//Plan if rectangle is perfecly horizontal
			}


			//Call functions to turn robot and functions to get new camera data here


			//Updating the avg points
			if(y4<y2) //If true y4 and y1 are top points if false y1 and y2 are top points
			{
				xTopAvg=(x1+x4)/2;
				yTopAvg=(y1+y4)/2;
				xBottomAvg=(x3+x2)/2;
				yBottomAvg=(y3+y2)/2;
				turnConstant=turnConstant*-1;
			}
			else
			{
				xTopAvg=(x1+x2)/2;
				yTopAvg=(y1+y2)/2;
				xBottomAvg=(x3+x4)/2;
				yBottomAvg=(y3+y4)/2;
			}
		}
	}
	
	public void autoUpdate()
	{
		update();
	}
	
	public void disable()
	{
		//disable someone else do it
	}
}		



