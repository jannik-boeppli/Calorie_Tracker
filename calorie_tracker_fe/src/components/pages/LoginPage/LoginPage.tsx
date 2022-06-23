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
            bodyStyle={{ width: "75%", height: "80%" }}
          >
            <Space
              className="fill-content"
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
                  <Link href="/" style={{ fontSize: "1.5em", color: "#00000073" }}><SignUpIcon fill="#00000073" width="1em" height="1em"/> Sign Up</Link>
              </Space>
            </Space>
          </Card>
        </Col>
      </Row>
    </>
  );
}
