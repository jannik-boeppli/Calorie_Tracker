import { Card, Space, Typography } from "antd";
import React, { useEffect, useState } from "react";
import Food from "../../../models/Food";
import UserGoal from "../../../models/UserGoal";
import useWindowDimensions from "../../../utils/WindowDimensions";
import CalorieIcon from "../../atoms/CalorieIcon/CalorieIcon";
import CarbIcon from "../../atoms/CarbIcon/CarbIcon";
import CircularProgress from "../../atoms/CircularProgress/CircularProgress";
import FatIcon from "../../atoms/FatIcon/FatIcon";
import ProteinIcon from "../../atoms/ProteinIcon/ProteinIcon";
import "./NutritionDashboard.css";

interface PropsType {
  goal: UserGoal,
  food: Food[],
}

interface ConsumedTotal {
  calories: number,
  protein: number,
  carbs: number,
  fat: number,
}

export default function NutritionDashboard({goal, food}: PropsType) {
  const { width } = useWindowDimensions();
  const { Title } = Typography;
  const [consumedTotal, setConsumedTotal] = useState<ConsumedTotal>({calories: 0, protein: 0, carbs: 0, fat: 0})
  useEffect(() => {
    let total = {calories: 0, protein: 0, carbs: 0, fat: 0};
    food.forEach((value) => {
      total.calories += value.nutrition.calories
      total.protein += value.nutrition.protein
      total.carbs += value.nutrition.carbs
      total.fat += value.nutrition.fat
    })
    setConsumedTotal(total);
  }, [food])
  
  return (
    <Card className="nutrition-dashboard-card">
      <Space
        className="progress-dashboard-space"
        direction={width < 1050 ? "vertical" : "horizontal"}
        size={46}
      >
        <div>
          <CircularProgress
            max={goal.nutrition.calories}
            reached={consumedTotal.calories}
            className="calorie-progress"
            strokeColor="#bae637"
            format={() => (
              <div className="icon-label-container">
                <div>
                  <div>
                    <CalorieIcon size="3em"/>
                  </div>
                  <div>
                    <Title className="progress-label-text" level={2}>
                      {consumedTotal.calories}cal/
                      <br />
                      {goal.nutrition.calories}cal
                    </Title>
                  </div>
                </div>
              </div>
            )}
          />
          <Title
            level={3}
            style={ { marginTop: "0.5em"}}
            className="progress-subtitle"
          >
            Calories
          </Title>
        </div>
        <div>
          <CircularProgress
            max={goal.nutrition.protein}
            reached={consumedTotal.protein}
            className="protein-progress"
            strokeColor="#ff4d4f"
            format={() => (
              <div className="icon-label-container">
                <div>
                  <div>
                    <ProteinIcon width="2em" height="2em" />
                  </div>
                  <div>
                    <Title className="progress-label-text" level={3}>
                      {consumedTotal.protein}g/{goal.nutrition.protein}g
                    </Title>
                  </div>
                </div>
              </div>
            )}
          />
          <div className="progress-subtitle-container">
          <Title
            level={3}
            style={width < 1050 ? {marginTop: "0.5em"} : { marginTop: "0.5em", marginLeft: "auto", marginRight: "auto", width: "7.5em", position: "absolute", textAlign: "center" }}
            className="progress-subtitle"
          >
            Protein
          </Title></div>
        </div>
        <div>
          <CircularProgress
            max={goal.nutrition.fat}
            reached={consumedTotal.fat}
            className="fat-progress"
            strokeColor="#ffec3d"
            format={() => (
              <div className="icon-label-container">
                <div>
                  <div>
                    <FatIcon width="2em" height="2em" />
                  </div>
                  <div>
                    <Title className="progress-label-text" level={3}>
                    {consumedTotal.fat}g/{goal.nutrition.fat}g
                    </Title>
                  </div>
                </div>
              </div>
            )}
          />
          <div className="progress-subtitle-container">
          <Title
            level={3}
            style={width < 1050 ? {marginTop: "0.5em",} : { marginTop: "0.5em", marginLeft: "auto", marginRight: "auto", width: "7.5em", position: "absolute", textAlign: "center" }}
            className="progress-subtitle"
          >
            Fat
          </Title></div>
        </div>
        <div>
        <CircularProgress
          max={goal.nutrition.carbs}
          reached={consumedTotal.carbs}
          className="carb-progress"
          strokeColor="#ffa940"
          format={() => (
            <div className="icon-label-container">
              <div>
                <div>
                  <CarbIcon width="2em" height="2em" />
                </div>
                <div>
                  <Title className="progress-label-text" level={3}>
                    {consumedTotal.carbs}g/{goal.nutrition.carbs}g
                  </Title>
                </div>
              </div>
            </div>
          )}
        />
        <div className="progress-subtitle-container">
        <Title
          level={3}
          style={width < 1050 ? {marginTop: "0.5em"} : { marginTop: "0.5em", marginLeft: "auto", marginRight: "auto", width: "7.5em", position: "absolute", textAlign: "center" }}
          className="progress-subtitle"
        >
          Carbohydrates
        </Title>
        </div>
        </div>
      </Space>
    </Card>
  );
}
