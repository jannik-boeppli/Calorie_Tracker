import { Card, Col, Input, Row } from "antd";
import { Form, Formik } from "formik";
import React, { useState } from "react";
import Food from "../../../models/Food";
import useWindowDimensions from "../../../utils/WindowDimensions";
import CalorieIcon from "../../atoms/CalorieIcon/CalorieIcon";
import CarbIcon from "../../atoms/CarbIcon/CarbIcon";
import FatIcon from "../../atoms/FatIcon/FatIcon";
import FoodSearchbar from "../../atoms/FoodSearchbar/FoodSearchbar";
import ProteinIcon from "../../atoms/ProteinIcon/ProteinIcon";
import "./AddFoodPage.css";

export default function AddFoodPage() {
  const iconDimensions = { width: "1em", height: "1em" };
  const [searchString, setSearchString] = useState("");
  const [initialValues, setInitialValues] = useState({
    name: "",
    amount: "",
    calories: "",
    protein: "",
    fat: "",
    carbs: "",
  });
  const isMobile = useWindowDimensions().width < 850;
  const food = [
    {
      id: "1",
      name: "Spagetthi",
      amount: 500,
      calories: 600,
      protein: 60,
      fat: 42,
      carbs: 90,
    },
    {
      id: "2",
      name: "Nudeln",
      amount: 200,
      calories: 300,
      protein: 30,
      fat: 12,
      carbs: 40,
    },
  ];

  const searchFunction = (value: string) => {
    console.log(value);
    const foundItem = food.find((item) => item.id === value);
    console.log(foundItem);
    if (foundItem) {
      setInitialValues({
        name: foundItem.name,
        amount: foundItem.amount + "",
        calories: foundItem.calories + "",
        protein: foundItem.protein + "",
        fat: foundItem.fat + "",
        carbs: foundItem.carbs + "",
      });
      setSearchString(foundItem.name);
    }
  };

  return (
    <Formik
      enableReinitialize
      initialValues={initialValues}
      onSubmit={(values) => {
        console.log(values);
        //TODO: Connect to backend
      }}
    >
      {({ submitForm, values, errors, isSubmitting, handleChange }) => (
        <Form className="add-food-page-center">
          <Card>
            <Row gutter={[24, isMobile ? 24 : 36]}>
              <Col span={24}>
                <FoodSearchbar
                  value={searchString}
                  onChange={setSearchString}
                  food={food}
                  onSelect={searchFunction}
                />
              </Col>
              <Col span={isMobile ? 24 : 12}>
                <Input
                  size="large"
                  value={values.name}
                  onChange={handleChange}
                  placeholder="Food Name"
                  name="name"
                />
              </Col>
              <Col span={isMobile ? 24 : 12}>
                <Input
                  onChange={handleChange}
                  value={values.amount}
                  size="large"
                  placeholder="Amount of Food"
                  suffix="g"
                  name="amount"
                />
              </Col>
              <Col span={isMobile ? 24 : 12}>
                <Input
                  onChange={handleChange}
                  value={values.calories}
                  size="large"
                  placeholder="Calories"
                  prefix={<CalorieIcon size={iconDimensions.width} />}
                  suffix="cal"
                  name="calories"
                />
              </Col>
              <Col span={isMobile ? 24 : 12}>
                <Input
                  onChange={handleChange}
                  value={values.protein}
                  size="large"
                  placeholder="Protein"
                  prefix={<ProteinIcon {...iconDimensions} />}
                  suffix="g"
                  name="protein"
                />
              </Col>
              <Col span={isMobile ? 24 : 12}>
                <Input
                  onChange={handleChange}
                  value={values.fat}
                  size="large"
                  placeholder="Fat"
                  prefix={<FatIcon {...iconDimensions} />}
                  suffix="g"
                  name="fat"
                />
              </Col>
              <Col span={isMobile ? 24 : 12}>
                <Input
                  onChange={handleChange}
                  value={values.carbs}
                  size="large"
                  placeholder="Carbohydrates"
                  prefix={<CarbIcon {...iconDimensions} />}
                  suffix="g"
                  name="carbs"
                />
              </Col>
            </Row>
          </Card>
        </Form>
      )}
    </Formik>
  );
}
