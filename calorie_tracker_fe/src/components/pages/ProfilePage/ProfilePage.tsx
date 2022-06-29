import { UserOutlined } from "@ant-design/icons";
import {
  Typography,
  Form,
  Row,
  Col,
  Card,
  Space,
  message,
} from "antd";
import { Formik } from "formik";
import React, { useEffect, useState } from "react";

import * as Yup from "yup";
import LineDivider from "../../atoms/LineDivider/LineDivider";
import useWindowDimensions from "../../../utils/WindowDimensions";
import "./ProfilePage.css";
import "../LoginPage/LoginPage.css";
import SaveButton from "../../atoms/SaveButton/SaveButton";
import Input from "../../atoms/Input/Input";
import UserService from "../../../services/UserService";

export default function ProfilePage() {
  const { Title } = Typography;
  const [user, setUser] = useState({
    firstName: "",
    lastName: "",
    username: "",
    weightInKg: "",
    heightInCM: "",
  });
  const { width } = useWindowDimensions();

  useEffect(() => {
    UserService()
      .getCurrentUser()
      .then((value) =>{
        setUser({
          ...value,
          weightInKg: value.bodyMass && value.bodyMass.weightInKg !== 0 ? value.bodyMass.weightInKg : "",
          heightInCM: value.heightInCM === 0 ? "" : value.heightInCM,
        })}
      );
  }, []);

  const validationSchema = Yup.object({
    firstName: Yup.string()
      .required("Please enter a first name")
      .max(255, "The firstName can't be longer than 255 characters"),
    lastName: Yup.string()
      .required("Please enter a last name")
      .max(255, "The last name can't be longer than 255 characters"),
    username: Yup.string()
      .required("Please enter a username")
      .max(255, "The username can't be longer than 255 characters"),
    weightInKg: Yup.number()
      .required("Please enter a weight")
      .typeError("weight must consist of numbers only")
      .positive("weight must be greater than zero")
      .integer("weight should be rounded to the nearest kg"),
    heightInCM: Yup.number()
      .required("Please enter a height")
      .typeError("height must consist of numbers only")
      .positive("height must be greater than zero")
      .integer("height should be rounded to the nearest cm"),
  });

  return (
    <Formik
      enableReinitialize
      initialValues={user}
      validationSchema={validationSchema}
      onSubmit={(values, helpers) => {
        console.log(values)
        UserService()
          .updateUser({
            ...values,
            bodyMass: { weightInKg: +values.weightInKg },
            heightInCM: +values.heightInCM,
          })
          .then(() => message.success("Profile successfully updated"))

        helpers.setSubmitting(false);
      }}
    >
      {({ isSubmitting, submitForm, handleChange, values, errors }) => (
        <Form>
          <Row className={width < 1050 ? "adjusted-half" : "profile-top-half"}>
            
            <Col span={24} className="center">
              <UserOutlined style={{ fontSize: "30vmin", color: "#389e0d" }} />
            </Col>
          </Row>
          <Row>
            <Col span={24} className="center">
              <Card
                className={"center " + (width < 850 ? "" : "profile-page-card")}
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
                      error={errors.firstName}
                      value={values.firstName}
                      onChange={handleChange}
                      name="firstName"
                      size="large"
                      placeholder="First Name"
                      autoFocus
                    />
                  </div>
                  <div>
                    <Input
                      error={errors.lastName}
                      value={values.lastName}
                      onChange={handleChange}
                      name="lastName"
                      size="large"
                      placeholder="Last Name"
                    />
                  </div>
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
                  <LineDivider />
                  <div>
                    <Input
                      error={errors.weightInKg}
                      value={values.weightInKg}
                      onChange={handleChange}
                      name="weightInKg"
                      size="large"
                      placeholder="Weight"
                      suffix={"kg"}
                    />
                  </div>
                  <div>
                    <Input
                      error={errors.heightInCM}
                      value={values.heightInCM}
                      onChange={handleChange}
                      name="heightInCM"
                      size="large"
                      placeholder="Height"
                      suffix={"cm"}
                    />
                  </div>
                  <Title
                    style={{ color: "#ffffff" }}
                    level={width < 1050 ? 3 : 2}
                  >
                    {Math.round(
                      (+values.weightInKg /
                        +values.heightInCM /
                        +values.heightInCM) *
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
