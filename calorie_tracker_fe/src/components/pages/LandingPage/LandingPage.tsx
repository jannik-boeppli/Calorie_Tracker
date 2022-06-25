import { Col, Row } from "antd";
import React from "react";
import useWindowDimensions from "../../../utils/WindowDimensions";
import ConsumedFoodDashboard from "../../organisms/ConsumedFoodDashboard/ConsumedFoodDashboard";
import NutritionDashboard from "../../organisms/NutritionDashboard/NutritionDashboard";

export default function LandingPage() {
  const {width, height} = useWindowDimensions()
  
  return (
    <div style={width < 1050 ? {} : {display: "flex", flexFlow: "column", height: "100vh"}}>
      <Row style={width < 1050 ? {} : {height: "30vh"}}>
        <Col span={24}>
          <NutritionDashboard />
        </Col>
      </Row>
      <Row style={width < 1050 ? {height: (height - 20) + "px"} : {height: "70vh"}} >
        <Col style={{height: "100%"}} span={width < 1050 ? 24 : 12}>
          <ConsumedFoodDashboard />
        </Col>
      </Row>
    </div>
  );
}
