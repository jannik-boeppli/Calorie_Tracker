import { CalendarOutlined } from "@ant-design/icons";
import { Typography, Card, Row, Col } from "antd";
import React from "react";
import ConsumptionDay from "../../../models/ConsumptionDay";
import useWindowDimensions from "../../../utils/WindowDimensions";
import CalorieIcon from "../../atoms/CalorieIcon/CalorieIcon";
import CarbIcon from "../../atoms/CarbIcon/CarbIcon";
import FatIcon from "../../atoms/FatIcon/FatIcon";
import ProteinIcon from "../../atoms/ProteinIcon/ProteinIcon";
import "./ConsumptionDayCard.css";

export default function ConsumptionDayCard({
  date,
  calories,
  protein,
  fat,
  carbs,
}: ConsumptionDay) {
  const { Title } = Typography;
  const { width } = useWindowDimensions();
  const colspan = width < 1050 ? 6 : 4;
  const fontSize = colspan === 6 && calories >= 1000 ? "14px" : "16px";
  return (
    <Card className="consumption-day-card">
      <CalendarOutlined className="consumption-day-card-date-icon" />
      <div className="consumption-day-card-item">
        <Title className="consumption-day-card-text" level={2}>
          {date.getDate() < 10 ? "0" + date.getDate() : date.getDate()}.
          {date.getMonth() + 1 < 10 ? "0" +( date.getMonth() + 1) : date.getMonth() + 1}
        </Title>
      </div>
      <div className="consumption-day-card-item">
        <Title className="consumption-day-card-text" level={4}>
          {date.getFullYear()}
        </Title>
      </div>
      <Row>
        <Col span={colspan}>
          <CalorieIcon size="2em" />
          <Title
            className="consumption-day-card-text"
            style={{ fontSize: fontSize, marginTop: 2 }}
            level={5}
          >
            {calories}cal
          </Title>
        </Col>
        <Col span={colspan}>
          <ProteinIcon width="2em" height="2em" />
          <Title
            className="consumption-day-card-text"
            style={{ fontSize: fontSize }}
            level={5}
          >
            {protein}g
          </Title>
        </Col>
        <Col span={colspan}>
          <FatIcon width="2em" height="2em" />
          <Title
            className="consumption-day-card-text"
            style={{ fontSize: fontSize }}
            level={5}
          >
            {fat}g
          </Title>
        </Col>
        <Col span={colspan}>
          <CarbIcon width="2em" height="2em" />
          <Title
            className="consumption-day-card-text"
            style={{ fontSize: fontSize }}
            level={5}
          >
            {carbs}g
          </Title>
        </Col>
      </Row>
    </Card>
  );
}
