const url = "http://localhost:8090/"
const user = localStorage["currentUser"];
const cuser_id = localStorage["user_id"]

console.log(user);

window.addEventListener('load', populateAllFunc)

document.getElementById("submitRequest").addEventListener("click", newRequestFunc)
document.getElementById("submitFilter").addEventListener("click", filterStatusFunc)
document.getElementById("logout").addEventListener("click", logOutFunc)
document.getElementById("refresh").addEventListener("click", populateAllFunc)

console.log(url);

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
        document.getElementById("reimbursementBody").innerHTML = ""
        for(let reimbursement of data){
            
            r = "r" + String(reimbursement.reimb_Id)
            s = "s" + String(reimbursement.reimb_Id)

            var btnId = s

            let row = document.createElement("tr")
            row.setAttribute("class", "text-center border border-rounded")
            row.setAttribute("type", "button")
            row.setAttribute("data-bs-toggle", "collapse")
            row.setAttribute("data-bs-target", "#" + r)

            let cell = document.createElement("th")
            cell.setAttribute("scope","row")
            cell.innerHTML = reimbursement.reimb_Id;
            row.appendChild(cell);

            let cell2 = document.createElement("td")
            cell2.innerHTML = reimbursement.author.username;
            row.appendChild(cell2);

            let cell3 = document.createElement("td")
            cell3.innerHTML = reimbursement.reimb_Amnt;
            row.appendChild(cell3);

            let cell4 = document.createElement("td")
            cell4.innerHTML = reimbursement.dateSubmitted;
            row.appendChild(cell4);

            let statusDiv = document.createElement("td")
            statusDiv.innerHTML = reimbursement.status.status
            row.appendChild(statusDiv)

            document.getElementById("reimbursementBody").appendChild(row);

            let collRow = document.createElement("tr")
            collRow.setAttribute("class", "text-center accordion-collapse collapse ")
            collRow.setAttribute("id", r)

            let collCell = document.createElement("td")
            collCell.setAttribute("colspan", "5")

            let collDiv = document.createElement("div")
            collDiv.setAttribute("class", "container")

            let divRow1 = document.createElement("div")
            divRow1.setAttribute("class", "row")

            let typeDiv = document.createElement("div")
            typeDiv.setAttribute("class", "col")

            let typeHead = document.createElement("p")
            typeHead.innerHTML = "Type"
            typeHead.setAttribute("class", "text-muted h-6 p-0")
            typeDiv.appendChild(typeHead)

            let typeBody = document.createElement("p")
            typeBody.innerHTML = reimbursement.type.type
            typeDiv.appendChild(typeBody)

            divRow1.appendChild(typeDiv)
            collDiv.appendChild(divRow1)

            let updateParCol = document.createElement("div")
            updateParCol.setAttribute("class", "col text-center col-sm-3")

            let updateCol = document.createElement("div")
            updateCol.setAttribute("class", "col text-center")

            let statusUpdate = document.createElement("select")
            statusUpdate.setAttribute("class", "form-select text-center")
            statusUpdate.setAttribute("id", s)

            let option1 = document.createElement("option")
            option1.setAttribute("value", "null")
            option1.innerText = "Select Status"
            statusUpdate.appendChild(option1);
            
            let option2 = document.createElement("option")
            option2.setAttribute("value", "1")
            option2.innerText = "Approved"
            statusUpdate.appendChild(option2);

            let option3 = document.createElement("option")
            option3.setAttribute("value", "2")
            option3.innerText = "Denied"
            statusUpdate.appendChild(option3);

            updateCol.appendChild(statusUpdate)
            updateParCol.appendChild(updateCol)
            divRow1.appendChild(updateParCol)

            let btnCol = document.createElement("div")
            btnCol.setAttribute("class", "col-sm-2")

            let subBtn = document.createElement("button")
            subBtn.setAttribute("type", "button")
            subBtn.setAttribute("id", "StatUpdate")
            subBtn.setAttribute("value", reimbursement.reimb_Id);
            subBtn.setAttribute("onclick", "updateReqFunc(this)");
            subBtn.innerText = "Submit"
            subBtn.setAttribute("class","btn btn-secondary")
            btnCol.appendChild(subBtn)

            divRow1.appendChild(btnCol);

            let divRow2 = document.createElement("div")
            divRow2.setAttribute("class", "row border-2 border-top")

            let resolverDiv = document.createElement("div")
            resolverDiv.setAttribute("class", "col")

            let resolverHead = document.createElement("p")
            resolverHead.innerHTML = "Resolver"
            resolverHead.setAttribute("class", "text-muted h-6 p-0")
            resolverDiv.appendChild(resolverHead)

            let resolverBody = document.createElement("p")
            if (reimbursement.resolver === undefined){
                    resolverBody.innerHTML = "-"
                 }else{
                     resolverBody.innerHTML = reimbursement.resolver.username;
                 }
            resolverDiv.appendChild(resolverBody)

            divRow2.appendChild(resolverDiv)

            let resolveDateDiv = document.createElement("div")
            resolveDateDiv.setAttribute("class", "col")

            let resolveDateHead = document.createElement("p")
            resolveDateHead.innerHTML = "Date Resolved"
            resolveDateHead.setAttribute("class", "text-muted h-6 p-0")
            resolveDateDiv.appendChild(resolveDateHead)

            let resolveDateBody = document.createElement("p")
            if (reimbursement.dateResolved === undefined){
                     resolveDateBody.innerHTML = "-"
                 }else {
                     resolveDateBody.innerHTML = reimbursement.dateResolved; 
                 }
            resolveDateDiv.appendChild(resolveDateBody)

            divRow2.appendChild(resolveDateDiv)

            collDiv.appendChild(divRow2)

            let divRow3 = document.createElement("div")
            divRow3.setAttribute("class", "row")

            let descriptionCol = document.createElement("div")
            descriptionCol.setAttribute("class", "col border-top border-2")
            
            let descriptionHeader = document.createElement("p")
            descriptionHeader.innerText = "Description"
            descriptionHeader.setAttribute("class", "text-muted h-6 p-0")
            descriptionCol.appendChild(descriptionHeader)

            let descriptionBody = document.createElement("p")
            descriptionBody.innerHTML = reimbursement.description
            descriptionCol.appendChild(descriptionBody)

            divRow3.appendChild(descriptionCol)

            collDiv.appendChild(divRow3)

            collCell.appendChild(collDiv)

            collRow.appendChild(collCell)

            document.getElementById("reimbursementBody").appendChild(collRow)

            r++
        }
    }

    

}

async function updateReqFunc(val){
        
    let rReimb_id = val.value

    var today = new Date();
    var dd = String(today.getDate()).padStart(2, '0');
    var mm = String(today.getMonth() + 1).padStart(2, '0'); //
    var yyyy = today.getFullYear();

    today = yyyy + '-' + mm + '-' + dd;

    let update = {
        reimb_Id:rReimb_id,
        status:{status_id:document.getElementById("s" + rReimb_id).value},
        resolver:{user_id:cuser_id},
        dateResolved: today
    }

     let response = await fetch(url + "reimbursements", {
        method:"PATCH",
        body:JSON.stringify(update),
        credentials:"include"
    })

    console.log(response.status)
    var toastLiveExample = document.getElementById('successToast')
    if (response.status === 200){
        console.log("Ticket: " + rReimb_id + "updated")
        var toast = new bootstrap.Toast(toastLiveExample)
        toast.show();
    toast.show()
    } else{
        console.log("Unable to update")
    }

}

async function filterStatusFunc(){

    let curFilter = document.getElementById("statusFilter").value
    let stat = {
        status_id:curFilter
    }

    let response = await fetch(url + "reimbursements", {
        credentials: "include",
        method:"POST",
        body:JSON.stringify(stat)
        })

    if (response.status === 200) {
        let data = await response.json();
        document.getElementById("reimbursementBody").innerHTML = ""
        for(let reimbursement of data){
            
            r = "r" + String(reimbursement.reimb_Id)
            s = "s" + String(reimbursement.reimb_Id)

            var btnId = s

            let row = document.createElement("tr")
            row.setAttribute("class", "text-center border border-rounded")
            row.setAttribute("type", "button")
            row.setAttribute("data-bs-toggle", "collapse")
            row.setAttribute("data-bs-target", "#" + r)

            let cell = document.createElement("th")
            cell.setAttribute("scope","row")
            cell.innerHTML = reimbursement.reimb_Id;
            row.appendChild(cell);

            let cell2 = document.createElement("td")
            cell2.innerHTML = reimbursement.author.username;
            row.appendChild(cell2);

            let cell3 = document.createElement("td")
            cell3.innerHTML = reimbursement.reimb_Amnt;
            row.appendChild(cell3);

            let cell4 = document.createElement("td")
            cell4.innerHTML = reimbursement.dateSubmitted;
            row.appendChild(cell4);

            let statusDiv = document.createElement("td")
            statusDiv.innerHTML = reimbursement.status.status
            row.appendChild(statusDiv)

            document.getElementById("reimbursementBody").appendChild(row);

            let collRow = document.createElement("tr")
            collRow.setAttribute("class", "text-center accordion-collapse collapse ")
            collRow.setAttribute("id", r)

            let collCell = document.createElement("td")
            collCell.setAttribute("colspan", "5")

            let collDiv = document.createElement("div")
            collDiv.setAttribute("class", "container")

            let divRow1 = document.createElement("div")
            divRow1.setAttribute("class", "row")

            let typeDiv = document.createElement("div")
            typeDiv.setAttribute("class", "col")

            let typeHead = document.createElement("p")
            typeHead.innerHTML = "Type"
            typeHead.setAttribute("class", "text-muted h-6 p-0")
            typeDiv.appendChild(typeHead)

            let typeBody = document.createElement("p")
            typeBody.innerHTML = reimbursement.type.type
            typeDiv.appendChild(typeBody)

            divRow1.appendChild(typeDiv)
            collDiv.appendChild(divRow1)

            let updateParCol = document.createElement("div")
            updateParCol.setAttribute("class", "col text-center col-sm-3")

            let updateCol = document.createElement("div")
            updateCol.setAttribute("class", "col text-center")

            let statusUpdate = document.createElement("select")
            statusUpdate.setAttribute("class", "form-select text-center")
            statusUpdate.setAttribute("id", s)

            let option1 = document.createElement("option")
            option1.setAttribute("value", "null")
            option1.innerText = "Select Status"
            statusUpdate.appendChild(option1);
            
            let option2 = document.createElement("option")
            option2.setAttribute("value", "1")
            option2.innerText = "Approved"
            statusUpdate.appendChild(option2);

            let option3 = document.createElement("option")
            option3.setAttribute("value", "2")
            option3.innerText = "Denied"
            statusUpdate.appendChild(option3);

            updateCol.appendChild(statusUpdate)
            updateParCol.appendChild(updateCol)
            divRow1.appendChild(updateParCol)

            let btnCol = document.createElement("div")
            btnCol.setAttribute("class", "col-sm-2")

            let subBtn = document.createElement("button")
            subBtn.setAttribute("type", "button")
            subBtn.setAttribute("id", "StatUpdate")
            subBtn.setAttribute("value", reimbursement.reimb_Id);
            subBtn.setAttribute("onclick", "updateReqFunc(this)");
            subBtn.innerText = "Submit"
            subBtn.setAttribute("class","btn btn-secondary")
            btnCol.appendChild(subBtn)

            divRow1.appendChild(btnCol);

            let divRow2 = document.createElement("div")
            divRow2.setAttribute("class", "row border-2 border-top")

            let resolverDiv = document.createElement("div")
            resolverDiv.setAttribute("class", "col")

            let resolverHead = document.createElement("p")
            resolverHead.innerHTML = "Resolver"
            resolverHead.setAttribute("class", "text-muted h-6 p-0")
            resolverDiv.appendChild(resolverHead)

            let resolverBody = document.createElement("p")
            if (reimbursement.resolver === undefined){
                    resolverBody.innerHTML = "-"
                 }else{
                     resolverBody.innerHTML = reimbursement.resolver.username;
                 }
            resolverDiv.appendChild(resolverBody)

            divRow2.appendChild(resolverDiv)

            let resolveDateDiv = document.createElement("div")
            resolveDateDiv.setAttribute("class", "col")

            let resolveDateHead = document.createElement("p")
            resolveDateHead.innerHTML = "Date Resolved"
            resolveDateHead.setAttribute("class", "text-muted h-6 p-0")
            resolveDateDiv.appendChild(resolveDateHead)

            let resolveDateBody = document.createElement("p")
            if (reimbursement.dateResolved === undefined){
                     resolveDateBody.innerHTML = "-"
                 }else {
                     resolveDateBody.innerHTML = reimbursement.dateResolved; 
                 }
            resolveDateDiv.appendChild(resolveDateBody)

            divRow2.appendChild(resolveDateDiv)

            collDiv.appendChild(divRow2)

            let divRow3 = document.createElement("div")
            divRow3.setAttribute("class", "row")

            let descriptionCol = document.createElement("div")
            descriptionCol.setAttribute("class", "col border-top border-2")
            
            let descriptionHeader = document.createElement("p")
            descriptionHeader.innerText = "Description"
            descriptionHeader.setAttribute("class", "text-muted h-6 p-0")
            descriptionCol.appendChild(descriptionHeader)

            let descriptionBody = document.createElement("p")
            descriptionBody.innerHTML = reimbursement.description
            descriptionCol.appendChild(descriptionBody)

            divRow3.appendChild(descriptionCol)

            collDiv.appendChild(divRow3)

            collCell.appendChild(collDiv)

            collRow.appendChild(collCell)

            document.getElementById("reimbursementBody").appendChild(collRow)

            r++
    }
    }
}

function logOutFunc(){
    window.location.replace("http://127.0.0.1:5501/index.html")
}