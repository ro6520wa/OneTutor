/**
 * This class represents a license key which is required to validate the access to a course.
 * Basically it is a 6 digit integer value.
 *
 * @author  Kevin Kosinski
 * @see     java.util.Random
 */
import java.util.Random;
public class CLicense {

    /**
     * Class constructor that generates a random 6 digit integer value.
     *
     * @return  Instance of this class
     */
    public CLicense ()
    {
        this.m_key = m_rand.nextInt(1000000);
    };

    /**************
     *ATTRIBUTES
     *************/
    private int m_key;
    Random m_rand = new Random();

    /**************
     *METHODS
     *************/
    public int getM_key() {
        return m_key;
    }

    public boolean validateKey(int _key)
    {
        boolean result;
        return result = (_key == this.m_key) ? true : false;
    }
}
