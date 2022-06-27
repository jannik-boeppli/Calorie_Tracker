import { Card, Typography } from "antd";
import React, { useState } from "react";
import ConsumptionDay from "../../../models/ConsumptionDay";
import useWindowDimensions from "../../../utils/WindowDimensions";
import LineDivider from "../../atoms/LineDivider/LineDivider";
import ConsumptionDayCard from "../../molecules/ConsumptionDayCard/ConsumptionDayCard";
import "./ConsumptionHistory.css";

export default function ConsumptionHistory() {
  const { Title } = Typography;
  const isMobile = useWindowDimensions().width < 1050


  const [daysToDisplay, setDaysToDisplay] = useState<ConsumptionDay[]>([
    {
      date: new Date("2022-06-13"),
      calories: 1600,
      protein: 40,
      fat: 28,
      carbs: 53,
    },
    {
      date: new Date("2022-06-13"),
      calories: 1600,
      protein: 40,
      fat: 28,
      carbs: 53,
    },
    {
      date: new Date("2022-06-13"),
      calories: 1600,
      protein: 40,
      fat: 28,
      carbs: 53,
    },
    {
      date: new Date("2022-06-13"),
      calories: 1600,
      protein: 40,
      fat: 28,
      carbs: 53,
    },
    {
      date: new Date("2022-06-13"),
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
