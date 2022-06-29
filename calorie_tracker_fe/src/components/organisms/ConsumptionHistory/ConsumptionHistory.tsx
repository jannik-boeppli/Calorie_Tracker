import { Card, Typography } from "antd";
import React, { useEffect, useState } from "react";
import ConsumptionDay from "../../../models/ConsumptionDay";
import Nutrition from "../../../models/Nutrition";
import FoodService from "../../../services/FoodService";
import useWindowDimensions from "../../../utils/WindowDimensions";
import LineDivider from "../../atoms/LineDivider/LineDivider";
import ConsumptionDayCard from "../../molecules/ConsumptionDayCard/ConsumptionDayCard";
import "./ConsumptionHistory.css";

export default function ConsumptionHistory() {
  const { Title } = Typography;
  const { width, height } = useWindowDimensions();
  const isMobile = width < 1050;

  const [daysToDisplay, setDaysToDisplay] = useState<ConsumptionDay[]>([]);

  useEffect(() => {
    FoodService()
      .getConsumedFoodHistory()
      .then((data) => {
        console.log(data);
        setDaysToDisplay(
          data.map((day: { localDateTime: Date; nutrition: Nutrition }) => {
            return { ...day.nutrition, date: new Date(day.localDateTime) };
          })
        );
      });
  }, []);

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
      className="consumption-history-dashboard-card"
    >
      <div style={{ height: "100%", display: "flex", flexDirection: "column" }}>
        <div>
          <Title style={{ color: "white" }} level={2}>
            History
          </Title>
          <LineDivider />
        </div>
        <div style={{ flex: "1", overflow: "auto" }}>
          {daysToDisplay.map((day) => (
            <ConsumptionDayCard
              date={day.date}
              calories={day.calories}
              protein={day.protein}
              fat={day.fat}
              carbs={day.carbs}
            />
          ))}
        </div>
        <div>
          <LineDivider />
        </div>
      </div>
    </Card>
  );
}
