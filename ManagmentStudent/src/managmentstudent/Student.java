/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package managmentstudent;

/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class Student {
private String id;
private String name;
private String birthday;
private float mean_of_marks;

    public Student() {
    }

    public Student(String id, String name, String birthday, float mean_of_marks) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.mean_of_marks = mean_of_marks;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

   

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public float getMean_of_marks() {
        return mean_of_marks;
    }

    public void setMean_of_marks(float mean_of_marks) {
        this.mean_of_marks = mean_of_marks;
    }

    @Override
    public String toString() {
        return "Student{" + "idString=" + id + ", name=" + name + ", birthday=" + birthday + ", mean_of_marks=" + mean_of_marks + '}';
    }

 
}
