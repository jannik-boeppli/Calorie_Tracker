import {
  EyeInvisibleOutlined,
  LoginOutlined,
  UserOutlined,
} from "@ant-design/icons";
import { Button, Card, Col, Input, Row, Space } from "antd";
import Link from "antd/lib/typography/Link";
import React, { useState } from "react";
import SignUpIcon from "../../atoms/SignUpIcon";
import "./LoginPage.css";

export default function LoginPage() {
  const [isHoveringLink, setIsHoveringLink] = useState(false);
  console.log(isHoveringLink)
  return (
    <>
      <Row className="login-half">
        <Col span={24} className="center">
          <LoginOutlined className="main-icon" />
        </Col>
      </Row>
      <Row className="login-half">
        <Col span={24} className="center">
          <Card
            className="center login-card"
            bodyStyle={{ width: "75%", height: "100%" }}
          >
            <Space
              className="fill-content center"
              direction="vertical"
              size={30}
            >
              <Input
                size="large"
                placeholder="Username"
                autoFocus
                suffix={<UserOutlined />}
              />
              <Input.Password size="large" placeholder="Password" />
              <Space className="fill-content" direction="vertical" size="small">
                <Button className="login-button" type="primary" size="large">
                  Login
                </Button>
                <Link
                  onMouseEnter={() => setIsHoveringLink(true)}
                  onMouseLeave={() => setIsHoveringLink(false)}
                  href="/"
                  style={{ fontSize: "1.5em" }}
                >
                  <SignUpIcon fill={isHoveringLink ? "#092b00" :"#00000073"} width="1em" height="1em" /> Sign
                  Up
                </Link>
              </Space>
            </Space>
          </Card>
        </Col>
      </Row>
    </>
  );
}
