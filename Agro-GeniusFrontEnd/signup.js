document.addEventListener("DOMContentLoaded", function () {
    const regionForm = document.querySelector("#regionForm");
    if (regionForm) {
        regionForm.addEventListener("submit", function (event) {
            event.preventDefault();
            addOrUpdateRegion();
        });
    }

    const signUpForm = document.querySelector("#signUpForm");
    if (signUpForm) {
        signUpForm.addEventListener("submit", function (event) {
            event.preventDefault();
            createUser();
        });
    }

    const togglePassword = document.getElementById("togglePassword");
    const passwordField = document.getElementById("password");

    if (togglePassword && passwordField) {
        togglePassword.addEventListener("click", function () {
            const type = passwordField.type === "password" ? "text" : "password";
            passwordField.type = type;
            this.textContent = type === "password" ? "👁️" : "🙈";
        });
    }
});

function addOrUpdateRegion() {
    const regionName = document.getElementById("regionName")?.value.trim();
    const state = document.getElementById("state")?.value.trim();

    if (!regionName || !state) {
        alert("Both region name and state are required!");
        return;
    }

    const regionData = {
        regionName: regionName,
        state: state
    };

    fetch("http://localhost:9070/regions", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            "Authorization": "Basic " + btoa("bijay:bijay121")
        },
        body: JSON.stringify(regionData)
    })
    .then(response => response.json().then(data => ({ status: response.status, body: data })))
    .then(result => {
        console.log("Region creation result:", result);

        if (result.status !== 200 && result.status !== 201) {
            throw new Error("Failed to add region: " + JSON.stringify(result.body));
        }

        localStorage.setItem("regionId", result.body.id);
        alert("Region added successfully!");
        window.location.replace("sign-up.html");
    })
    .catch(error => {
        console.error("Error adding region:", error);
        alert("There was an error adding the region. Please try again.");
    });
}

function createUser() {
    const username = document.querySelector("#name")?.value.trim();
    const useremail = document.querySelector("#email")?.value.trim();
    const userphone = document.querySelector("#Number")?.value.trim();
    const userpassword = document.querySelector("#password")?.value.trim();
    const regionId = localStorage.getItem("regionId");

    if (!regionId) {
        alert("Please add a region first!");
        window.location.replace("add-region.html");
        return;
    }

    if (!username || !useremail || !userphone || !userpassword) {
        alert("All fields are required!");
        return;
    }

    const userData = {
        userName: username,
        email: useremail,
        phone: userphone,
        password: userpassword,
        region: { id: Number(regionId) } 
    };
    

    console.log("Sending user data:", userData);

    fetch("http://localhost:9070/users", { 
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            "Authorization": "Basic " + btoa("bijay:bijay121")
        },
        body: JSON.stringify(userData)
    })
    
    .then(response => {
        if (!response.ok) {
            return response.text().then(text => {
                throw new Error(`HTTP error! Status: ${response.status}, Message: ${text}`);
            });
        }
        return response.json();
    })
    .then(data => {
        alert("Registration successful!");
        localStorage.removeItem("regionId");
        localStorage.setItem("userId", data.id);
        window.location.replace("sign-in.html");
    })
    .catch(error => {
        console.error("Fetch error:", error);
        alert("There was an error during registration. Please try again.");
    });
}