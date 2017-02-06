/**
 * Created by kevin on 29.01.2017.
 */

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

public class CCourseTest {
    private CCourse course;

    @Before
    public void setUp()
    {
        course = new CCourse(CDataBase.titleCourse,CDataBase.testMemberAdmin, CDataBase.InstitutionID);
    }

    @Test
    public void testCreateCourse ()
    {
        Assert.assertEquals("Title should be equal to the titleCourse given to the constructor",CDataBase.titleCourse,course.getM_title());
        Assert.assertEquals("Admin ID should be equal to the ID given to the constructor",CDataBase.idAdmin,course.getM_admin().getM_ID());
        Assert.assertEquals("Institution ID should be equal to the ID given to the constructor",CDataBase.InstitutionID,course.getM_InstitutionID());
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

    //more tests regarding controlExercise and deleteExercise

    @Test
    public void testSetMaterial ()
    {
        String newTitle = "fahrrad";
        String fileType = "jpg";
        course.setMaterial(newTitle,fileType,CDataBase.testMemberAdmin);
        Assert.assertTrue("Material was set.", true);
    }
}
