document.addEventListener("DOMContentLoaded", function () {
    const form = document.getElementById("weatherForm");
    const resultsContainer = document.getElementById("recommendationResults");
    const body = document.body; // Select the entire body to apply blur

    form.addEventListener("submit", async function (event) {
        event.preventDefault(); // Prevent form submission

        // Get input values
        const temperature = parseFloat(document.getElementById("temperature").value);
        const soilType = document.getElementById("soilType").value.trim();
        const weatherCondition = document.getElementById("weatherCondition").value.trim();

        // Validate input before sending request
        if (isNaN(temperature) || !soilType || !weatherCondition) {
            alert("Please enter valid weather data.");
            return;
        }

        // Clear previous results
        resultsContainer.innerHTML = "";
        body.classList.remove("blur-effect"); // Remove blur if it was applied earlier

        // API URL (Ensure your backend is running!)
        const apiUrl = `http://localhost:9070/api/recommendation/crops?temperature=${temperature}&soilType=${soilType}&weatherCondition=${weatherCondition}`;

        try {
            const response = await fetch(apiUrl);

            if (!response.ok) {
                if (response.status === 404) {
                    throw new Error("No recommendations found for this weather data.");
                }
                throw new Error(`HTTP error! Status: ${response.status}`);
            }

            const data = await response.json();

            // Check if recommendations exist
            if (data.length > 0) {
                displayRecommendations(data);
            } else {
                resultsContainer.innerHTML = "<p>No recommendations found.</p>";
            }
        } catch (error) {
            console.error("Error fetching recommendations:", error);
            resultsContainer.innerHTML = `<p style="color:red;">${error.message}</p>`;
        }
    });

    function displayRecommendations(crops) {
        body.classList.add("blur-effect"); // Apply blur effect

        resultsContainer.innerHTML = `
            <div class="recommendation-box">
                <h3>Recommended Crops:</h3>
                <ul>
                    ${crops.map(crop => `<li>${crop}</li>`).join("")}
                </ul>
                <button id="closeRecommendations">Close</button>
            </div>
        `;

        // Add event listener to close button
        document.getElementById("closeRecommendations").addEventListener("click", function () {
            resultsContainer.innerHTML = ""; // Hide recommendations
            body.classList.remove("blur-effect"); // Remove blur effect
        });
    }
});
