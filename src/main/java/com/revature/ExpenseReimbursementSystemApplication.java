package com.revature;

import com.revature.models.Role;
import com.revature.models.Status;
import com.revature.models.User;
import com.revature.models.Reimbursement;
import com.revature.services.RoleService;
import com.revature.services.StatusService;
import com.revature.services.UserService;
import com.revature.services.ReimbursementService;
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
	@Autowired
	private UserService userService;
	@Autowired
	private ReimbursementService reimbursementService;

	@Override
	public void run(String... args) throws Exception{


//		Role role1 = new Role(1, "Employee");
//		Role role2 = new Role(2, "Finance Manager");
//		Status status = new Status(3, "Pending");
//		User user1 = new User(2, "Mario", "Mario", role1, "MM_R", "fjkld");
//		User user2 = new User(3, "Luigi", "Mario", role2, "LM_R", "fjdfkld");
//		Reimbursement r1 = new Reimbursement(0, 8000, "First Reimbursement", user1, status);
//
//		roleService.addRole(role1);
//		roleService.addRole(role2);
//		statusService.addStatus(status);
//		userService.createUser(user1);
//		reimbursementService.createReimbursement(r1);
//		reimbursementService.approveReimbursement(1);
//		reimbursementService.denyReimbursement(1);
//		reimbursementService.getAllResolvedReimbursements();
		reimbursementService.getResolvedReimbursementsByPerson(3);
		reimbursementService.getPendingReimbursementsByPerson(2);
//		roleService.deleteRole(1);
//		roleService.deleteRole(2);
//		statusService.deleteStatus(1);

//		System.out.println(statusService.getAllStatus());
//		System.out.println(roleService.getAllRoles());
//		System.out.println(statusService.searchStatuses("app"));

	}

}
