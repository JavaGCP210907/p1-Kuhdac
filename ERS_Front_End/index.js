const url = "http://localhost:8090/"

window.addEventListener('load', populateAllFunc)


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
            cell4.innerHTML = reimbursement.dateResolved;
            row.appendChild(cell4);

            let cell5 = document.createElement("td")
            cell5.innerHTML = reimbursement.description;
            row.appendChild(cell5);

            let cell6 = document.createElement("td")
            cell6.innerHTML = reimbursement.author;
            row.appendChild(cell6);

            let cell7 = document.createElement("td")
            cell7.innerHTML = reimbursement.resolver;
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