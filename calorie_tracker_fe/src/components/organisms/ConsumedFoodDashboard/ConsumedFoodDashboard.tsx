import { Card, Typography } from "antd";
import React, { useState } from "react";
import Food from "../../../models/Food";
import useWindowDimensions from "../../../utils/WindowDimensions";
import AddButton from "../../atoms/AddButton/AddButton";
import LineDivider from "../../atoms/LineDivider/LineDivider";
import ConsumedFoodCard from "../../molecules/ConsumedFoodCard/ConsumedFoodCard";
import "./ConsumedFoodDashboard.css";

export default function ConsumedFoodDashboard() {
  const { Title } = Typography;
  const isMobile = useWindowDimensions().width < 1050

  const [foodToDisplay, setFoodToDisplay] = useState<Food[]>([
    {
      amount: 250,
      name: "Spaghetti Bolognese",
      calories: 1600,
      protein: 40,
      fat: 28,
      carbs: 53,
    },
    {
      amount: 250,
      name: "Spaghetti Bolognese",
      calories: 1600,
      protein: 40,
      fat: 28,
      carbs: 53,
    },
    {
      amount: 250,
      name: "Spaghetti Bolognese",
      calories: 1600,
      protein: 40,
      fat: 28,
      carbs: 53,
    },
    {
      amount: 250,
      name: "Spaghetti Bolognese",
      calories: 1600,
      protein: 40,
      fat: 28,
      carbs: 53,
    },
    {
      amount: 250,
      name: "Spaghetti Bolognese",
      calories: 1600,
      protein: 40,
      fat: 28,
      carbs: 53,
    },
  ]);

  return (
    <Card
    style={isMobile ? {} : {marginTop: "4em"}}
      bodyStyle={{ height: "100%" }}
      className="consumed-food-dashboard-card"
    >
      <div style={{ height: "100%", display: "flex", flexDirection: "column" }}>
        <div>
          <Title style={{ color: "white" }} level={2}>
            Food Consumed Today
          </Title>
          <LineDivider />
        </div>
        <div style={{ flex: "1", overflow: "auto" }}>
          {foodToDisplay.map((food) => (
            <ConsumedFoodCard
            amount={food.amount}
            name={food.name}
            calories={food.calories}
            protein={food.protein}
            fat={food.fat}
            carbs={food.carbs}
          />
          ))}
        </div>
        <div>
          <LineDivider />
          <AddButton />
        </div>
      </div>
    </Card>
  );
}
