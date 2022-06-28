import Food from "./Food";
import User from "./User";

export default interface RegisteredFood {
    user: User,
    food: Food
}