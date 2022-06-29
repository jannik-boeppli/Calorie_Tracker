import Nutrition from "./Nutrition";

export default interface Food {
    id?: string,
    amount?: number,
    name: string,
    nutrition: Nutrition
}