document.addEventListener("DOMContentLoaded", function () {
    fetchRegions();
    document.querySelector("#regionForm").addEventListener("submit", function (event) {
        event.preventDefault();
        addOrUpdateRegion();
    });
});

const API_URL = "http://localhost:9070/regions";
const AUTH_HEADER = {
    "Content-Type": "application/json",
    "Authorization": "Basic " + btoa("bijay:bijay121")
};

// Fetch existing regions
function fetchRegions() {
    fetch(API_URL, { headers: AUTH_HEADER })
        .then(response => {
            if (!response.ok) throw new Error("Failed to fetch regions");
            return response.json();
        })
        .then(regions => displayRegions(regions))
        .catch(error => console.error("Error fetching regions:", error));
}

// Display regions in the table
function displayRegions(regions) {
    const tableBody = document.getElementById("regionTableBody");
    tableBody.innerHTML = ""; 

    regions.forEach(region => {
        const row = document.createElement("tr");

        const idCell = document.createElement("td");
        idCell.textContent = region.id;

        const nameCell = document.createElement("td");
        nameCell.textContent = region.regionName;

        const stateCell = document.createElement("td");
        stateCell.textContent = region.state;

        const actionCell = document.createElement("td");
        const editButton = document.createElement("button");
        editButton.textContent = "Edit";
        editButton.onclick = () => editRegion(region.id, region.regionName, region.state);

        const deleteButton = document.createElement("button");
        deleteButton.textContent = "Delete";
        deleteButton.onclick = () => deleteRegion(region.id);

        actionCell.appendChild(editButton);
        actionCell.appendChild(deleteButton);

        row.appendChild(idCell);
        row.appendChild(nameCell);
        row.appendChild(stateCell);
        row.appendChild(actionCell);

        tableBody.appendChild(row);
    });
}

// Create or update region
function addOrUpdateRegion() {
    const regionId = document.getElementById("regionId").value;
    const regionName = document.getElementById("regionName").value.trim();
    const state = document.getElementById("state").value.trim();

    if (!regionName || !state) {
        alert("Both region name and state are required!");
        return;
    }

    const regionData = { regionName, state };
    if (regionId) regionData.id = regionId; 

    fetch(regionId ? `${API_URL}/${regionId}` : API_URL, {
        method: regionId ? "PUT" : "POST",
        headers: AUTH_HEADER,
        body: JSON.stringify(regionData)
    })
    .then(response => {
        if (!response.ok) throw new Error(`Failed to ${regionId ? "update" : "add"} region`);
        return response.json();
    })
    .then(() => {
        alert(`Region ${regionId ? "updated" : "added"} successfully!`);
        resetForm();
        fetchRegions(); 
    })
    .catch(error => alert(`Error: ${error.message}`));
}

// Load data into the form for editing
function editRegion(id, regionName, state) {
    document.getElementById("regionId").value = id;
    document.getElementById("regionName").value = regionName;
    document.getElementById("state").value = state;
    document.getElementById("submitBtn").textContent = "Update Region";
}

// Delete region
function deleteRegion(id) {
    if (!confirm("Are you sure you want to delete this region?")) return;

    fetch(`${API_URL}/${id}`, { method: "DELETE", headers: AUTH_HEADER })
    .then(response => {
        if (!response.ok) throw new Error("Failed to delete region");
        alert("Region deleted successfully!");
        fetchRegions();
    })
    .catch(error => alert(`Error: ${error.message}`));
}

// Reset form after adding/updating
function resetForm() {
    document.getElementById("regionForm").reset();
    document.getElementById("regionId").value = "";
    document.getElementById("submitBtn").textContent = "Create Region"; 
}
