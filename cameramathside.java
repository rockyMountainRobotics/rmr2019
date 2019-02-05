import math;
public class CameraMath extends Component {
    //angle camera calulation by Johnathan Pollard
    int error = 10;
    int inch = 8;

    public CameraMath(error, height){
        inch = 2(2000/(height *(5.039 / 6)));
    }
    
    public double findconstant(double height){ // calculates the constent based on camera height  
        
    }
    public math (int [8] points){
        int xaxis[4];
        int yaxis[4]; 
        for (int i = 0; i<8; i+=2){
            points[i] = xaxis[i/2]
            points[i+1] = yaxis[i/2]        
        }
        for (int b = 0; b<4; b++){
            for (int a = 0; a<4; a++){
                if ((findconstant - error) =< math.sqrt((xaxis[a] - xaxis[b])^2 + (yaxis[a] - yaxis[b])^2 ) && sqrt((xaxis[a] - xaxis[b])^2 + (yaxis[a] - yaxis[b])^2 ) =< (findconstant + error)){
                  return double angle = math.arctan((xaxis[a] - xaxis[b])/(yaxis[a] - yaxis[b]));
                }    
            } 
        }
    }

}
