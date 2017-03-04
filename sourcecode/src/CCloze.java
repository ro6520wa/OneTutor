/**
 * This class inherits from {@link CExercise} and represents a specific form of an exercise within a course.
 *
 * @author  Kevin Kosinski
 * @see     CExercise
 */

import java.util.ArrayList;

public class CCloze extends CExercise
{

    /**************
     *CONSTRUCTOR
     *************/
    public CCloze (String _name, ArrayList<String> _questions, ArrayList<String> _answers)
    {
        super(_name,_questions);
        this.m_correctAnswers = _answers;
    }

    /**************
     *ATTRIBUTES
     *************/
    private ArrayList<String> m_correctAnswers;

    /**************
     *METHODS
     *************/
    public ArrayList<String> getM_correctAnswers() {
        return m_correctAnswers;
    }

    public void setM_correctAnswers(ArrayList<String> _correctAnswers) {
        this.m_correctAnswers = _correctAnswers;
    }


    /**
     * Returns the result in percentage based on the given answers.
     * If the size of the given answers is not equal to the correct answers it returns the invalid value -1.
     *
     * @param   _answers    Array of strings which is compared with the correct answers
     * @return              Result represented by a float value
     */
    public float controlCloze(ArrayList<String> _answers)
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

    /**
     * Returns true or false, depending on whether the question and corresponding answer were removed successful.
     * In case the given index is out of bounds it returns false.
     *
     * @param   _index  integer which specifies the question/answer to be deleted
     * @return          true if questions/answer are removed successful, else false
     */
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
