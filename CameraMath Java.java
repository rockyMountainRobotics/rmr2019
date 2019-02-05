//This code was written by Nathan Jensen
//to calculate the angles for the camera data

//This code is mostly untested.

import math.sqrt;
import math.arctan;

public class CameraMath_Java{
    public static void main(String[] args){
        CameraMath math = new CameraMath(8, 10); //the first number is the height of the camera in inches and the second number is the error bars for the calculations. Higher = more relaxed standards.
        System.out.println(math.math([/*replace this with test points*/]));
    }
}

public class CameraMath extends Component{
    //the amount of pixels in 1 inch
    int inch;
    //the amount of pixels dists can be off
    int error;

    //constructor - one arg - height of camera off the ground
    public CameraMath(double height, int error){
        //this sets inch in to the correct number
        inch = 2000 / (height * (5.039 / 6));

        //this sets the error to the right error
        this.error = error;
    }

    //this is the main math function
    public math(int[8] points){
        //find the highest point
        highest_index = 1;
        for(int i = 1; i < 8; i += 1){
            if(points[i] > points[highest_index]){
                highest_index = i;
            }
        }
        index--;

        //get the distances from that point
        int xa = (points[(highest_index - 2) % 4] - points[(highest_index)]);
        int ya = (points[(highest_index - 1) % 4] - points[(highest_index + 1)]);
        int dist_a = sqrt(xa * xa + ya * ya);
        int xb = (points[(highest_index + 2) % 4] - points[(highest_index)]);
        int yb = (points[(highest_index + 3) % 4] - points[(highest_index) + 1]);
        int dist_b = sqrt(xb * xb + yb * yb);

        //figure out which direction it will go
        if(dist_a == dist_b){
            return 0;
        } else if(2 * inch - error < dist_a && dist_a < 2 * inch + error){
            return arctan(ya / xa);
        } else if(2 * inch - error < dist_b && dist_b < 2 * inch + error){
            return arctan(yb / xb);
        } else {
            //throw an exception if the input is bad
            throw new IllegalArgumentException("Error in CameraMath Java.java: You wrote the code above this error thrower badly. Try fixing it until the code stops erring.");
            
        }

    }

    //this is for tick updates
    @Override
    public Update(){

    }
}