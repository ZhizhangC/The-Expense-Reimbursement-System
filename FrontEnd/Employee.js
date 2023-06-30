let loggedInUser = parseJwt(document.cookie)

console.log(loggedInUser) 


document.getElementById("greeting").innerText = "Welcome " + loggedInUser.sub + ", " + loggedInUser.Role + " " + loggedInUser.Id + " " + loggedInUser.FirstName + " " + loggedInUser.LastName


const url = "http://localhost:8080/"

window.onload = async function() {
    await fetch(url + "reimbursements/pending/" + loggedInUser.Id)
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
                document.getElementById("pendingTableBody").appendChild(row);
            }
        });

    await fetch(url + "reimbursements/resolved/" + loggedInUser.Id)
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
};


document.getElementById("createReimbursementButton").onclick = createReimbursement

async function createReimbursement() {
    let amount = parseInt(document.getElementById("amount").value);
    let description = document.getElementById("description").value;
  
    console.log(amount, description);
  
    let urlWithParams = `${url}reimbursements/create/${loggedInUser.Id}?a=${amount}&s=${description}`;
  
    await fetch(urlWithParams, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        "Authorization": "Bearer " + document.cookie
      }
    })
      .then((response) => response.json())
      .then((data) => {
        console.log(data);
        console.log("Reimbursement created!");
      })
      .catch((error) => {
        console.log("Failed to create reimbursement: " + error);
      });
  }


function parseJwt (token) {
    var base64Url = token.split('.')[1];
    var base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
    var jsonPayload = decodeURIComponent(window.atob(base64).split('').map(function(c) {
        return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
    }).join(''));

    return JSON.parse(jsonPayload);
}