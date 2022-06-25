import { Card, Col, Row, Typography } from "antd";
import React from "react";
import Food from "../../../models/Food";
import useWindowDimensions from "../../../utils/WindowDimensions";
import CalorieIcon from "../../atoms/CalorieIcon/CalorieIcon";
import CarbIcon from "../../atoms/CarbIcon/CarbIcon";
import FatIcon from "../../atoms/FatIcon/FatIcon";
import ProteinIcon from "../../atoms/ProteinIcon/ProteinIcon";
import "./ConsumedFoodCard.css"

export default function ConsumedFoodCard({
  name,
  amount,
  calories,
  protein,
  fat,
  carbs,
}: Food) {
  const { Title } = Typography;
  const {width} = useWindowDimensions()
  const colspan = width < 1050 ? 6 : 4
  const fontSize = colspan === 6 && calories >= 1000 ?  "14px" : "16px"
  return (
    <Card className="consumed-food-card">
      <div className="consumed-food-card-item">
        <Title className="consumed-food-card-text" level={2}>{amount}g</Title>
      </div>
      <div className="consumed-food-card-item">
        <Title className="consumed-food-card-text" level={4}>{name}</Title>
      </div>
      <Row>
        <Col span={colspan}>
        <CalorieIcon size="2em"/>
        <Title className="consumed-food-card-text" style={{fontSize: fontSize, marginTop: 2}} level={5}>{calories}cal</Title>
        </Col>
        <Col span={colspan}>
        <ProteinIcon width="2em" height="2em"/>
        <Title className="consumed-food-card-text" style={{fontSize: fontSize}} level={5}>{protein}g</Title>
        </Col>
        <Col span={colspan}>
        <FatIcon width="2em" height="2em"/>
        <Title className="consumed-food-card-text" style={{fontSize: fontSize}} level={5}>{fat}g</Title>
        </Col>
        <Col span={colspan}>
        <CarbIcon width="2em" height="2em"/>
        <Title className="consumed-food-card-text" style={{fontSize: fontSize}} level={5}>{carbs}g</Title>
        </Col>
      </Row>
    </Card>
  );
}
