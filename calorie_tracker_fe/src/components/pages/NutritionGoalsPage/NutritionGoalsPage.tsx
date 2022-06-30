import { Card, Col, message, Row } from "antd";
import { Form, Formik } from "formik";
import React, { useEffect, useState } from "react";
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
import UserGoal from "../../../models/UserGoal";
import GoalService from "../../../services/GoalService";

export default function NutritionGoalsPage() {
  const iconDimensions = { height: "10vmin", width: "10vmin" };
  const [userGoal, setUserGoal] = useState<UserGoal>({
    nutrition: { calories: 0, carbs: 0, protein: 0, fat: 0 },
    bodyMass: { weightInKg: 0 },
  });
  useEffect(() => {
    GoalService()
      .getUserGoal()
      .then((data) => {
        setUserGoal({ ...data.goal });
      });
  }, []);

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
    weightInKg: Yup.number()
      .typeError("Please only enter decimal numbers")
      .required("You must fill out this field")
      .test(
        "maxDigitsAfterDecimal",
        "weight have 1 digit after the decimal point or less",
        (number) => number !== undefined && Number.isInteger(number * 10)
      ),
  });
  return (
    <Formik
      enableReinitialize
      validationSchema={validationSchema}
      initialValues={{
        calories:
          userGoal.nutrition && userGoal.nutrition.calories !== 0
            ? userGoal.nutrition.calories
            : "",
        protein:
          userGoal.nutrition && userGoal.nutrition.protein !== 0
            ? userGoal.nutrition.protein
            : "",
        fat:
          userGoal.nutrition && userGoal.nutrition.fat !== 0
            ? userGoal.nutrition.fat
            : "",
        carbs:
          userGoal.nutrition && userGoal.nutrition.carbs !== 0
            ? userGoal.nutrition.carbs
            : "",
        weightInKg:
          userGoal.bodyMass && userGoal.bodyMass.weightInKg !== 0
            ? userGoal.bodyMass.weightInKg
            : "",
      }}
      onSubmit={(values, helpers) => {
        GoalService().createUserGoal({
          nutrition: {
            calories: +values.calories,
            carbs: +values.carbs,
            fat: +values.fat,
            protein: +values.protein,
          },
          bodyMass: {
            weightInKg: +values.weightInKg
          }
        }).then(() =>{ message.success("Updated goal"); helpers.setSubmitting(false);});
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
                  status={errors.calories && "error"}
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
                  status={errors.protein && "error"}
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
                  status={errors.fat && "error"}
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
                  status={errors.carbs && "error"}
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
                  error={errors.weightInKg}
                  status={errors.weightInKg && "error"}
                  placeholder="Bodyweight Goal"
                  onChange={handleChange}
                  value={values.weightInKg}
                  name="weightInKg"
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
                  htmlType="submit"
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
