package com.revature;

import com.revature.models.Role;
import com.revature.models.Status;
import com.revature.services.RoleService;
import com.revature.services.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ExpenseReimbursementSystemApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ExpenseReimbursementSystemApplication.class, args);

	}

	@Autowired
	private RoleService roleService;
	@Autowired
	private StatusService statusService;

	@Override
	public void run(String... args) throws Exception{


//		Role role1 = new Role(0, "Employee");
//		Role role2 = new Role(0, "Finance Manager");
//		Status status = new Status(0, "Approved");
//
//		roleService.addRole(role1);
//		roleService.addRole(role2);
//		statusService.addStatus(status);

//		roleService.deleteRole(1);
//		roleService.deleteRole(2);
//		statusService.deleteStatus(1);

//		System.out.println(statusService.getAllStatus());
//		System.out.println(roleService.getAllRoles());
//		System.out.println(statusService.searchStatuses("app"));

	}

}
