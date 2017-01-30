
import java.util.ArrayList;

/**
 * Created by kevin on 23.01.2017.
 */
public class CCourse {

    //constructor
    public CCourse (String _title, CMember _courseAdmin, int _InstitutionID)
    {
        this.m_title = _title;
        this.m_admin = _courseAdmin;
        this.m_license = new CLicense();
        this.m_InstitutionID = _InstitutionID;

        this.m_exercises = new ArrayList<CExercise>();
        this.m_materials = new ArrayList<CMaterial>();
        this.m_members = new ArrayList<CMember>();
    }

    //attributes
    private String m_title;
    private CMember m_admin;
    private CLicense m_license;
    private ArrayList<CExercise> m_exercises;
    private ArrayList<CMember> m_members;
    private ArrayList<CMaterial> m_materials;
    private int m_InstitutionID;

    //methods


    public int getM_InstitutionID() {
        return m_InstitutionID;
    }

    public String getM_title() {
        return m_title;
    }

    public boolean setM_title(String m_title, CMember _member) {

        boolean success = false;

        //check if _member has permission to change m_title
        if (_member.getM_ID() == this.m_admin.getM_ID())
        {
            this.m_title = m_title;
            return success = true;
        }
        else
        {
            return success;
        }
    }

    public CMember getM_admin() {
        return m_admin;
    }

    public CLicense getM_license() {
        return m_license;
    }

    public ArrayList<CExercise> getM_exercises() {
        return m_exercises;
    }

    public ArrayList<CMember> getM_members() {
        return m_members;
    }

    public ArrayList<CMaterial> getM_materials() {
        return m_materials;
    }

    public boolean setExercise (String _name, ArrayList<String> _questions, ArrayList _answers, boolean isCloze, CMember _member)
    {
        boolean success = false;
        if (this.m_admin.getM_ID() == _member.getM_ID())
        {
            if (isCloze)
            {
                CExercise newExercise = new CCloze(_name, _questions, _answers);
                this.m_exercises.add(newExercise);
                return success = true;
            }
            else
            {
                CExercise newExercise = new CMultipleChoice(_name, _questions, _answers);
                this.m_exercises.add(newExercise);
                return success = true;
            }
        }

        return success;
    }

    public boolean removeExercise(int _index, CMember _member)
    {
        boolean success = false;
        int listIndex = _index - 1;

        if (this.m_admin.getM_ID() == _member.getM_ID())
        {
            if ((this.getM_exercises().size() - listIndex >= 0) && (listIndex >= 0))
            {
                this.m_exercises.remove(listIndex);
                return success = true;
            }
        }

        return success;
    }

    public int controlExercise (int _index, ArrayList _answers)
    {
        int result = 0;
        int listIndex = _index - 1;

        if ((listIndex < 0) || ((this.m_exercises.size() - listIndex) < 0))
        {
            return result = - 1;
        }

        if (this.m_exercises.get(_index) instanceof CCloze)
        {
            result =(((CCloze) this.m_exercises.get(_index)).controlCloze(_answers));
        } else
        {
            result =(((CMultipleChoice) this.m_exercises.get(_index)).controlMultipleChoice(_answers));
        }

        return result;
    }

    public void addMember(CMember _member)
    {
        this.m_members.add(_member);
    }
}


