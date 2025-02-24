document.addEventListener("DOMContentLoaded", function () {
    fetchUsers(); 
    document.querySelector("#userForm").addEventListener("submit", function (event) {
        event.preventDefault();
        addOrUpdateUser();
    });
});

const API_URL = "http://localhost:9070/users";
const AUTH_HEADER = {
    "Content-Type": "application/json",  
    "Authorization": "Basic " + btoa("bijay:bijay121")
};

function fetchUsers() {
    fetch(API_URL, { headers: AUTH_HEADER })
        .then(response => {
            if (!response.ok) throw new Error("Failed to fetch users");
            return response.json();
        })
        .then(users => {
            const tableBody = document.getElementById("userTableBody");
            tableBody.innerHTML = "";

            users.forEach(user => {
                const row = document.createElement("tr");
                row.innerHTML = `
                    <td>${user.id}</td>
                    <td>${user.userName}</td>
                    <td>${user.email}</td>
                    <td>${user.phone}</td>
                    <td>${user.region ? user.region.regionName : "N/A"}</td>
                    <td>
                        <button onclick="editUser(${user.id}, '${user.userName}', '${user.email}', '${user.phone}', ${user.region ? user.region.id : null})">Edit</button>
                        <button onclick="deleteUser(${user.id})">Delete</button>
                    </td>
                `;
                tableBody.appendChild(row);
            });
        })
        .catch(error => console.error("Error fetching users:", error));
}

function addOrUpdateUser() {
    const userId = document.getElementById("userId").value;
    const userName = document.getElementById("userName").value.trim();
    const email = document.getElementById("email").value.trim();
    const phone = document.getElementById("phone").value.trim();
    const password = document.getElementById("password").value.trim(); 
    const regionId = document.getElementById("regionId").value;

    if (!userName || !email || !phone || !password || !regionId) {
        alert("All fields are required!");
        return;
    }

    const userData = {
        userName,
        email,
        phone,
        password,
        region: { id: parseInt(regionId) }
    };

    const method = userId ? "PUT" : "POST";
    const url = userId ? `${API_URL}/${userId}` : API_URL;

    fetch(url, {
        method: method,
        headers: AUTH_HEADER,
        body: JSON.stringify(userData)
    })
    .then(response => {
        if (!response.ok) return response.json().then(err => { throw new Error(err.message || "Failed to save user"); });
        return response.json();
    })
    .then(() => {
        alert(`User ${userId ? "updated" : "added"} successfully!`);
        document.getElementById("userForm").reset();
        document.getElementById("userId").value = "";
        document.getElementById("submitBtn").textContent = "Create User";
        fetchUsers();
    })
    .catch(error => alert(`Error: ${error.message}`));
}

function editUser(id, userName, email, phone, regionId) {
    document.getElementById("userId").value = id;
    document.getElementById("userName").value = userName;
    document.getElementById("email").value = email;
    document.getElementById("phone").value = phone;
    document.getElementById("regionId").value = regionId || "";
    
    document.getElementById("password").value = ""; 
    document.getElementById("submitBtn").textContent = "Update User";
}

function deleteUser(id) {
    if (!confirm("Are you sure you want to delete this user?")) return;

    fetch(`${API_URL}/${id}`, {
        method: "DELETE",
        headers: AUTH_HEADER
    })
    .then(response => {
        if (!response.ok) throw new Error("Failed to delete user");
        alert("User deleted successfully!");
        fetchUsers();
    })
    .catch(error => alert(`Error: ${error.message}`));
}
