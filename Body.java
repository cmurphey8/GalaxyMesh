/**********************************************************************************
 *
 *  GOAL: complete all methods below!
 *              
 **********************************************************************************/
import java.util.Scanner;

public class Body {
    public final double SCALE = 0.042;  // scalar for star size

    protected double rx;        // x position
    protected double ry;        // y position

    protected double mass;      // mass
    protected double size;      // size

    protected double maxSize;   // max size any object can be - use for Nova selector
    protected boolean removed = false;    // removed status

    private double vx;          // x velocity
    private double vy;          // y velocity

    private String image;       // png image
    
    private double fx;          // x force
    private double fy;          // y force

/**********************************************************************************
 *  Constructors        
 **********************************************************************************/

    // create and init a new object with input parameters scanned from a .txt file
    public Body(Scanner scan, double R) {
        rx    = scan.nextDouble();
        ry    = scan.nextDouble();
        vx    = scan.nextDouble();
        vy    = scan.nextDouble();
        mass  = scan.nextDouble();
        size  = Math.random() * SCALE * R;
        image = scan.next();
        if (image.equals("blackhole.gif")) {
            size = SCALE * R;
        }
        maxSize = SCALE * R;
    }

    // create and (re)init a new object with inputs gathered from a Nova'd Star
    public Body(double[] arr, double R) {
        rx    = arr[0];
        ry    = arr[1];
        vx    = arr[2];
        vy    = arr[3];
        mass  = arr[4];
        fx    = arr[5];
        fy    = arr[6];
        size  = SCALE * R;
        maxSize = SCALE * R;
    }

/**********************************************************************************
 *  Modifiers      
 **********************************************************************************/

    // set fx & fy to 0
    public void zeroF() {
        fx = 0;
        fy = 0;
    }

    // update fx && fy with the additive gravitational force
    public void updateF(double dx, double dy, double other_mass, double G) {
        double rad = Math.sqrt(dx * dx + dy * dy);
        double Force = 0;
        if (rad > 0) { 
            Force = G * mass * other_mass / (rad * rad); 
            fx += Force * dx / rad;
            fy += Force * dy / rad;    
        }
    }      
    
    // update fx && fy with the additive gravitational force
    public void updateF(Body obj, double G) {
        double dx = obj.rx - rx;
        double dy = obj.ry - ry;
        double rad = Math.sqrt(dx * dx + dy * dy);
        double Force = 0;
        if (rad > 0) { 
            Force = G * mass * obj.mass / (rad * rad); 
            fx += Force * dx / rad;
            fy += Force * dy / rad;    
        }
    }   

    // update this object's position and velocity
    public void step(double dt) {
        vx += dt * fx / mass;
        vy += dt * fy / mass;
        rx += dt * vx;
        ry += dt * vy;
    }

    // update image
    public void newImage(String imageName) {
        image = imageName;
    }

    // update size
    public void newSize(double multiplier) {
        size *= multiplier;
    }

/**********************************************************************************
 *  Accessors      
 **********************************************************************************/

    // return an array with all necessary parameters to reinit as a new celestial object
    public double[] params() {
        double[] arr = {rx, ry, vx, vy, mass, fx, fy};
        return arr;
    }

    // return image
    public String getImage() {
        return image;
    }

     // return the mass of this object
     public double getMass() {
        return mass;
    }

    // return the rx of this object
    public double getRx() {
        return rx;
    }

    // return the ry of this object
    public double getRy() {
        return ry;
    }

    // return removed status
    public boolean isRemoved() {
        return removed;
    }

    // draw this object using it's rx, ry, and image data
    public void draw() {;
        StdDraw.picture(rx, ry, image, size, size);
    }

}
