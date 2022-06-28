import { LoginOutlined, UserOutlined } from "@ant-design/icons";
import { Button, Card, Col, Row, Input as AntInput, Space, Typography } from "antd";
import Link from "antd/lib/typography/Link";
import { Form, Formik } from "formik";
import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import SignUpIcon from "../../atoms/SignUpIcon/SignUpIcon";
import "./LoginPage.css";
import * as Yup from "yup";
import Input from "../../atoms/Input/Input";
import { useAuth } from "../../../contexts/AuthenticationContext";

export default function LoginPage() {
  const [isHoveringLink, setIsHoveringLink] = useState(false);
  const [hasSubmitted, setHasSubmitted] = useState(false);
  const navigate = useNavigate();
  const { Text } = Typography;
  const validationSchema = Yup.object({
    username: Yup.string()
      .required("Please enter a username")
      .max(255, "The username can't be longer than 255 characters"),
    password: Yup.string()
      .required("Please enter a password")
      .max(255, "The password can't be longer than 255 characters"),
  });

  const {login} = useAuth();

  return (
    <Formik
      validateOnChange={hasSubmitted}
      initialValues={{ username: "", password: "" }}
      validationSchema={validationSchema}
      onSubmit={(values, helpers) => {
        console.log(values);
        login(values.username, values.password).then(() => navigate("/"))
        helpers.setSubmitting(false);
      }}
    >
      {({ isSubmitting, submitForm, handleChange, values, errors }) => (
        <Form>
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
                  <div>
                    <Input
                      error={errors.username}
                      value={values.username}
                      onChange={handleChange}
                      name="username"
                      size="large"
                      placeholder="Username"
                      autoFocus
                      suffix={<UserOutlined />}
                    />
                  </div>
                  <div>
                    <AntInput.Password
                      status={errors.password && "error"}
                      value={values.password}
                      onChange={handleChange}
                      name="password"
                      size="large"
                      placeholder="Password"
                    />
                    {errors.password && (
                      <Text type="danger">{errors.password}</Text>
                    )}
                  </div>
                  <Space
                    className="fill-content"
                    direction="vertical"
                    size="small"
                  >
                    <Button
                      className="login-button"
                      onClick={() => {setHasSubmitted(true);submitForm()}}
                      loading={isSubmitting}
                      disabled={isSubmitting}
                      type="primary"
                      size="large"
                    >
                      Login
                    </Button>
                    <Link
                      onMouseEnter={() => setIsHoveringLink(true)}
                      onMouseLeave={() => setIsHoveringLink(false)}
                      onClick={() => navigate("/signup")}
                      style={{ fontSize: "1.5em" }}
                    >
                      <SignUpIcon
                        fill={isHoveringLink ? "#092b00" : "#00000073"}
                        width="1em"
                        height="1em"
                      />{" "}
                      Sign Up
                    </Link>
                  </Space>
                </Space>
              </Card>
            </Col>
          </Row>
        </Form>
      )}
    </Formik>
  );
}
