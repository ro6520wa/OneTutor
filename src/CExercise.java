/**
 * Created by kevin on 21.12.2016.
 */

import java.util.ArrayList;
import java.util.Random;

public abstract class CExercise
{
    //attributes
    protected String m_name;
    protected ArrayList<String> m_questions;
    protected int m_ID;
    Random m_rand = new Random();



    //constructor
    protected CExercise(String _name, ArrayList<String> _questions)
    {
        this.m_name = _name;
        this.m_questions = _questions;
        this.m_ID = m_rand.nextInt(1000000);

    }

    //methods
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