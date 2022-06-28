import { REFRESH_TOKEN } from '../config/constants/Cookies';
import User from '../models/User';
import CookieUtility from '../utils/CookieUtility';
import ApiService from './ApiService'

const baseURL = "/user"

const UserService = () => ({
  getCurrentUser: async () => {
      const {data} = await ApiService.get(baseURL + "/");
      return data;
  },
  updateUser: async (user: User) => {
    const {data} = await ApiService.update(baseURL + "/", user);
    return data;
} 
})

export default UserService
