/** A class that represents a path via pursuit curves.
 *  @author You!
 */
public class Path {

    /** What to do, what to do... */
	private Point currPoint;
    private Point nextPoint;

	public Path(double x, double y){
        currPoint = new Point(x, y);
        nextPoint = new Point(x, y);
	}
    public double getCurrX(){return this.currPoint.getX();}
    public double getCurrY(){return this.currPoint.getY();}
    public double getNextX(){return this.nextPoint.getX();}
    public double getNextY(){return this.nextPoint.getY();}
    public void iterate(double dx, double dy){
        currPoint.setX(nextPoint.getX());
        currPoint.setY(nextPoint.getY());
        nextPoint.setX(currPoint.getX()+dx);
        nextPoint.setY(currPoint.getY()+dy);
    }

}
