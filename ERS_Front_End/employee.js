const url = "http://localhost:8090/"
const usern = localStorage["currentUser"];
const user_id = localStorage["user_id"]

console.log(user_id)

window.addEventListener('load', populateReqFunc)

document.getElementById("submitRequest").addEventListener("click", newRequestFunc)
document.getElementById("submitFilter").addEventListener("click", filterStatusFunc)
document.getElementById("logout").addEventListener("click", logOutFunc)
document.getElementById("refresh").addEventListener("click", populateReqFunc)

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

    let response = await fetch(url + "reimbursements/" + user_id, {
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


async function filterStatusFunc(){

    let curFilter = document.getElementById("statusFilter").value
    let stat = {
        status_id:curFilter
    }

    let path = url + "reimbursements" + "/" + user_id + "/" + curFilter
    let response = await fetch(path, {
        method:"GET",
        credentials: "include"
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

