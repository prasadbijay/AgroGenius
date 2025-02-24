// document.addEventListener("DOMContentLoaded", function () {
//     console.log("DOM fully loaded");

//     const form = document.querySelector("#regionForm");
//     const regionNameInput = document.getElementById("regionName");
//     const stateInput = document.getElementById("state");
//     const regionList = document.getElementById("regionList");

//     if (!form || !regionNameInput || !stateInput || !regionList) {
//         console.error("Form or input fields not found! Check HTML IDs.");
//         return;
//     }

//     form.addEventListener("submit", function (event) {
//         event.preventDefault();
//         addOrUpdateRegion();
//     });

//     loadRegions(); // Load existing regions when the page loads
// });

// // 🔹 Create or Update Region
// async function addOrUpdateRegion() {
//     const regionName = document.getElementById("regionName").value.trim();
//     const state = document.getElementById("state").value.trim();
//     const regionId = document.getElementById("regionId").value; // Hidden field for updates

//     if (!regionName || !state) {
//         alert("Both region name and state are required!");
//         return;
//     }

//     const regionData = { regionName, state };
//     const url = regionId ? `http://localhost:9070/regions/${regionId}` : "http://localhost:9070/regions";
//     const method = regionId ? "PUT" : "POST";

//     try {
//         const response = await fetch(url, {
//             method,
//             headers: {
//                 "Content-Type": "application/json",
//                 "Authorization": "Basic " + btoa("bijay:bijay121"),
//             },
//             body: JSON.stringify(regionData),
//         });

//         if (!response.ok) {
//             throw new Error(`Failed to ${regionId ? "update" : "add"} region`);
//         }

//         const data = await response.json();
//         console.log(`Region ${regionId ? "updated" : "added"}:`, data);
//         alert(`Region ${regionId ? "updated" : "added"} successfully!`);
        
//         form.reset(); // Clear the form
//         document.getElementById("regionId").value = ""; // Reset hidden field
//         loadRegions(); // Refresh region list
//     } catch (error) {
//         console.error("Error:", error);
//         alert("There was an error processing the request. Please try again.");
//     }
// }

// // 🔹 Read and Display All Regions
// async function loadRegions() {
//     try {
//         const response = await fetch("http://localhost:9070/regions", {
//             headers: {
//                 "Authorization": "Basic " + btoa("bijay:bijay121"),
//             },
//         });

//         if (!response.ok) {
//             throw new Error("Failed to fetch regions");
//         }

//         const regions = await response.json();
//         const regionList = document.getElementById("regionList");
//         regionList.innerHTML = ""; // Clear existing list

//         regions.forEach(region => {
//             const li = document.createElement("li");
//             li.innerHTML = `
//                 ${region.regionName} (${region.state})
//                 <button onclick="editRegion(${region.id}, '${region.regionName}', '${region.state}')">Edit</button>
//                 <button onclick="deleteRegion(${region.id})">Delete</button>
//             `;
//             regionList.appendChild(li);
//         });
//     } catch (error) {
//         console.error("Error fetching regions:", error);
//     }
// }

// // 🔹 Populate Form for Editing
// function editRegion(id, name, state) {
//     document.getElementById("regionId").value = id; // Store ID for update
//     document.getElementById("regionName").value = name;
//     document.getElementById("state").value = state;
// }

// // 🔹 Delete Region
// async function deleteRegion(regionId) {
//     if (!confirm("Are you sure you want to delete this region?")) return;

//     try {
//         const response = await fetch(`http://localhost:9070/regions/${regionId}`, {
//             method: "DELETE",
//             headers: {
//                 "Authorization": "Basic " + btoa("bijay:bijay121"),
//             },
//         });

//         if (!response.ok) {
//             throw new Error("Failed to delete region");
//         }

//         alert("Region deleted successfully!");
//         loadRegions(); // Refresh the list
//     } catch (error) {
//         console.error("Error deleting region:", error);
//         alert("There was an error deleting the region. Please try again.");
//     }
// }
