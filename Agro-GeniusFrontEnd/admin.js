const BASE_URL = "http://localhost:9070"; 

const apiRequest = async (endpoint, method = "GET", data = null) => {
    const options = {
        method,
        headers: { "Content-Type": "application/json" },
        body: data ? JSON.stringify(data) : null,
    };

    try {
        const response = await fetch(`${BASE_URL}${endpoint}`, options);
        const result = await response.json();

        if (!response.ok) {
            throw new Error(result.message || `HTTP Error! Status: ${response.status}`);
        }

        return result;
    } catch (error) {
        console.error("API Request Failed:", error.message);
        return { success: false, error: error.message };
    }
};

const createAPI = (resource) => ({
    create: (data) => apiRequest(`/${resource}`, "POST", data),
    getAll: () => apiRequest(`/${resource}`),
    update: (id, data) => apiRequest(`/${resource}/${id}`, "PUT", data),
    delete: (id) => apiRequest(`/${resource}/${id}`, "DELETE"),
});

const AdminAPI = createAPI("admin");
const CropAPI = createAPI("crop");
const RecommendationAPI = createAPI("recommendation");
const WeatherAPI = createAPI("weather");
const RegionAPI = createAPI("region");
const UserAPI = createAPI("user");
window.AdminAPI = createAPI("admin");
window.CropAPI = createAPI("crop");
window.RecommendationAPI = createAPI("recommendation");
window.WeatherAPI = createAPI("weather");
window.RegionAPI = createAPI("region");
window.UserAPI = createAPI("user");

