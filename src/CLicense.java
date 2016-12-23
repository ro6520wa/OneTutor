/**
 * Created by kevin on 22.12.2016.
 */
import java.util.Random;
public class CLicense {
    //attributes
    private int m_key;
    Random m_rand = new Random();

    //constructor
    public CLicense ()
    {
        this.m_key = m_rand.nextInt(1000000);
    };

    //methods

    public int getM_key() {
        return m_key;
    }

    public boolean validateKey(int _key)
    {
        boolean result;
        return result = (_key == this.m_key) ? true : false;
    }
}
