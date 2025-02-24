document.addEventListener("DOMContentLoaded", function () {
    fetchWeatherData();
    document.querySelector("#weatherForm").addEventListener("submit", function (event) {
        event.preventDefault();
        addOrUpdateWeather();
    });
});

const API_URL = "http://localhost:9070/weather";
const AUTH_HEADER = {
    "Content-Type": "application/json",
    "Authorization": "Basic " + btoa("bijay:bijay121") // Replace with real authentication
};

// Fetch and display weather data
function fetchWeatherData() {
    fetch(API_URL, { headers: AUTH_HEADER })
        .then(response => response.json())
        .then(weatherList => {
            const tableBody = document.getElementById("weatherTableBody");
            tableBody.innerHTML = "";

            weatherList.forEach(weather => {
                const row = document.createElement("tr");
                row.innerHTML = `
                    <td>${weather.id}</td>
                    <td>${weather.temperature}°C</td>
                    <td>${weather.rangeTemperature}°C</td>
                    <td>${weather.soilType}</td>
                    <td>${weather.weatherCondition}</td>
                    <td>
                        <button onclick="editWeather(${weather.id}, ${weather.temperature}, ${weather.rangeTemperature}, '${weather.soilType}', '${weather.weatherCondition}')">Edit</button>
                        <button onclick="deleteWeather(${weather.id})">Delete</button>
                    </td>
                `;
                tableBody.appendChild(row);
            });
        })
        .catch(error => console.error("Error fetching weather data:", error));
}

// Add or Update weather data
function addOrUpdateWeather() {
    const weatherId = document.getElementById("weatherId").value;
    const temperature = document.getElementById("temperature").value.trim();
    const rangeTemperature = document.getElementById("rangeTemperature").value.trim();
    const soilType = document.getElementById("soilType").value.trim();
    const weatherCondition = document.getElementById("weatherCondition").value.trim();

    if (!temperature || !rangeTemperature || !soilType || !weatherCondition) {
        alert("All fields are required!");
        return;
    }

    const weatherData = { temperature, rangeTemperature, soilType, weatherCondition };
    const method = weatherId ? "PUT" : "POST";
    const url = weatherId ? `${API_URL}/${weatherId}` : API_URL;

    fetch(url, {
        method: method,
        headers: AUTH_HEADER,
        body: JSON.stringify(weatherData)
    })
    .then(response => {
        if (!response.ok) throw new Error(`Failed to ${weatherId ? "update" : "add"} weather data`);
        return response.json();
    })
    .then(() => {
        alert(`Weather data ${weatherId ? "updated" : "added"} successfully!`);
        document.getElementById("weatherForm").reset();
        document.getElementById("weatherId").value = "";
        document.getElementById("submitBtn").textContent = "Create Weather Data"; 
        fetchWeatherData(); 
    })
    .catch(error => alert(`Error: ${error.message}`));
}

// Edit weather data (populate form)
function editWeather(id, temperature, rangeTemperature, soilType, weatherCondition) {
    document.getElementById("weatherId").value = id;
    document.getElementById("temperature").value = temperature;
    document.getElementById("rangeTemperature").value = rangeTemperature;
    document.getElementById("soilType").value = soilType;
    document.getElementById("weatherCondition").value = weatherCondition;
    document.getElementById("submitBtn").textContent = "Update Weather Data";
}

// Delete weather data
function deleteWeather(id) {
    if (!confirm("Are you sure you want to delete this weather data?")) return;

    fetch(`${API_URL}/${id}`, {
        method: "DELETE",
        headers: AUTH_HEADER
    })
    .then(response => {
        if (!response.ok) throw new Error("Failed to delete weather data");
        alert("Weather data deleted successfully!");
        fetchWeatherData(); 
    })
    .catch(error => alert(`Error: ${error.message}`));
}
