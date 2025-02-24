document.addEventListener("DOMContentLoaded", function () {
    const weatherDataDropdown = document.getElementById("weatherData");
    const recommendationForm = document.getElementById("recommendation-form");
    const responseMessage = document.getElementById("response-message");

    fetch("http://localhost:9070/weather")
        .then(response => response.json())
        .then(data => {
            weatherDataDropdown.innerHTML = '<option value="">Select Weather Data</option>';
            data.forEach(weather => {
                let option = document.createElement("option");
                option.value = weather.id;
                option.textContent = `ID: ${weather.id} - ${weather.soilType}, ${weather.weatherCondition}, ${weather.temperature}°C`;
                weatherDataDropdown.appendChild(option);
            });
        })
        .catch(error => {
            console.error("Error fetching weather data:", error);
            weatherDataDropdown.innerHTML = '<option value="">Failed to load</option>';
        });

    recommendationForm.addEventListener("submit", function (event) {
        event.preventDefault();

        const selectedWeatherId = weatherDataDropdown.value;
        const recommendationText = document.getElementById("recommendationText").value.trim();

        if (!selectedWeatherId) {
            responseMessage.textContent = "❌ Please select weather data!";
            return;
        }

        if (recommendationText.length < 20) {
            responseMessage.textContent = "❌ Recommendation must be at least 20 characters.";
            return;
        }

        const recommendationData = {
            weatherDataId: selectedWeatherId,
            recommendationText: recommendationText
        };

        fetch("http://localhost:9070/api/recommendation", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                "Authorization": "Basic " + btoa("bijay:bijay121") 
            },
            body: JSON.stringify({
                weatherData: { id: 1 }, 
                recommendationText: "New Recommendation"
            })
        })
        .then(response => {
            if (!response.ok) {
                throw new Error(`HTTP error! Status: ${response.status}`);
            }
            return response.json(); 
        })
        .then(data => console.log("✅ Recommendation Saved:", data))
        .catch(error => console.error("❌ Error saving recommendation:", error));        
    });
});
