/**
 * This is the test class for {@link MultipleChoice}.
 * It includes tests regarding the control and add/remove question/answer methods.
 *
 * @author  Kevin Kosinski
 * @see     MultipleChoice
 */


import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

public class MultipleChoiceTest {

    private MultipleChoice multipleChoice;

    @Before
    public void setUp()
    {
        multipleChoice = new MultipleChoice(DataBase.nameExercise, DataBase.questions, DataBase.answersMultipleChoice);
    }

    @Test
    public void testControlMultipleChoice ()
    {
        Assert.assertEquals("Result should be 100.0%",100.0, multipleChoice.controlMultipleChoice(DataBase.testAnswersTrueMultipleChoice),0.0);
        Assert.assertEquals("Result should be 0.0%",0.0, multipleChoice.controlMultipleChoice(DataBase.testAnswersFalseMultipleChoice),0.0);
        Assert.assertEquals("Result should be 75.0%",75.0, multipleChoice.controlMultipleChoice(DataBase.testAnswersOneFalseMultipleChoice),0.0);
        Assert.assertEquals("Result should be -1%",-1.0, multipleChoice.controlMultipleChoice(DataBase.testAnswersSizeMultipleChoice),0.0);
    }

    @Test
    public void testRemoveQuestionAndAnswer ()
    {
        int currSizeQuestions = multipleChoice.getM_questions().size();
        int currSizeAnswers = multipleChoice.getM_correctAnswers().size();

        //Test return value
        Assert.assertEquals(true, multipleChoice.removeQuestionAndAnswer(2));
        Assert.assertEquals(false, multipleChoice.removeQuestionAndAnswer(5));

        //Test size of ArrayLists after removing Question and corresponding Answer
        multipleChoice.removeQuestionAndAnswer(1);
        Assert.assertEquals(currSizeQuestions-2, multipleChoice.getM_questions().size());
        Assert.assertEquals(currSizeAnswers-2, multipleChoice.getM_correctAnswers().size());
    }

    @Test
    public void testAddQuestionAndAnswer ()
    {
        int currSizeQuestions = multipleChoice.getM_questions().size();
        int currSizeAnswers = multipleChoice.getM_correctAnswers().size();
        multipleChoice.addQuestionAndAnswer(DataBase.newQuestion, DataBase.newAnswerMultipleChoice);
        Assert.assertEquals(currSizeQuestions+1, multipleChoice.getM_questions().size());
        Assert.assertEquals(currSizeAnswers+1, multipleChoice.getM_correctAnswers().size());
    }
}
