import java.util.ArrayList;
/**
 * Created by ronwa on 27.01.2017.
 */
public class CPlatform {

    //attributes
    private ArrayList<CInstitution> m_institutions;
    private ArrayList<CMember> m_member;
    private ArrayList<CMember> m_admins;

    //constructor
    public CPlatform() {
        this.m_institutions = new ArrayList<CInstitution>();
        this.m_member = new ArrayList<CMember>();
        this.m_admins = new ArrayList<CMember>();
    }

    //methods
   public CMember register(String firstname, String lastname, int ID, String mail){
        CMember member = new CMember(firstname, lastname, ID, mail);
        addMember(member);
        return member;
    }

    public boolean login(String _mail){
        boolean success = false;
        int i = 0;
        do{
            if(this.m_member.get(i).getM_mail() == _mail){
                success = true;
            }
        }while(!success && i < this.m_member.size());
        return success;
    }
    //LOGOUT NEEDED!?

    public void addMember(CMember _member){
        this.m_member.add(_member);
        return;
    }

    public boolean deleteMember(CMember _member){
        boolean success = false;
        int i = 0;
        //check if the _member actually is in the member list of the platform
        do{
            if(this.m_member.get(i).getM_ID() == _member.getM_ID()){
                int j = 0;
                //if so, remove him from all his courses
                do{
                    _member.getCourses().get(j).getM_members().remove(_member);
                    j++;
                }while(j < this.m_member.get(i).getCourses().size());
                //and delete him from the member list and all reference to himself
                this.m_member.remove(i);
                success = true;
                _member = null;
            }
            i++;
        }while(!success && i < this.m_member.size());
        return success;
    }

    public void addAdmin(CMember _admin){
        this.m_admins.add(_admin);
        return;
    }

    //admins can only remove themselves or get deleted directly in the database by us
    public boolean removeAdmin(CMember _admin){
        boolean success = false;
        int i = 0;
        //checking if the member calling the function is actually an admin
        do {
            if (this.m_admins.get(i).getM_ID() == _admin.getM_ID()){
                this.m_admins.remove(i);
                success = true;
            }
        }while(!success && i < this.m_admins.size());
        return success;
    }

    //return the ID so the calling function/user knows what institution he just generated
    public int addInstitution(){
        //for our uses it is enough to just generate a random ID, with higher numbers of institutions we would implement an autoincrement
        CLicense license = new CLicense();
        int ID = license.getM_key();
        CInstitution institution = new CInstitution(ID);
        m_institutions.add(institution);
        return ID;
    }

    //returns true when the institution with the given ID could be found and removed. returns false if there's no institution with the given ID
    public boolean removeInstitution(int _ID){
        CInstitution institution = getInstitutionByID(_ID);
        if(institution != null){
            m_institutions.remove(institution);
            return true;
        }else {
            return false;
        }
    }

    //return the Institution belonging to the ID given, if there's none we return null
    public CInstitution getInstitutionByID(int _ID){
        CInstitution institution = null;
        boolean success = false;
        int i = 0;
        do{
            if(m_institutions.get(i).getM_ID() == _ID){
                institution = m_institutions.get(i);
                success = true;
            }
            i++;
        }while(!success && i < m_institutions.size());
        if(success){
            return institution;
        }else{
            return null;
        }
    }
}
