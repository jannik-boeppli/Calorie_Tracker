import { Button, ButtonProps } from "antd";
import React from "react";
import "./SaveButton.css";

export default function SaveButton(props: ButtonProps) {
  return (
    <Button type="primary" size="large" className="save-button" {...props}>
      Save
    </Button>
  );
}
