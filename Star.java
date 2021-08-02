
/**********************************************************************************
 *
 *  GOAL: Complete the step(...) method too add supernove functionality
 *              
 **********************************************************************************/
import java.util.Scanner;

public class Star extends Body {
    private final double NOVA_RATE = 1.2;
    private final double NOVA_GROWTH = Math.pow(NOVA_RATE, 10);
    
    private boolean startNova = false;
    private int novaCount = 0;

    // create and init a new object with input parameters scanned from a .txt file
    public Star(Scanner scan, double R) {
        super(scan, R);
    }

    // start the supernova process randomly
    public void Nova() {
        if (Math.random() > 0.99995) {
            startNova = true;
        }
    }

    // attempt to supernova after Body step update
    @Override
    public void step(double dt) {
        // call the Body class step method
        super.step(dt);

        // if supernova has started, grow image by scaling factor NOVA_RATE
        if (startNova) {
            if (novaCount == 10) {
                // TODO: select new object type by updating image -- compare to maxSize scaled by NOVA_GROWTH (size at end of Nova)
                // The largest 20% of objects will become Black Holes
                // The next 40% of objects will become Pulsars
                // The remaining 40% (smallest 40% of objects) will become Neutron Stars
                // HINT: check in on static variables in this class AND the Body class that might help us here!
            }
            else {
                newSize(NOVA_RATE);
                novaCount++;
            }
        }
        else {
            Nova();
        }
    }

}
