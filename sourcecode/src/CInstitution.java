/**
 * This class represents the multiple institutions that are part of the platform.
 * One instance of this class contains a list of courses, a list of admins and an ID.
 *
 * @author Ron Wagner
 * @see CMember
 * @see CCourse
 */

import java.util.ArrayList;

public class CInstitution {

    /**
     * Class constructor that creates an instance of this class with the given attribute.
     *
     * @param   _ID           Integer value that represents a unique identifier for every instance
     * @return                An instance of this class with the specific attributes
     */
    public CInstitution(int _ID){
        this.m_courses = new ArrayList<CCourse>();
        this.m_admins = new ArrayList<CMember>();
        this.m_ID = _ID;
    }

    /**************
     *ATTRIBUTES
     *************/
    private ArrayList<CCourse> m_courses;
    private ArrayList<CMember> m_admins;
    private int m_ID;

    /**************
     *METHODS
     *************/

    /**
     * This method creates an instance of {@link CCourse}.
     * Only the administrator of the institution is allowed to add/create courses.
     *
     * @param _title            Character string that represents the name of this course
     * @param _admin            Instance of class {@link CMember}, should be the calling person, that helps determining whether
     *                          the person is an admin of this institution or not
     * @return                  A null reference of a {@link CCourse} when the calling {@link CMember} is not an admin of this institution
     *                          or an instance of a {@link CCourse} with the given {@link CMember} as admin, _title as title and the
     *                          ID of this institution
     */
    public CCourse addCourse(String _title, CMember _admin) {
        boolean success = false;
        if (this.m_admins.contains(_admin)) {
            success = true;
        }
        CCourse course;
        if(success){
            course = new CCourse(_title, _admin, this.getM_ID());
            this.m_courses.add(course);
        }
        else{
            course = null;
        }
        return course;
    }

    /**
     * This method removes an instance of {@link CCourse} from the list m_courses.
     * Only the administrator of the institution is allowed to remove courses.
     *
     * @param _course           Instance of {@link CCourse} that represents the course to be deleted
     * @param _admin            Instance of class {@link CMember}, should be the calling person, that helps determining whether
     *                          the person is an admin of this institution or not
     * @return                  True if _admin is an admin and _course was part of the course list of the institution,
     *                          false if not
     */
    public boolean deleteCourse(CCourse _course, CMember _admin) {
        boolean is_admin = false;
        if(this.getM_admins().contains(_admin)) {
            is_admin = true;
        }
        boolean success = false;
        if (is_admin) {
            if(this.m_courses.contains(_course)){
                this.m_courses.remove(_course);
                success = true;
                }
        }
        return success;
    }

    /**
     * This method enters an instance of {@link CMember} into the list of {@link CMember} if the given key is correct
     * It checks if the given course is part of the course list of the institution, if the given key is correct and if
     * the member isn't already part of the course.
     *
     * @param _key              Integer value that represents the key that the member entered
     * @param _course           Instance of {@link CCourse} that represents the course to be entered
     * @param _member           Instance of class {@link CMember}, should be the calling person, that gets entered into
     *                          the member list of _course if his key was correct
     * @return                  True if the member was entered successfully i.e. he was not already part of the course, his key was valid
     *                          and the course is part of this institution, false if not
     */
    public boolean loginCourse(int _key, CCourse _course, CMember _member){
        boolean success = false;
        if(this.m_courses.contains(_course) && _course.getM_license().validateKey(_key) && !_course.getM_members().contains(_member)){
            _course.addMember(_member);
            success = true;
        }
        return success;
    }

    /**
     * This method checks if a given instance of {@link CMember} is entered in the member list a given instance of {@link CCourse}
     * and if so removes the member from the list.
     *
     * @param _course           Instance of {@link CCourse} that represents the course
     * @param _member           Instance of class {@link CMember}, should be the calling person, that gets removed from the member list
     *                          of _course
     * @return                  True if the member was removed successfully i.e. he was part of the course
     *                          and the course is part of this institution, false if not
     */
    public boolean logoutCourse(CMember _member, CCourse _course){
        boolean success = false;
        if(this.m_courses.contains(_course) && _course.getM_members().contains(_member)){
            _course.removeMember(_member);
            success = true;
        }
        return success;
    }

    public void addAdmin(CMember _admin){
        this.m_admins.add(_admin);
        return;
    }

    public boolean deleteAdmin(CMember _admin){
        boolean success = false;
        int i = 0;
        if (this.m_admins.contains(_admin)) {
            this.m_admins.remove(i);
            success = true;
        }
        return success;
    }

    public void setM_ID(int ID){
        this.m_ID = ID;
        return;
    }
    public int getM_ID(){
        return this.m_ID;
    }

    public ArrayList<CMember> getM_admins(){return m_admins;}
}

