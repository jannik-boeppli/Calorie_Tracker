import { FireFilled } from "@ant-design/icons";
import { Card, Space, Typography } from "antd";
import React from "react";
import useWindowDimensions from "../../../utils/WindowDimensions";
import CarbIcon from "../../atoms/CarbIcon/CarbIcon";
import CircularProgress from "../../atoms/CircularProgress/CircularProgress";
import FatIcon from "../../atoms/FatIcon/FatIcon";
import ProteinIcon from "../../atoms/ProteinIcon/ProteinIcon";
import "./NutritionDashboard.css";

export default function NutritionDashboard() {
  const { width } = useWindowDimensions();
  const { Title } = Typography;
  return (
    <Card className="nutrition-dashboard-card">
      <Space
        className="progress-dashboard-space"
        direction={width < 1050 ? "vertical" : "horizontal"}
        size={46}
      >
        <div>
          <CircularProgress
            max={1800}
            reached={600}
            className="calorie-progress"
            strokeColor="#bae637"
            format={() => (
              <div className="icon-label-container">
                <div>
                  <div>
                    <FireFilled style={{ color: "#bae637", fontSize: "3em" }} />
                  </div>
                  <div>
                    <Title className="progress-label-text" level={2}>
                      600cal/
                      <br />
                      1800cal
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
            max={120}
            reached={40}
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
                      40g/120g
                    </Title>
                  </div>
                </div>
              </div>
            )}
          />
          <div  className="progress-subtitle-container">
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
            max={80}
            reached={28}
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
                      28g/80g
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
          max={160}
          reached={53}
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
                    53g/160g
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
