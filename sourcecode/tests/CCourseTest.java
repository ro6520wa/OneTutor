/**
 * This is the test class for {@link CCourse}.
 * It includes tests regarding set/control/remove exercises and add/remove material.
 * Before running this class ensure that the test files are located in your home directory.
 * This files can be found under "others".
 *
 * @author  Kevin Kosinski
 * @see     CCloze
 * @see     CCourse
 * @see     CExercise
 * @see     CMaterial
 * @see     CMultipleChoice
 */

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import java.io.IOException;

public class CCourseTest {
    private CCourse course;

    @Before
    public void setUp() throws IOException {
        course = new CCourse(CDataBase.titleCourse,CDataBase.testMemberAdmin, CDataBase.InstitutionID);
        course.setExercise(CDataBase.nameExercise,CDataBase.questions,CDataBase.answersCloze,true, CDataBase.testMemberAdmin);
        course.setExercise(CDataBase.nameExercise,CDataBase.questions,CDataBase.answersMultipleChoice,false, CDataBase.testMemberAdmin);
        course.setMaterial(CDataBase.TestFileName,CDataBase.TestFileType,CDataBase.testMemberAdmin);

    }

    @Test
    public void testSetExercise ()
    {
        int currSizeExercises = course.getM_exercises().size();

        //set Cloze True
        course.setExercise(CDataBase.nameExercise,CDataBase.questions,CDataBase.answersCloze,true, CDataBase.testMemberAdmin);
        Assert.assertEquals(currSizeExercises+1,course.getM_exercises().size());
        Assert.assertTrue("Member is not an administrator", true);

        //set Cloze false -> no permission
        course.setExercise(CDataBase.nameExercise,CDataBase.questions,CDataBase.answersCloze,true, CDataBase.testMemberNoAdmin);
        Assert.assertEquals(currSizeExercises+1,course.getM_exercises().size());
        Assert.assertFalse("Member is an administrator", false);

        //set MultipleChoice True
        currSizeExercises = course.getM_exercises().size();
        course.setExercise(CDataBase.nameExercise,CDataBase.questions,CDataBase.answersMultipleChoice,false, CDataBase.testMemberAdmin);
        Assert.assertEquals(currSizeExercises+1,course.getM_exercises().size());
        Assert.assertTrue("Member is not an administrator", true);

        //set MultipleChoice False
        course.setExercise(CDataBase.nameExercise,CDataBase.questions,CDataBase.answersMultipleChoice,false, CDataBase.testMemberNoAdmin);
        Assert.assertEquals(currSizeExercises+1,course.getM_exercises().size());
        Assert.assertFalse("Member is an administrator", false);
    }

    @Test
    public void testControlExercise() {

        //controlExercise() cloze
        CExercise clozeInCourse = course.getM_exercises().get(0);
        CExercise clozeNotInCourse = new CCloze("NotInCourse",CDataBase.questions,CDataBase.answersCloze);

        Assert.assertEquals("Result should be 100.0%",100.0,course.controlExercise(clozeInCourse, CDataBase.answersCloze),0.0);
        Assert.assertEquals("Result should be 0.0%",0.0,course.controlExercise(clozeInCourse, CDataBase.testAnswersFalseCloze),0.0);
        Assert.assertEquals("Result should be 75.0%",75.0,course.controlExercise(clozeInCourse, CDataBase.testAnswersOneFalseCloze),0.0);
        Assert.assertEquals("Result should be -1",-1.0,course.controlExercise(clozeInCourse,CDataBase.testAnswersSizeCloze),0.0);
        Assert.assertEquals("Result should be -2",-2.0,course.controlExercise(clozeNotInCourse,CDataBase.answersCloze),0.0);

        //controlExercise() multipleChoice
        CExercise multipleChoiceInCourse = course.getM_exercises().get(1);
        CExercise multipleChoiceNotInCourse = new CCloze("NotInCourse",CDataBase.questions,CDataBase.answersCloze);

        Assert.assertEquals("Result should be 100.0%",100.0,course.controlExercise(multipleChoiceInCourse, CDataBase.answersMultipleChoice),0.0);
        Assert.assertEquals("Result should be 0.0%",0.0,course.controlExercise(multipleChoiceInCourse, CDataBase.testAnswersFalseMultipleChoice),0.0);
        Assert.assertEquals("Result should be 75.0%",75.0,course.controlExercise(multipleChoiceInCourse, CDataBase.testAnswersOneFalseMultipleChoice),0.0);
        Assert.assertEquals("Result should be -1",-1.0,course.controlExercise(clozeInCourse,CDataBase.testAnswersSizeMultipleChoice),0.0);
        Assert.assertEquals("Result should be -2",-2.0,course.controlExercise(multipleChoiceNotInCourse,CDataBase.answersCloze),0.0);
    }

    @Test
    public void testRemoveExercise () {

        int currSizeExercises = course.getM_exercises().size();

        //true conditions
        Assert.assertTrue("Exercise not removed!",course.removeExercise(course.getM_exercises().get(0), CDataBase.testMemberAdmin));
        Assert.assertEquals(currSizeExercises-1,course.getM_exercises().size());

        //false conditions
        Assert.assertFalse("Exercise has been removed!", course.removeExercise(course.getM_exercises().get(0), CDataBase.testMemberNoAdmin));
        Assert.assertEquals(currSizeExercises-1,course.getM_exercises().size());

    }

    @Test
    public void testSetMaterial () throws IOException {

        //set Material True
        int currSizeMaterial = course.getM_materials().size();
        Assert.assertTrue("Material hasn`t been set.", course.setMaterial(CDataBase.NewTestFileName,CDataBase.NewTestFileType,CDataBase.testMemberAdmin));
        Assert.assertEquals(currSizeMaterial+1,course.getM_exercises().size());

        //set Material false
        String titleFalse ="false";
        String fileTypeFalse = "txt";
        Assert.assertFalse("Material has been set.", course.setMaterial(titleFalse,fileTypeFalse,CDataBase.testMemberAdmin));
        Assert.assertFalse("Material has been set.", course.setMaterial(CDataBase.NewTestFileName,CDataBase.NewTestFileType,CDataBase.testMemberNoAdmin));
    }

    @Test
    public void testRemoveMaterial () {

        //remove Material False
        String titleFalse ="false";
        String fileTypeFalse = "txt";
        Assert.assertFalse("Material has been removed.", course.removeMaterial(course.getM_materials().get(0),CDataBase.testMemberNoAdmin));

        //remove Material True
        int currSizeMaterial = course.getM_materials().size();
        Assert.assertTrue("Material hasn´t been removed.",course.removeMaterial(course.getM_materials().get(0),CDataBase.testMemberAdmin));
        Assert.assertEquals(currSizeMaterial-1,course.getM_materials().size());


    }

}
