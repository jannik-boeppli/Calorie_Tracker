import { LoginOutlined } from "@ant-design/icons";
import { Button } from "antd";
import React from "react";

export default function AuthPage() {
  return (
    <>
      <Button type="primary" icon={<LoginOutlined />}>
        Login
      </Button>
      <Button>Sign Up</Button>
    </>
  );
}
