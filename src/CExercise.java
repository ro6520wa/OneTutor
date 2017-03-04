/**
 * This class is an abstract base class for {@link CCloze} and {@link CMultipleChoice}.
 * It provides all basic attributes and methods to represent an exercise of a course.
 *
 * @author  Kevin Kosinski
 * @see     CCloze
 * @see     CMultipleChoice
 * @see     java.util.Random
 */

import java.util.ArrayList;
import java.util.Random;

public abstract class CExercise
{
    /**************
     *CONSTRUCTOR
     *************/
    protected CExercise(String _name, ArrayList<String> _questions)
    {
        this.m_name = _name;
        this.m_questions = _questions;
        this.m_ID = m_rand.nextInt(1000000);

    }

    /**************
     *ATTRIBUTES
     *************/
    protected String m_name;
    protected ArrayList<String> m_questions;
    protected int m_ID;
    Random m_rand = new Random();

    /**************
     *METHODS
     *************/
    public String getM_name()
    {
        return m_name;
    }

    public ArrayList<String> getM_questions() {
        return m_questions;
    }

    public void setM_questions(ArrayList<String> _questions) {
        this.m_questions = _questions;
    }
    public int getM_ID() {
    return m_ID;
}
}