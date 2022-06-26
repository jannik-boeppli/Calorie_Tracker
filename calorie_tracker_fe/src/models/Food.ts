import Nutrition from "./Nutrition";

export default interface Food extends Nutrition {
    id?: string,
    amount: number,
    name: string,
}