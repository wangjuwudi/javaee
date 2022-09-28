package first.com.wd;

import org.openxmlformats.schemas.presentationml.x2006.main.STIndex;

public class student {
    private String name;


    student (String n,String i,String s,String C,String t,String e){
        this.name=n;
        this.id=i;
        this.sex=s;
        this.tel=t;
        this.Classes=C;
        this.email=e;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getClasses() {
        return Classes;
    }

    public void setClasses(String aClass) {
        Classes = aClass;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private String id;
    private String sex;
    private String Classes;
    private String tel;
    private String email;
}
