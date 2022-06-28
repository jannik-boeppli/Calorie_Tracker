import ApiService from './ApiService'

const baseURL = "/consumedfood"

const FoodService = () => ({
  getConsumedFoodToday: async () => {
      const {data} = await ApiService.get(baseURL + "/now");
      return data;
  },
  getConsumedFoodHistory: async () => {
    const {data} = await ApiService.get(baseURL + "/");
    return data;
} 
})

export default FoodService
