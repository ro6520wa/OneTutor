/**
 * This class inherits from {@link CExercise} and represents a specific form of an exercise within a course.
 *
 * @author  Kevin Kosinski
 * @see     CExercise
 */

import java.util.ArrayList;

    public class CMultipleChoice extends CExercise
    {

    /**************
     *CONSTRUCTOR
     *************/
    public CMultipleChoice (String name, ArrayList<String> questions, ArrayList<Boolean> answers)
    {
        super(name,questions);
        this.m_correctAnswers = answers;
    }

    /**************
     *ATTRIBUTES
     *************/
    private ArrayList<Boolean> m_correctAnswers;

    /**************
     *METHODS
     *************/
    public ArrayList<Boolean> getM_correctAnswers() {
        return m_correctAnswers;
    }

    public void setM_correctAnswers(ArrayList<Boolean> _correctAnswers) {
        this.m_correctAnswers = _correctAnswers;
    }

    /**
     * Returns the result in percentage based on the given answers.
     * If the size of the given answers is not equal to the correct answers it returns the invalid value -1.
     *
     * @param   _answers    Array of boolean which is compared with the correct answers
     * @return              Result represented by a float value
     */
    public float controlMultipleChoice(ArrayList<Boolean> _answers)
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
