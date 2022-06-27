import { UserOutlined } from "@ant-design/icons";
import { Typography, Form, Row, Col, Card, Space, Input } from "antd";
import { Formik } from "formik";
import React from "react";

import * as Yup from "yup";
import LineDivider from "../../atoms/LineDivider/LineDivider";
import useWindowDimensions from "../../../utils/WindowDimensions";
import "./ProfilePage.css";
import "../LoginPage/LoginPage.css";
import SaveButton from "../../atoms/SaveButton/SaveButton";

export default function ProfilePage() {
  const { Text, Title } = Typography;
  const { width } = useWindowDimensions();

  const validationSchema = Yup.object({
    username: Yup.string()
      .required("Please enter a username")
      .max(255, "The username can't be longer than 255 characters"),
    password: Yup.string()
      .required("Please enter a password")
      .max(255, "The password can't be longer than 255 characters"),
    weight: Yup.number()
      .required("Please enter a weight")
      .typeError("weight must consist of numbers only")
      .positive("weight must be greater than zero")
      .integer("weight should be rounded to the nearest kg"),
    height: Yup.number()
      .required("Please enter a height")
      .typeError("height must consist of numbers only")
      .positive("height must be greater than zero")
      .integer("height should be rounded to the nearest cm"),
  });

  return (
    <Formik
      initialValues={{
        username: "John",
        password: "Doe",
        weight: "67",
        height: "176",
      }}
      validationSchema={validationSchema}
      onSubmit={(values, helpers) => {
        console.log("test", values);
        helpers.setSubmitting(false);
        //TODO: Submit data to backend
      }}
    >
      {({ isSubmitting, submitForm, handleChange, values, errors }) => (
        <Form>
          <Row className={width < 1050 ? "adjusted-half" : "login-half"}>
            <Col span={24} className="center">
              <UserOutlined style={{ fontSize: "40vmin", color: "#389e0d" }} />
            </Col>
          </Row>
          <Row className="login-half">
            <Col span={24} className="center">
              <Card
                className={"center " + (width < 850 ? "" : "login-card")}
                style={{ marginBottom: "1em" }}
                bodyStyle={{ width: "75%", height: "100%" }}
              >
                <Space
                  className="fill-content center"
                  direction="vertical"
                  size={width < 1050 ? 10 : 20}
                >
                  <div>
                    <Input
                      status={errors.username && "error"}
                      value={values.username}
                      onChange={handleChange}
                      name="username"
                      size="large"
                      placeholder="Username"
                      autoFocus
                      suffix={<UserOutlined />}
                    />
                    {errors.username && (
                      <Text type="danger">{errors.username}</Text>
                    )}
                  </div>
                  <div>
                    <Input.Password
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
                  <LineDivider />
                  <div>
                    <Input
                      status={errors.weight && "error"}
                      value={values.weight}
                      onChange={handleChange}
                      name="weight"
                      size="large"
                      placeholder="Weight"
                      suffix={"kg"}
                    />
                    {errors.weight && (
                      <Text type="danger">{errors.weight}</Text>
                    )}
                  </div>
                  <div>
                    <Input
                      status={errors.height && "error"}
                      value={values.height}
                      onChange={handleChange}
                      name="height"
                      size="large"
                      placeholder="Height"
                      suffix={"cm"}
                    />
                    {errors.height && (
                      <Text type="danger">{errors.height}</Text>
                    )}
                  </div>
                  <Title
                    style={{ color: "#ffffff" }}
                    level={width < 1050 ? 3 : 2}
                  >
                    {Math.round(
                      (+values.weight / +values.height / +values.height) *
                        100000
                    ) / 10 || "-"}{" "}
                    BMI
                  </Title>
                  <SaveButton
                    onClick={() => submitForm()}
                    loading={isSubmitting}
                    disabled={isSubmitting}
                  />
                </Space>
              </Card>
            </Col>
          </Row>
        </Form>
      )}
    </Formik>
  );
}
