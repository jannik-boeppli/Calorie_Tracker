import { Card, Empty, Typography } from "antd";
import React from "react";
import { useNavigate } from "react-router-dom";
import Food from "../../../models/Food";
import useWindowDimensions from "../../../utils/WindowDimensions";
import AddButton from "../../atoms/AddButton/AddButton";
import LineDivider from "../../atoms/LineDivider/LineDivider";
import ConsumedFoodCard from "../../molecules/ConsumedFoodCard/ConsumedFoodCard";
import "./ConsumedFoodDashboard.css";

interface PropsType {
  food: Food[];
}

export default function ConsumedFoodDashboard({ food }: PropsType) {
  const { Title } = Typography;
  const { width, height } = useWindowDimensions();
  const isMobile = width < 1050;
  const navigation = useNavigate();

  const renderCards = () => {
    if (food.length === 0) {
      return (
        <Empty
          image={Empty.PRESENTED_IMAGE_SIMPLE}
          imageStyle={{ width: "100%", height: "100%", margin: "auto" }}
          description={
            <Title
              style={{ color: "#389e0d" }}
              underline
              onClick={() => navigation("/food")}
              level={5}
            >
              No entries yet, create one!
            </Title>
          }
        />
      );
    }
    return food.map((singleFood) => (
      <ConsumedFoodCard
        amount={singleFood.amount}
        name={singleFood.name}
        nutrition={singleFood.nutrition}
      />
    ));
  };

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
        <div style={{ flex: "1", overflow: "auto" }}>{renderCards()}</div>
        <div>
          <LineDivider />
          <AddButton onClick={() => navigation("/food")} />
        </div>
      </div>
    </Card>
  );
}
