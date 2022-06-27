import { InputProps, Input as AntInput, Typography } from "antd";
import React from "react";

interface PropsType extends InputProps {
  error: string | undefined;
}

export default function Input(props: PropsType) {
  const { Text } = Typography;
  return (
    <>
      <AntInput status={props.error && "error"} {...props} />{" "}
      {props.error && <Text type="danger">{props.error}</Text>}{" "}
    </>
  );
}
