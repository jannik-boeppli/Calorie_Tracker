import ApiService from './ApiService'
import Food from "../models/Food";
import ConsumedFood from '../models/ConsumedFood';

const baseURL = "/consumedfood"

const FoodService = () => ({
  getConsumedFoodToday: async () => {
      const {data} = await ApiService.get(baseURL + "/now");
      return data;
  },
  getConsumedFoodHistory: async () => {
    const {data} = await ApiService.get(baseURL + "/");
    return data;
},
  addConsumedFood: async (consumedfood: ConsumedFood) => {
    console.log(consumedfood)
    const {data} = await ApiService.post(baseURL + "/", {registeredFood: consumedfood.registeredFood, amount: consumedfood.amount});
    return data;
},
  getRegisteredFood: async () => {
  const {data} = await ApiService.get("/registeredfood");
  return data;
}, 
  registerFood: async (food: Food) => {
    console.log({food: {name: food.name, nutrition: food.nutrition}})
    const {data} = await ApiService.post("/registeredfood", {food: {name: food.name, nutrition: food.nutrition}});
    return data;
}, 
})

export default FoodService
