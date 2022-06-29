import { Col, Row } from "antd";
import React, { useEffect, useState } from "react";
import Food from "../../../models/Food";
import RegisteredFood from "../../../models/RegisteredFood";
import UserGoal from "../../../models/UserGoal";
import FoodService from "../../../services/FoodService";
import GoalService from "../../../services/GoalService";
import useWindowDimensions from "../../../utils/WindowDimensions";
import ConsumedFoodDashboard from "../../organisms/ConsumedFoodDashboard/ConsumedFoodDashboard";
import ConsumptionHistory from "../../organisms/ConsumptionHistory/ConsumptionHistory";
import NutritionDashboard from "../../organisms/NutritionDashboard/NutritionDashboard";

export default function LandingPage() {
  const {width, height} = useWindowDimensions()
  const [foodToDisplay, setFoodToDisplay] = useState<Food[]>([]);
  const [goals, setGoals] = useState<UserGoal>({bodyMass: {weightInKg: 0}, nutrition: {calories: 0, carbs: 0, protein: 0, fat: 0}})

  useEffect(() => {
    FoodService()
      .getConsumedFoodToday()
      .then((value) =>{
        setFoodToDisplay(
          value.map(
            ({ registeredFood, amount }: { registeredFood: RegisteredFood, amount: number }) => {return {...registeredFood.food, amount: amount}}
          )
        )}
      );
      GoalService()
      .getUserGoal()
      .then((data) => {
        setGoals({ ...data.goal });
      });
  }, []);

  return (
    <div style={width < 1050 ? {} : {display: "flex", flexFlow: "column", height: "100vh"}}>
      <Row style={width < 1050 || height < 1050 ? {} : {height: "30vh"}}>
        <Col span={24}>
          <NutritionDashboard goal={goals} food={foodToDisplay}  />
        </Col>
      </Row>
      <Row style={width < 1050 || height < 1050 ? {height: (height - 25) + "px"} : {height: "70vh"}} >
        <Col style={{height: "100%"}} span={width < 1050 ? 24 : 12}>
          <ConsumedFoodDashboard food={foodToDisplay} />
        </Col>
        <Col style={{height: "100%"}} span={width < 1050 ? 24 : 12}>
          <ConsumptionHistory />
        </Col>
      </Row>
    </div>
  );
}
