let loggedInUser = parseJwt(document.cookie)

console.log(loggedInUser) 


document.getElementById("greeting").innerText = "Welcome " + loggedInUser.sub + ", " + loggedInUser.Role + " " + loggedInUser.Id + " " + loggedInUser.FirstName + " " + loggedInUser.LastName


const url = "http://localhost:8080/"

window.onload = async function(){
    await fetch(url + "reimbursements/users/pending", {
        method: "GET",
        headers: {
            "Content-Type": "application/json",
            "Authorization": "Bearer " + document.cookie
        }
     })
    .then((response) => response.json())
    .then((data) => {
        console.log(data)
        for(let reimbursement of data){
            let row = document.createElement("tr")
            let cell = document.createElement("td")
            cell.innerText = reimbursement.id
            row.appendChild(cell)
            let cell2 = document.createElement("td")
            cell2.innerText = reimbursement.user_id.firstName + " " + reimbursement.user_id.lastName
            row.appendChild(cell2)
            let cell3 = document.createElement("td")
            cell3.innerText = reimbursement.amount
            row.appendChild(cell3)
            let cell4 = document.createElement("td")
            cell4.innerText = reimbursement.description
            row.appendChild(cell4)
            let cell5 = document.createElement("td")
            var approveButton = document.createElement("button")
            approveButton.innerText = "Approve"
            var denyButton = document.createElement("button")
            denyButton.innerText = "Deny"
            let gap = document.createTextNode(" ");
            cell5.appendChild(approveButton)
            cell5.appendChild(gap);
            cell5.appendChild(denyButton)
            row.appendChild(cell5)
            document.getElementById("pendingTableBody").appendChild(row)
            approveButton.addEventListener("click", function () {
                approveDenyFunction(reimbursement.id, 1);
            });
            denyButton.addEventListener("click", function () {
                approveDenyFunction(reimbursement.id, 2);
            });
        }
    })
    await fetch(url + "reimbursements/users/resolved", {
        method: "GET",
        headers: {
            "Content-Type": "application/json",
            "Authorization": "Bearer " + document.cookie
        }
     })
    .then((response) => response.json())
    .then((data) => {
        console.log(data);
        for (let reimbursement of data) {
            let row = document.createElement("tr");
            let cell = document.createElement("td");
            cell.innerText = reimbursement.id;
            row.appendChild(cell);
            let cell2 = document.createElement("td");
            cell2.innerText = reimbursement.user_id.firstName + " " + reimbursement.user_id.lastName;
            row.appendChild(cell2);
            let cell3 = document.createElement("td");
            cell3.innerText = reimbursement.amount;
            row.appendChild(cell3);
            let cell4 = document.createElement("td");
            cell4.innerText = reimbursement.description;
            row.appendChild(cell4);
            let cell5 = document.createElement("td");
            cell5.innerText = reimbursement.status_id.name;
            row.appendChild(cell5);
            document.getElementById("resolvedTableBody").appendChild(row);
        }
    });
} 

async function approveDenyFunction(reimbursementId, action) {
    if(action === 1){
        await fetch(url + "reimbursements/approve/" + reimbursementId, {
            method: "PUT",
            headers: {
                "Content-Type": "application/json",
                "Authorization": "Bearer " + document.cookie
            }
         })
        .then((response) => response.json())
        .then((data) => {
            console.log(data);
            console.log("Reimbursement Approved!");
        })      
        .catch((error) => {
         console.log("Failed to approve reimbursement: " + error);
        });
    }else {
        await fetch(url + "reimbursements/deny/" + reimbursementId, {
            method: "PUT",
            headers: {
                "Content-Type": "application/json",
                "Authorization": "Bearer " + document.cookie
            }
        })
        .then((response) => response.json())
        .then((data) => {
            console.log(data);
            console.log("Reimbursement Denied!");
        })
        .catch((error) => {
        console.log("Failed to deny reimbursement: " + error);
        });
    }

}





// function approveDenyFunction(newStatus, f){

//     if(newStatus === 1){
//         approveReimbursement(f)
//     }
//     else if(newStatus === 2){
//         denyReimbursement(f)
//     }

// }

function parseJwt (token) {
    console.log(token)
    var base64Url = token.split('.')[1];
    var base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
    var jsonPayload = decodeURIComponent(window.atob(base64).split('').map(function(c) {
        return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
    }).join(''));

    return JSON.parse(jsonPayload);
}