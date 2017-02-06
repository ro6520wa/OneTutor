/**
 * Created by kevin on 23.12.2016.
 */

import java.util.ArrayList;

    public class CMultipleChoice extends CExercise
{
    //additional attribute
    private ArrayList<Boolean> m_correctAnswers;

    //constructor
    public CMultipleChoice (String name, ArrayList<String> questions, ArrayList<Boolean> answers)
    {
        super(name,questions);
        this.m_correctAnswers = answers;
    }

    //methods
    public ArrayList<Boolean> getM_correctAnswers() {
        return m_correctAnswers;
    }

    public void setM_correctAnswers(ArrayList<Boolean> _correctAnswers) {
        this.m_correctAnswers = _correctAnswers;
    }

    public int controlMultipleChoice(ArrayList<Boolean> _answers)
    {
        if (_answers.size()!= this.m_correctAnswers.size())
        {
            return -1;
        } else
        {
            int correct = 0;
            for (int i = 0; i < this.m_correctAnswers.size(); i++)
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
        if (((this.m_questions.size() - listIndex) < 0) || (listIndex < 0))
        {
            return result;
        }
        this.m_questions.remove(listIndex);
        this.m_correctAnswers.remove(listIndex);
        return result = true;
    }

    public void addQuestionAndAnswer (String _question, boolean _answer)
    {
        this.m_correctAnswers.add(_answer);
        this.m_questions.add(_question);
    }
}
