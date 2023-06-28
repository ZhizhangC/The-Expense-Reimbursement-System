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
//		Status status1 = new Status(1, "Approved");
//		Status status2 = new Status(2, "Denied");
//		Status status3 = new Status(3, "Pending");
//		User user1 = new User(1, "Mario", "Mario", role1, "MM_R", "fjkld");
//		User user2 = new User(2, "Luigi", "Mario", role2, "LM_R", "fjdfkld");
//		Reimbursement r1 = new Reimbursement(0, 5700, "First Reimbursement", user2, status1);
//		Reimbursement r2 = new Reimbursement(0, 1300, "Second Reimbursement", user2, status3);
//		Reimbursement r3 = new Reimbursement(0, 3500, "Third Reimbursement", user1, status3);
//
//		roleService.addRole(role1);
//		roleService.addRole(role2);
//		statusService.addStatus(status1);
//		statusService.addStatus(status2);
//		statusService.addStatus(status3);
//		userService.createUser(user1);
//		userService.createUser(user2);
//		reimbursementService.createReimbursement(r1);
//		reimbursementService.createReimbursement(r2);
//		reimbursementService.createReimbursement(r3);
//		reimbursementService.approveReimbursement(1);
//		reimbursementService.denyReimbursement(1);
//		reimbursementService.getAllPendingReimbursements();
//		reimbursementService.getAllResolvedReimbursements();
		reimbursementService.getResolvedReimbursementsByPerson(2);
		reimbursementService.getPendingReimbursementsByPerson(1);
//		roleService.deleteRole(1);
//		roleService.deleteRole(2);
//		statusService.deleteStatus(1);

//		System.out.println(statusService.getAllStatus());
//		System.out.println(roleService.getAllRoles());
//		System.out.println(statusService.searchStatuses("app"));

	}

}
