const url = "http://localhost:8090/"
const usern = localStorage["currentUser"];
const user_id = localStorage["user_id"]

console.log(user_id)

window.addEventListener('load', populateReqFunc)

document.getElementById("submitRequest").addEventListener("click", newRequestFunc)
document.getElementById("status").addEventListener("click", getReimbursementReqByStatus)

console.log(url);

//Create new Reimbursement Request
async function newRequestFunc(){

    var today = new Date();
    var dd = String(today.getDate()).padStart(2, '0');
    var mm = String(today.getMonth() + 1).padStart(2, '0'); //
    var yyyy = today.getFullYear();

    today = yyyy + '-' + mm + '-' + dd;


    let reqAmount = document.getElementById("reqAmnt").value
    let reqType = document.getElementById("reqType").value
    let reqDescription = document.getElementById("reqDescription").value

    request = {
        reimb_Amnt:reqAmount,
        type:{type_id:getTypeId(reqType)},
        dateSubmitted:today,
        description:reqDescription,
        author:{user_id:user_id},
        status:{status_id:3}
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

// Retrieves list of reimbursement requests by current user and populates table on load
async function populateReqFunc(){

    
    let pathName = url +  "reimbursements/" + user_id 


    let response = await fetch(pathName, {
        method:"GET",
        credentials: "include"
    })

    
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
                cell4.innerHTML = " - "
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
            if (reimbursement.resolver === undefined){
                cell7.innerHTML = " - "
            } else {
                cell7.innerHTML = reimbursement.resolver.username
            }
            row.appendChild(cell7);

            let cell8 = document.createElement("td")
            cell8.innerHTML = reimbursement.status.status;
            row.appendChild(cell8);

            let cell9 = document.createElement("td")
            cell9.innerHTML = reimbursement.type.type;
            row.appendChild(cell9);

            document.getElementById("reimbursementBody").appendChild(row);
            console.log(reimbursement.type.type)
        }
    } else{
        console.log("Request failed")
    }

}
// Function to get the appropriate type_id for corresponding type string
function getTypeId(type){
    switch(type){
        case "Lodging":
            return 1;
        case "Meals":
            return 2;
        case "Travel":
            return 3;
        case "Fuel":
            return 4;
        case "Misc":
            return 5;
    }
}

async function getReimbursementReqByStatus(){

    currentStatus = document.getElementById("statSelect").value

    let statusInfo = url + "reimbursements/" + user_id + "/" + currentStatus
    
    let response = await fetch(statusInfo, {

        method:"GET",
        credentials:"include"
    })


   if (response.status === 200){
        let data = await response.json();
        document.getElementById('reimbursementBody').innerHTML = '';

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
                cell4.innerHTML = " - "
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
            if (reimbursement.resolver === undefined){
                cell7.innerHTML = " - "
            } else {
                cell7.innerHTML = reimbursement.resolver.username
            }
            row.appendChild(cell7);

            let cell8 = document.createElement("td")
            cell8.innerHTML = reimbursement.status.status;
            row.appendChild(cell8);

            let cell9 = document.createElement("td")
            cell9.innerHTML = reimbursement.type.type;
            row.appendChild(cell9);

            document.getElementById("reimbursementBody").appendChild(row);
            console.log(reimbursement.type.type)
        }
    } else {
            console.log("Request failed")
        }
}

