document.addEventListener("DOMContentLoaded", function () {
    fetchCrops(); 
    document.querySelector("#cropForm").addEventListener("submit", function (event) {
        event.preventDefault();
        addOrUpdateCrop();
    });
});

const API_URL = "http://localhost:9070/crops";
const AUTH_HEADER = {
    "Content-Type": "application/json",
    "Authorization": "Basic " + btoa("bijay:bijay121")
};

// Fetch existing crops
function fetchCrops() {
    fetch(API_URL, { headers: AUTH_HEADER })
        .then(response => response.json())
        .then(crops => {
            const tableBody = document.getElementById("cropTableBody");
            tableBody.innerHTML = "";

            crops.forEach(crop => {
                const row = document.createElement("tr");
                row.innerHTML = `
                    <td>${crop.id}</td>
                    <td>${crop.cropName}</td>
                    <td>${crop.otherCrop ? crop.otherCrop : "N/A"}</td>
                    <td>
                        <button onclick="editCrop(${crop.id}, '${crop.cropName}', '${crop.otherCrop || ''}')">Edit</button>
                        <button onclick="deleteCrop(${crop.id})">Delete</button>
                    </td>
                `;
                tableBody.appendChild(row);
            });
        })
        .catch(error => console.error("Error fetching crops:", error));
}

// Add or Update crop
function addOrUpdateCrop() {
    const cropId = document.getElementById("cropId").value;
    const cropName = document.getElementById("cropName").value.trim();
    const otherCrop = document.getElementById("otherCrop").value.trim();

    if (!cropName) {
        alert("Crop name is required!");
        return;
    }

    const cropData = { cropName, otherCrop };
    const method = cropId ? "PUT" : "POST";
    const url = cropId ? `${API_URL}/${cropId}` : API_URL;

    fetch(url, {
        method: method,
        headers: AUTH_HEADER,
        body: JSON.stringify(cropData)
    })
    .then(response => {
        if (!response.ok) throw new Error(`Failed to ${cropId ? "update" : "add"} crop`);
        return response.json();
    })
    .then(() => {
        alert(`Crop ${cropId ? "updated" : "added"} successfully!`);
        document.getElementById("cropForm").reset();
        document.getElementById("cropId").value = "";
        document.getElementById("submitBtn").textContent = "Create Crop"; 
        fetchCrops(); 
    })
    .catch(error => alert(`Error: ${error.message}`));
}

// Edit crop (populate form)
function editCrop(id, cropName, otherCrop) {
    document.getElementById("cropId").value = id;
    document.getElementById("cropName").value = cropName;
    document.getElementById("otherCrop").value = otherCrop;
    document.getElementById("submitBtn").textContent = "Update Crop";
}

// Delete crop
function deleteCrop(id) {
    if (!confirm("Are you sure you want to delete this crop?")) return;

    fetch(`${API_URL}/${id}`, {
        method: "DELETE",
        headers: AUTH_HEADER
    })
    .then(response => {
        if (!response.ok) throw new Error("Failed to delete crop");
        alert("Crop deleted successfully!");
        fetchCrops(); 
    })
    .catch(error => alert(`Error: ${error.message}`));
}
