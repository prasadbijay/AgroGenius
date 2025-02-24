const BASE_URL = "http://localhost:9070/api/admins";  // ✅ Corrected API base URL
const username = "bijay";
const password = "bijay121";

// ✅ Add Authentication Headers
const headers = new Headers({
    "Authorization": "Basic " + btoa(username + ":" + password),
    "Content-Type": "application/json"
});

document.addEventListener("DOMContentLoaded", () => {
    loadAdmins();
    document.getElementById("adminForm").addEventListener("submit", handleFormSubmit);
});

// ✅ Load Admins and Display in Table
async function loadAdmins() {
    const admins = await getAllAdmins();
    const tableBody = document.getElementById("adminTableBody");
    tableBody.innerHTML = "";

    admins.forEach(admin => {
        const row = `<tr>
            <td>${admin.id}</td>
            <td>${admin.name}</td>
            <td>${admin.email}</td>
            <td>${admin.phone}</td>
            <td>
                <button onclick="editAdmin(${admin.id})">Edit</button>
                <button onclick="deleteAdmin(${admin.id})">Delete</button>
            </td>
        </tr>`;
        tableBody.innerHTML += row;
    });
}

// ✅ Handle Form Submission (Create or Update)
async function handleFormSubmit(event) {
    event.preventDefault();

    const id = document.getElementById("adminId").value;
    const name = document.getElementById("adminName").value;
    const email = document.getElementById("adminEmail").value;
    const password = document.getElementById("adminPassword").value;
    const phone = document.getElementById("adminPhone").value;

    const adminData = { name, email, password, phone };

    try {
        if (id) {
            await updateAdmin(id, adminData);
        } else {
            await createAdmin(adminData);
        }

        document.getElementById("adminForm").reset();
        loadAdmins();  // ✅ Refresh table after adding/updating admin
    } catch (error) {
        console.error("Error handling form submission:", error);
    }
}

// ✅ Edit Admin (Prefill Form)
async function editAdmin(id) {
    try {
        const admin = await getAdminById(id);
        if (!admin) return;

        document.getElementById("adminId").value = admin.id;
        document.getElementById("adminName").value = admin.name;
        document.getElementById("adminEmail").value = admin.email;
        document.getElementById("adminPhone").value = admin.phone;
    } catch (error) {
        console.error("Error editing admin:", error);
    }
}

// ✅ Fetch All Admins
async function getAllAdmins() {
    try {
        const response = await fetch(`${BASE_URL}`, { method: "GET", headers });

        if (!response.ok) throw new Error("Failed to fetch admins");
        return await response.json();
    } catch (error) {
        console.error("Error fetching admins:", error);
        return [];
    }
}

// ✅ Fetch Admin by ID
async function getAdminById(id) {
    try {
        const response = await fetch(`${BASE_URL}/${id}`, { method: "GET", headers });

        if (!response.ok) throw new Error("Admin not found");
        return await response.json();
    } catch (error) {
        console.error("Error fetching admin:", error);
    }
}

// ✅ Create a New Admin
async function createAdmin(adminData) {
    try {
        const response = await fetch(`${BASE_URL}`, {
            method: "POST",
            headers,
            body: JSON.stringify(adminData),
        });

        if (!response.ok) throw new Error("Failed to create admin");
        return await response.json();
    } catch (error) {
        console.error("Error creating admin:", error);
    }
}

// ✅ Update an Existing Admin
async function updateAdmin(id, adminData) {
    try {
        const response = await fetch(`${BASE_URL}/${id}`, {
            method: "PUT",
            headers,
            body: JSON.stringify(adminData),
        });

        if (!response.ok) throw new Error("Failed to update admin");
        return await response.json();
    } catch (error) {
        console.error("Error updating admin:", error);
    }
}

// ✅ Delete an Admin
async function deleteAdmin(id) {
    if (!confirm("Are you sure you want to delete this admin?")) return;

    try {
        const response = await fetch(`${BASE_URL}/${id}`, { method: "DELETE", headers });

        if (!response.ok) throw new Error("Failed to delete admin");
        console.log(`Admin with ID ${id} deleted successfully`);
        loadAdmins();  // ✅ Refresh table after deleting admin
    } catch (error) {
        console.error("Error deleting admin:", error);
    }
}
