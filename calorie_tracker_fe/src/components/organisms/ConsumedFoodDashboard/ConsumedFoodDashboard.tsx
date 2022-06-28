import { Card, Typography } from "antd";
import React from "react";
import { useNavigate } from "react-router-dom";
import Food from "../../../models/Food";
import useWindowDimensions from "../../../utils/WindowDimensions";
import AddButton from "../../atoms/AddButton/AddButton";
import LineDivider from "../../atoms/LineDivider/LineDivider";
import ConsumedFoodCard from "../../molecules/ConsumedFoodCard/ConsumedFoodCard";
import "./ConsumedFoodDashboard.css";

interface PropsType {
  food: Food[]
}

export default function ConsumedFoodDashboard({food}: PropsType) {
  const { Title } = Typography;
  const { width, height } = useWindowDimensions();
  const isMobile = width < 1050;
  const navigation = useNavigate();
  

  return (
    <Card
      style={
        isMobile
          ? {}
          : height < 1050
          ? { marginTop: "2em" }
          : { marginTop: "4em" }
      }
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
          {food.map((singleFood) => (
            <ConsumedFoodCard
              amount={singleFood.amount}
              name={singleFood.name}
              nutrition={singleFood.nutrition}
            />
          ))}
        </div>
        <div>
          <LineDivider />
          <AddButton onClick={() => navigation("/food")} />
        </div>
      </div>
    </Card>
  );
}
