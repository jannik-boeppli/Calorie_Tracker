import Nutrition from "./Nutrition";

export default interface Food extends Nutrition {
    amount: number,
    name: string,
}