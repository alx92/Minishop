package userapp.persistence.dao;

import userapp.persistence.FileUtil;
import userapp.persistence.model.DepartmentModel;

import java.util.*;

public class DepartmentDao
{
    private FileUtil<DepartmentModel> fileUtil = new FileUtil("department.txt");

    //Create (ADD)
    public void addDepartment(DepartmentModel department){
        List<DepartmentModel> departmentModelList = fileUtil.read();
        departmentModelList.add(department);
        fileUtil.write(departmentModelList);
    }

    //Read (Read)
    public List<DepartmentModel> readDepartments(){
        return fileUtil.read();
    }

    //Read (Read)
    public Optional<DepartmentModel> readDepartment(int id){
        List<DepartmentModel> departments = fileUtil.read();
        return departments.stream().filter(department -> department.getId() ==  id)
                .findFirst();
    }

    //Update
    public void update(DepartmentModel updateDepartment){
        List<DepartmentModel> departments = fileUtil.read();
        for(int i = 0; i  < departments.size() ; i++){
            DepartmentModel oldDepartment =  departments.get(i);
            if(oldDepartment.getId() == updateDepartment.getId()){
                departments.remove(oldDepartment);
                departments.add(updateDepartment);
            }
        }
        fileUtil.write(departments);
    }

    //Delete
    public void delete(int id){
        List<DepartmentModel> departments = fileUtil.read();
        for(int i = 0; i  < departments.size() ; i++){
            DepartmentModel departmentModel =  departments.get(i);
            if(departmentModel.getId() == id){
                departments.remove(departmentModel);
            }
        }
        fileUtil.write(departments);
    }
}