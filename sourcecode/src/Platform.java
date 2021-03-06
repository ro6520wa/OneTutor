/**
 * This class represents the platform. Normally only one instance of this class exists. It contains a list of institutions, member and admins.
 *
 * @author Ron Wagner
 * @see Institution
 * @see Member
 * @see License
 */

import java.util.ArrayList;

public class Platform {

    /**************
     *CONSTRUCTOR
     *************/
    public Platform() {
        this.m_institutions = new ArrayList<Institution>();
        this.m_member = new ArrayList<Member>();
        this.m_admins = new ArrayList<Member>();
    }

    /**************
     *ATTRIBUTES
     *************/
    private ArrayList<Institution> m_institutions;
    private ArrayList<Member> m_member;
    private ArrayList<Member> m_admins;

    /**************
     *METHODS
     *************/

    /**
     * This methods registers a user to use the platform and enters him into the member list if the given mail string is not already in use.
     * This method creates an instance of {@link Member} if the mail string given is not already in use.
     *
     * @param _firstname        Character string that represents the firstname of the member
     * @param _lastname         Character string that represents the lastname of the member
     * @param _ID               Integer value that represents the ID to be used, probably given by the database
     * @param _mail             Character string that represents the E-Mail of the member
     * @return                  Null if the mail is already in use, an instance of {@link Member} with the given parameters if the mail is not
     *                          already in use
     */
   public Member register(String _firstname, String _lastname, int _ID, String _mail){
        int i = 0;
        boolean mail_used = false;
        while(i < this.m_member.size() && !mail_used){
            if(this.m_member.get(i).getM_mail() == _mail){
                mail_used = true;
            }
            i++;
        }
        if (mail_used){
            return null;
        }
        else {
            Member member = new Member(_firstname, _lastname, _ID, _mail);
            addMember(member);
            return member;
        }
    }

    /**
     * This methods logs a user into the platform if his mail is correct.
     *
     * @param _mail             Character string that represents the E-Mail of the member
     * @return                  True if the mail exists in the member list, false if not
     */
    public boolean login(String _mail){
        boolean success = false;
        int i = 0;
        while (!success && i < this.m_member.size()) {
            if (this.m_member.get(i).getM_mail() == _mail) {
                success = true;
            }
            i++;
        }
        return success;
    }

    public void addMember(Member _member){
        this.m_member.add(_member);
        return;
    }

    /**
     * This method deletes a {@link Member} from the platform, all reference to him and removes him from his courses.
     *
     * @param _member           Instance of {@link Member} that represents the member to be deleted
     * @return                  void
     */
    public void deleteMember(Member _member){
        int j = 0;
        while (j < _member.getCourses().size()) {
            _member.getCourses().get(j).getM_members().remove(_member);
            j++;
        }
        this.m_member.remove(_member);
        _member = null;
        return;
    }

    public void addAdmin(Member _admin){
        this.m_admins.add(_admin);
        return;
    }

    public boolean removeAdmin(Member _admin){
        boolean success = false;
        if (this.m_admins.contains(_admin)){
            this.m_admins.remove(_admin);
            success = true;
        }
        return success;
    }

    /**
     * This method creates an instance of {@link Institution} and therefor creates an instance of {@link License}
     * For our uses it is enough to just generate a random ID, with higher numbers of institutions we would implement an autoincrement.
     *
     * @return                  Integer value that represents the ID of the institution that just got created
     */
    public int addInstitution(){
        License license = new License();
        int ID = license.getM_key();
        Institution institution = new Institution(ID);
        m_institutions.add(institution);
        return ID;
    }

    /**
     * This method removes an instance of {@link Institution} if the an institution with the given ID could be found.
     * Therefor it uses the helper function getInstitutionByID
     *
     * @param _ID               Integer value that represents the ID of the institution to be removed
     * @return                  True if institution was found and removed, false if not
     */
    public boolean removeInstitution(int _ID){
        Institution institution = getInstitutionByID(_ID);
        if(institution != null){
            m_institutions.remove(institution);
            return true;
        }else {
            return false;
        }
    }

    /**
     * This method searches for a {@link Institution} with the given ID
     *
     * @param _ID               Integer value that represents the ID of the institution to be searched
     * @return                  An instance of {@link Institution} when the given ID could be found, null if not
     */
    public Institution getInstitutionByID(int _ID){
        Institution institution = null;
        boolean success = false;
        int i = 0;
        while (!success && i < m_institutions.size()) {
            if (m_institutions.get(i).getM_ID() == _ID) {
                institution = m_institutions.get(i);
                success = true;
            }
            i++;
        }
        if(success){
            return institution;
        }else{
            return null;
        }
    }

    public int getMemberListSize(){
        return this.m_member.size();
    }
}
