import { Card, Col, Row } from "antd";
import { Form, Formik } from "formik";
import React from "react";
import useWindowDimensions from "../../../utils/WindowDimensions";
import CalorieIcon from "../../atoms/CalorieIcon/CalorieIcon";
import CarbIcon from "../../atoms/CarbIcon/CarbIcon";
import FatIcon from "../../atoms/FatIcon/FatIcon";
import ProteinIcon from "../../atoms/ProteinIcon/ProteinIcon";
import SaveButton from "../../atoms/SaveButton/SaveButton";
import WeightIcon from "../../atoms/WeightIcon/WeightIcon";
import NutritionGoal from "../../molecules/NutritionGoal/NutritionGoal";
import "./NutritionGoalsPage.css";
import * as Yup from "yup";

export default function NutritionGoalsPage() {
  const iconDimensions = { height: "10vmin", width: "10vmin" };
  const isMobile = useWindowDimensions().width < 850;
  const validationSchema = Yup.object({
    calories: Yup.number()
      .typeError("Please only enter decimal numbers")
      .required("You must fill out this field")
      .integer("Please round to the next whole number"),
    protein: Yup.number()
      .typeError("Please only enter decimal numbers")
      .required("You must fill out this field")
      .integer("Please round to the next whole number"),
    fat: Yup.number()
      .typeError("Please only enter decimal numbers")
      .required("You must fill out this field")
      .integer("Please round to the next whole number"),
    carbs: Yup.number()
      .typeError("Please only enter decimal numbers")
      .required("You must fill out this field")
      .integer("Please round to the next whole number"),
    bodyWeight: Yup.number()
      .typeError("Please only enter decimal numbers")
      .required("You must fill out this field")
      .integer("Please round to the next whole number"),
  });
  return (
    <Formik
      validationSchema={validationSchema}
      initialValues={{
        calories: "",
        protein: "",
        fat: "",
        carbs: "",
        bodyWeight: "",
      }}
      onSubmit={(values, helpers) => {
        console.log(values);
        helpers.setSubmitting(false);
        //TODO: Connect to backend
      }}
    >
      {({ submitForm, isSubmitting, errors, values, handleChange }) => (
        <Form className="nutritions-goals-page-center">
          <Card className="nutritions-goals-page-card">
            <Row gutter={[0, 24]}>
              <Col span={1} />
              <Col span={isMobile ? 22 : 9}>
                <NutritionGoal
                  icon={<CalorieIcon size={iconDimensions.width} />}
                  error={errors.calories}
                  placeholder="Calorie Consumption Goal"
                  onChange={handleChange}
                  value={values.calories}
                  name="calories"
                  suffix="cal"
                />
              </Col>
              <Col span={isMobile ? 1 : 4} />
              {isMobile && <Col span={1} />}
              <Col span={isMobile ? 22 : 9}>
                <NutritionGoal
                  icon={<ProteinIcon {...iconDimensions} />}
                  error={errors.protein}
                  placeholder="Protein Consumption Goal"
                  onChange={handleChange}
                  value={values.protein}
                  name="protein"
                  suffix="g"
                />
              </Col>
              <Col span={1} />
              <Col span={1} />
              <Col span={isMobile ? 22 : 9}>
                <NutritionGoal
                  icon={<FatIcon {...iconDimensions} />}
                  error={errors.fat}
                  placeholder="Fat Consumption Goal"
                  onChange={handleChange}
                  value={values.fat}
                  name="fat"
                  suffix="g"
                />
              </Col>
              <Col span={isMobile ? 1 : 4} />
              {isMobile && <Col span={1} />}
              <Col span={isMobile ? 22 : 9}>
                <NutritionGoal
                  icon={<CarbIcon {...iconDimensions} />}
                  error={errors.carbs}
                  placeholder="Carb Consumption Goal"
                  onChange={handleChange}
                  value={values.carbs}
                  name="carbs"
                  suffix="g"
                />
              </Col>
              <Col span={1} />
              <Col span={1} />
              <Col span={isMobile ? 22 : 9} style={{ margin: "auto" }}>
                <NutritionGoal
                  icon={<WeightIcon {...iconDimensions} />}
                  error={errors.bodyWeight}
                  placeholder="Bodyweight Goal"
                  onChange={handleChange}
                  value={values.bodyWeight}
                  name="bodyWeight"
                  suffix="kg"
                />
              </Col>
              <Col span={1} />
            </Row>
            <Row style={{ marginTop: "5vh" }}>
              <Col span={1} />
              <Col span={isMobile ? 22 : 9} style={{ margin: "auto" }}>
                <SaveButton
                  onClick={() => submitForm()}
                  loading={isSubmitting}
                  disabled={isSubmitting}
                />
              </Col>
              <Col span={1} />
            </Row>
          </Card>
        </Form>
      )}
    </Formik>
  );
}
