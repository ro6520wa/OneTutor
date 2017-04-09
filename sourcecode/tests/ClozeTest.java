/**
 * This is the test class for {@link Cloze}.
 * It includes tests regarding the control and add/remove question/answer methods.
 *
 *@author   Kevin Kosinski
 *@see      Cloze
 */
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;


public class ClozeTest {

    private Cloze cloze;

    @Before
    public void setUp()
    {
        cloze = new Cloze(DataBase.nameExercise, DataBase.questions, DataBase.answersCloze);
    }

    @Test
    public void testControlCCloze ()
    {
        Assert.assertEquals("Result should be 100.0%",100.0, cloze.controlCloze(DataBase.testAnswersTrueCloze),0.0);
        Assert.assertEquals("Result should be 0.0%",0.0, cloze.controlCloze(DataBase.testAnswersFalseCloze),0.0);
        Assert.assertEquals("Result should be 75.0%",75.0, cloze.controlCloze(DataBase.testAnswersOneFalseCloze),0.0);
        Assert.assertEquals("Result should be -1%",-1.0, cloze.controlCloze(DataBase.testAnswersSizeCloze),0.0);
    }

    @Test
    public void testRemoveQuestionAndAnswer ()
    {
        int currSizeQuestions = cloze.getM_questions().size();
        int currSizeAnswers = cloze.getM_correctAnswers().size();

        //Test about the returned value
        Assert.assertEquals(true, cloze.removeQuestionAndAnswer(2));
        Assert.assertEquals(false, cloze.removeQuestionAndAnswer(5));

        //Test to check size of ArrayLists after removing Question and corresponding Answer
        cloze.removeQuestionAndAnswer(1);
        Assert.assertEquals(currSizeQuestions-2, cloze.getM_questions().size());
        Assert.assertEquals(currSizeAnswers-2, cloze.getM_correctAnswers().size());
    }

    @Test
    public void testAddQuestionAndAnswer ()
    {
        int currSizeQuestions = cloze.getM_questions().size();
        int currSizeAnswers = cloze.getM_correctAnswers().size();
        cloze.addQuestionAndAnswer(DataBase.newQuestion, DataBase.newAnswerCloze);
        Assert.assertEquals(currSizeQuestions+1, cloze.getM_questions().size());
        Assert.assertEquals(currSizeAnswers+1, cloze.getM_correctAnswers().size());
    }
}
