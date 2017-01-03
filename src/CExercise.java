/**
 * Created by kevin on 21.12.2016.
 */

import java.util.ArrayList;

public abstract class CExercise
{
    //attributes
    protected String m_name;
    protected ArrayList<String> m_questions;

    //constructor
    protected CExercise(String name, ArrayList<String> questions)
    {
        this.m_name = name;
        this.m_questions = questions;
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
}