import { Card, Col, message, Row } from "antd";
import { Form, Formik } from "formik";
import React, { useEffect, useState } from "react";
import useWindowDimensions from "../../../utils/WindowDimensions";
import AddButton from "../../atoms/AddButton/AddButton";
import CalorieIcon from "../../atoms/CalorieIcon/CalorieIcon";
import CarbIcon from "../../atoms/CarbIcon/CarbIcon";
import FatIcon from "../../atoms/FatIcon/FatIcon";
import FoodSearchbar from "../../atoms/FoodSearchbar/FoodSearchbar";
import Input from "../../atoms/Input/Input";
import ProteinIcon from "../../atoms/ProteinIcon/ProteinIcon";
import "./AddFoodPage.css";
import * as Yup from "yup";
import FoodService from "../../../services/FoodService";
import RegisteredFood from "../../../models/RegisteredFood";
import Food from "../../../models/Food";

export default function AddFoodPage() {
  const iconDimensions = { width: "1em", height: "1em" };
  const [searchString, setSearchString] = useState("");
  const [hasSubmitted, setHasSubmitted] = useState(false);
  const [storedFood, setStoredFood] = useState<Food[]>([]);
  const [initialValues, setInitialValues] = useState({
    name: "",
    amount: "",
    calories: "",
    protein: "",
    fat: "",
    carbs: "",
  });
  const isMobile = useWindowDimensions().width < 850;
  useEffect(() => {
    FoodService()
      .getRegisteredFood()
      .then((registeredFood: RegisteredFood[]) => {
        setStoredFood(
          registeredFood.map((singleFood) => {
            return { ...singleFood.food };
          })
        );
      });
  }, []);

  const searchFunction = (value: string) => {
    const foundItem = storedFood.find((item) => item.id === value);
    if (foundItem) {
      setInitialValues({
        name: foundItem.name,
        amount: "",
        calories: foundItem.nutrition.calories + "",
        protein: foundItem.nutrition.protein + "",
        fat: foundItem.nutrition.fat + "",
        carbs: foundItem.nutrition.carbs + "",
      });
      setSearchString(foundItem.name);
    }
  };

  const validationSchema = Yup.object({
    name: Yup.string().required("You must fill out this field"),
    amount: Yup.number()
      .required("You must fill out this field")
      .typeError("Please only enter whole numbers")
      .integer("Please round to the next whole number"),
    calories: Yup.number()
      .required("You must fill out this field")
      .typeError("Please only enter whole numbers")
      .integer("Please round to the next whole number"),
    protein: Yup.number()
      .required("You must fill out this field")
      .typeError("Please only enter whole numbers")
      .integer("Please round to the next whole number"),
    fat: Yup.number()
      .required("You must fill out this field")
      .typeError("Please only enter whole numbers")
      .integer("Please round to the next whole number"),
    carbs: Yup.number()
      .required("You must fill out this field")
      .typeError("Please only enter whole numbers")
      .integer("Please round to the next whole number"),
  });

  return (
    <Formik
      validationSchema={validationSchema}
      validateOnChange={hasSubmitted}
      enableReinitialize
      initialValues={initialValues}
      onSubmit={(values, helpers) => {
        FoodService()
          .registerFood({
            name: values.name,
            nutrition: {
              calories: +values.calories,
              carbs: +values.carbs,
              fat: +values.fat,
              protein: +values.protein,
            },
          })
          .then((registeredFood) => {
            FoodService()
              .addConsumedFood({
                registeredFood: registeredFood,
                amount: +values.amount,
              })
              .then(() => {
                message.success("Added food");
                helpers.setSubmitting(false);
              });
          });
      }}
    >
      {({ submitForm, values, errors, isSubmitting, handleChange }) => (
        <Form className="add-food-page-center">
          <Card style={{ marginLeft: "2em", marginRight: "2em" }}>
            <Row gutter={[24, isMobile ? 24 : 36]}>
              <Col span={24}>
                <FoodSearchbar
                  value={searchString}
                  onChange={setSearchString}
                  food={storedFood}
                  onSelect={searchFunction}
                />
              </Col>
              <Col span={isMobile ? 24 : 12}>
                <Input
                  error={errors.name}
                  size="large"
                  value={values.name}
                  onChange={handleChange}
                  placeholder="Food Name"
                  name="name"
                />
              </Col>
              <Col span={isMobile ? 24 : 12}>
                <Input
                  error={errors.amount}
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
                  error={errors.calories}
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
                  error={errors.protein}
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
                  error={errors.fat}
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
                  error={errors.carbs}
                  onChange={handleChange}
                  value={values.carbs}
                  size="large"
                  placeholder="Carbohydrates"
                  prefix={<CarbIcon {...iconDimensions} />}
                  suffix="g"
                  name="carbs"
                />
              </Col>
              <Col style={{ margin: "auto" }} span={isMobile ? 24 : 8}>
                <AddButton
                  onClick={() => {
                    setHasSubmitted(true);
                    submitForm();
                  }}
                  loading={isSubmitting}
                  disabled={isSubmitting}
                />
              </Col>
            </Row>
          </Card>
        </Form>
      )}
    </Formik>
  );
}
