package userapp.service;

import userapp.persistence.dao.DepartmentDao;
import userapp.persistence.model.DepartmentModel;

import java.util.List;

public class DepartmentService {

    private DepartmentDao departmentDao =  new DepartmentDao();

    public void add(DepartmentModel departmentModel){
        departmentModel.setId(System.currentTimeMillis());
        departmentDao.addDepartment(departmentModel);
    }

    public List<DepartmentModel> getDepartments(){
        return departmentDao.readDepartments();
    }
}
