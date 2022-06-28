import BodyMass from "./BodyMass";
import Nutrition from "./Nutrition";

export default interface UserGoal {
    nutrition: Nutrition,
    bodyMass: BodyMass,
}