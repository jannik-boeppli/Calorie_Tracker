import { Card, Typography } from "antd";
import React from "react";
import AddButton from "../../atoms/AddButton/AddButton";
import LineDivider from "../../atoms/LineDivider/LineDivider";
import ConsumedFoodCard from "../../molecules/ConsumedFoodCard/ConsumedFoodCard";
import "./ConsumedFoodDashboard.css"

export default function ConsumedFoodDashboard() {
  const { Title } = Typography;
  return (
    <Card className="consumed-food-dashboard-card">
      <Title style={{ color: "white" }} level={2}>
        Food Consumed Today
      </Title>
      <LineDivider />
      <ConsumedFoodCard
        amount={250}
        name="Spaghetti Bolognese"
        calories={1600}
        protein={40}
        fat={28}
        carbs={53}
      />
      <LineDivider />
      <AddButton />
    </Card>
  );
}
