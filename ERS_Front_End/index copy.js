const url = "http://localhost:8090/"

console.log(currentUser);

window.addEventListener('load', populateAllFunc)

document.getElementById("submitRequest").addEventListener("click", newRequestFunc)

async function newRequestFunc(){

    let reqAmount = document.getElementById("reqAmnt").value
    let reqType = document.getElementById("reqType").value
    let reqDescription = document.getElementById("reqDescription").value

    request = {
        reimb_Amnt:reqAmount,
        type:{reqType},
        description:reqDescription
    }

    let response = await fetch(url + "reimbursements", {
        method:"POST",
        body:JSON.stringify(request),
        credentials:"include"
    })

    console.log(response.status)

    if (response.status === 200){
        console.log("Request Submitted")
    }

}

async function populateAllFunc(){

    let response = await fetch(url + "reimbursements", {credentials: "include"});

    if (response.status === 200) {
        let data = await response.json();
        console.log(response.status);

        for(let reimbursement of data){
            
            let row = document.createElement("tr")

            let cell = document.createElement("td")
            cell.innerHTML = reimbursement.reimb_Id;
            row.appendChild(cell);

            let cell2 = document.createElement("td")
            cell2.innerHTML = reimbursement.reimb_Amnt;
            row.appendChild(cell2);

            let cell3 = document.createElement("td")
            cell3.innerHTML = reimbursement.dateSubmitted;
            row.appendChild(cell3);

            let cell4 = document.createElement("td")
            if (reimbursement.dateResolved === undefined){
                cell4.innerHTML = "-"
            }else {
                cell4.innerHTML = reimbursement.dateResolved; 
            }
            row.appendChild(cell4);

            let cell5 = document.createElement("td")
            cell5.innerHTML = reimbursement.description;
            row.appendChild(cell5);

            let cell6 = document.createElement("td")
            cell6.innerHTML = reimbursement.author.username;
            row.appendChild(cell6);

            let cell7 = document.createElement("td")
            cell7.innerHTML = reimbursement.resolver.username;
            row.appendChild(cell7);

            let cell8 = document.createElement("td")
            cell8.innerHTML = reimbursement.status.status;
            row.appendChild(cell8);

            let cell9 = document.createElement("td")
            cell9.innerHTML = reimbursement.type.type;
            row.appendChild(cell9);

            document.getElementById("reimbursementBody").appendChild(row);
        }
    }

}