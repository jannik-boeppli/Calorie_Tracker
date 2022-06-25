import { Col, Row } from "antd";
import React from "react";
import useWindowDimensions from "../../../utils/WindowDimensions";
import ConsumedFoodDashboard from "../../organisms/ConsumedFoodDashboard/ConsumedFoodDashboard";
import NutritionDashboard from "../../organisms/NutritionDashboard/NutritionDashboard";

export default function LandingPage() {
  const {width} = useWindowDimensions()
  return (
    <>
      <Row>
        <Col span={24}>
          <NutritionDashboard />
        </Col>
      </Row>
      <Row>
        <Col span={width < 1050 ? 24 : 12}>
          <ConsumedFoodDashboard />
        </Col>
      </Row>
    </>
  );
}
