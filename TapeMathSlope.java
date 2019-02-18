//added the update functions
//Written by 

public class TapeMathSlope extends Component
{

	public String dir1 = "";
	//camera init
  	SerialPort cam;

  	//Creates an int to hold the number of times the x or y values exceed their limit for whatever reason.
  	int exceedsCap = 0;
  	int totalNums = 0;
  	int largestX = 0;
  	int largestY = 0;
  	//UsbCamera jevois = CameraServer.getInstance().startAutomaticCapture();

  	@Override
  	public void robotInit() {
    		cam = new SerialPort(115200, SerialPort.Port.kUSB);
  	}
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
		
		/*System.out.println("pinging Jevois");
  		String cmd = "ping";
    		int bytes = cam.writeString(cmd + "\n");
    		System.out.println("wrote  "+ bytes + "/" + cmd.length() + "bytes");*/
    		//try to read the string
      		try{
        		if (cam.getBytesReceived() > 0)
        		{
          		//This just reads the data from the camera and puts it into a string.
          		String cameraInput = cam.readString();
		
          		//Outputs the string we just made.
          		//System.out.println(cameraInput);
		
          		//Splits the camera data because sometimes it gives us two inputs shoved together
          		String [] output = cameraInput.split(" ");
		
          		//Gets rid of invisible characters in the string
          		output[output.length - 1] = output[output.length - 1].replaceAll("[^0-9-]", "");
		          
          		//Creates an array to hold the 4 x and 4 y values
          		int [] points = new int[8]; 
          		int j = 0; 
          		for(int i = output.length-8; i < output.length; i++){
              			//puts all STRING points into an INT array so we can use them properly. 
              			points[j] = Integer.valueOf(output[i]);   
              			j++;  
          		}

          		//Prints out all the int values in the array.
          		for(int i = 0; i < points.length; i++){
            			System.out.print(points[i] + ", ");
				totalNums++;
			}
		}
		catch (Exception e) {
       			//if it fails, print an error message
        		System.out.println("Error: " + e);
      		}
		
		System.out.println("Total Values:" + totalNums);


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
		
		//output the array

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

		//Get inverse slope -- this is more usefull because straight has a slope of 0, not undifined
		if(yTopAvg - yBottomAvg != 0){
			slope=(xTopAvg-xBottomAvg)/(yTopAvg-yBottomAvg);
		} else {
			//if it is 90 degrees off, direction does not matter that much
			//because whoever is driving it has done a bad
			dir = "LEFT";
		}
		if(slope > 4 || slope < -4) //if the slope is not straight...
		{
			System.out.println("Not Straight");
			
			//tell the robot to move towards slope 0
			if(slope < 0){
				dir1 = "LEFT"
			} else if(slope > 0){
				dir1 = "RIGHT"
			}
			
			//I hope this code works vvv
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
		} else {
			//make sure that the robot is centered on the line
			if(xTopAvg > 10){
				dir1 = "LEFT";
			} else if (xTopAvg < -10){
				dir1 = "RIGHT";
			} else {
				dir1 = "STRAIGHT";
			}
			
			
			//this is the code from above
			//I hope this code works vvv
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
		System.out.println( "If you are reading this, that is because the robot's sensors have detected that the world will end in 30 minutes. The only thing that you can do to prevent this is to find the sword of Martin the Warrior and use it to kill Voldemort , who you can find in Camelot.");
	}
}		



