import { Input, InputProps, Typography } from "antd";
import React from "react";

interface PropsType extends InputProps {
  icon: JSX.Element;
  error: string | undefined;
}

export default function NutritionGoal(props: PropsType) {
  const { Text } = Typography;
  return (
    <>
      {props.icon}
      <Input style={{ marginTop: "1em" }} size="large" {...props} />
      {props.error && <Text type="danger">{props.error}</Text>}
    </>
  );
}
