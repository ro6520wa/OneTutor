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
}
