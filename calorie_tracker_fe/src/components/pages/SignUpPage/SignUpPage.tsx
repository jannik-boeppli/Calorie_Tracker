import { UserOutlined } from "@ant-design/icons";
import { Row, Col, Card, Space, Button, Typography, Input as AntInput, } from "antd";
import { Form, Formik } from "formik";
import SignUpIcon from "../../atoms/SignUpIcon/SignUpIcon";
import "../LoginPage/LoginPage.css";
import * as Yup from "yup";
import { useState } from "react";
import Input from "../../atoms/Input/Input";

export default function SignUpPage() {
  const [hasSubmitted, setHasSubmitted] = useState(false);
  const { Text } = Typography;
  const validationSchema = Yup.object({
    firstName: Yup.string()
      .required("Please enter a first name")
      .max(255, "The first name can't be longer than 255 characters"),
      lastName: Yup.string()
      .required("Please enter a last name")
      .max(255, "The last name can't be longer than 255 characters"),
    username: Yup.string()
      .required("Please enter a username")
      .max(255, "The username can't be longer than 255 characters"), 
    password: Yup.string()
      .required("Please enter a password")
      .max(255, "The password can't be longer than 255 characters"),
    repeatedPassword: Yup.string()
      .required("Please repeat the password")
      .test("passwords-match", "Passwords must match", function (value) {
        return this.parent.password === value;
      }),
  });

  return (
    <Formik
    validateOnChange={hasSubmitted}
      initialValues={{firstName: "", lastName: "", username: "", password: "", repeatedPassword: "" }}
      validationSchema={validationSchema}
      onSubmit={(values, helpers) => {
        console.log(values);
        helpers.setSubmitting(false);
        //TODO: Submit data to backend
      }}
    >
      {({ isSubmitting, submitForm, handleChange, values, errors }) => (
        <Form>
          <Row className="login-half">
            <Col span={24} className="center">
              <SignUpIcon width="40vmin" height="40vmin" fill="#389e0d" />
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
                  <div>
                    <AntInput.Password
                      status={errors.repeatedPassword && "error"}
                      value={values.repeatedPassword}
                      onChange={handleChange}
                      name="repeatedPassword"
                      size="large"
                      placeholder="Repeat Password"
                    />
                    {errors.repeatedPassword && (
                      <Text type="danger">{errors.repeatedPassword}</Text>
                    )}
                  </div>
                  <Button
                    className="login-button"
                    onClick={() => {setHasSubmitted(true);submitForm()}}
                    loading={isSubmitting}
                    disabled={isSubmitting}
                    type="primary"
                    size="large"
                  >
                    Sign Up
                  </Button>
                </Space>
              </Card>
            </Col>
          </Row>
        </Form>
      )}
    </Formik>
  );
}
