/**
 * Created by kevin on 23.12.2016.
 */

import java.util.ArrayList;

public class CCloze extends CExercise
{
    //additional attribute
    private ArrayList<String> m_correctAnswers;

    //constructor
    public CCloze (String _name, ArrayList<String> _questions, ArrayList<String> _answers)
    {
        super(_name,_questions);
        this.m_correctAnswers = _answers;
    }

    //methods
    public ArrayList<String> getM_correctAnswers() {
        return m_correctAnswers;
    }

    public void setM_correctAnswers(ArrayList<String> _correctAnswers) {
        this.m_correctAnswers = _correctAnswers;
    }

    public int controlCloze(ArrayList<String> _answers)
    {
        if (this.m_correctAnswers.size() != _answers.size())
        {
            return -1;
        } else
        {
            int correct = 0;
            for (int i = 0; i < this.m_correctAnswers.size() ; i++)
            {
                if (_answers.get(i).equals(this.m_correctAnswers.get(i)))
                {
                    correct++;
                }
            }
            return ((correct*100)/this.m_correctAnswers.size());
        }
    }

    public boolean removeQuestionAndAnswer (int _index)
    {
        boolean result = false;
        int listIndex = _index - 1;
        if ((listIndex < 0) || ((this.m_questions.size() - listIndex) < 0))
        {
            return result;
        }
        this.m_questions.remove(listIndex);
        this.m_correctAnswers.remove(listIndex);
        return result = true;
    }

    public void addQuestionAndAnswer (String _question, String _answer)
    {
        this.m_correctAnswers.add(_answer);
        this.m_questions.add(_question);
    }
}
