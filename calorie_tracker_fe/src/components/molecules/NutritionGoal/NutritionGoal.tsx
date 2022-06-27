import { InputProps } from "antd";
import React from "react";
import Input from "../../atoms/Input/Input";

interface PropsType extends InputProps {
  icon: JSX.Element;
  error: string | undefined;
}

export default function NutritionGoal(props: PropsType) {
  return (
    <>
      {props.icon}
      <Input style={{ marginTop: "1em" }} size="large" {...props} />
    </>
  );
}
