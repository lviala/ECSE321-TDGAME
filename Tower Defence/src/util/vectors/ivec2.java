package util.vectors;

/**
 * Created by Francis O'Brien - 1/7/2015 - 2:06 AM
 *
 * Basic vector functionality for 2d integer vectors
 *
 * Be careful when assigning a vector to another (i.e vec1 = vec 2)
 * Some portions of the code depend on shallow copies and use these vectors as pointers (see Box class) so that the values within both instances change together
 * Assigning a vector that is being "pointed" to to another vector, will break the link
 */

public class ivec2 {

    public int x, y;

    /// Default constructor initializes values to zero
    public ivec2(){
        x = 0;
        y = 0;
    }

    /// Parameter constructor
    public ivec2(int x, int y){
        this.x = x;
        this.y = y;
    }

    /// Vector constructor
    public ivec2(ivec2 vec){
        x = vec.x;
        y = vec.y;
    }

    /// Returns the length of the vector
    public double length() {
        return Math.sqrt((x*x) + (y*y));
    }

    /// Returns angle in radians (range: -Pi to Pi)
    public double angle() {
        if (x > 0){
                return Math.atan(y / x);
        } else{
            return Math.atan(y / x) + (y * Math.PI);

        }
    }

    /// Return sum of two vectors
    public ivec2 add(ivec2 vec){
        return new ivec2(x + vec.x, y + vec.y);
    }

    /// Add the value of a vector to this instance
    public void addSelf(ivec2 vec){
        x += vec.x;
        y += vec.y;
    }
}
