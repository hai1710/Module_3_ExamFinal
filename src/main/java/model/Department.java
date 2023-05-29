package model;

public class Department {
    protected int id_department;
    protected String nameDepartment;

    public Department() {
    }

    public Department(int id_department, String nameDepartment) {
        this.id_department = id_department;
        this.nameDepartment = nameDepartment;
    }

    public int getId_department() {
        return id_department;
    }

    public void setId_department(int id_department) {
        this.id_department = id_department;
    }

    public String getNameDepartment() {
        return nameDepartment;
    }

    public void setNameDepartment(String nameDepartment) {
        this.nameDepartment = nameDepartment;
    }
}
