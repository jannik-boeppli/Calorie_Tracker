import UserGoal from '../models/UserGoal';
import ApiService from './ApiService'

const baseURL = "/usergoal"

const GoalService = () => ({
  getUserGoal: async () => {
      const {data} = await ApiService.get(baseURL + "/open");
      return data;
  },
  createUserGoal: async (userGoal: UserGoal) => {
    const {data} = await ApiService.post(baseURL + "/", {goal: userGoal});
    return data;
} 
})

export default GoalService
