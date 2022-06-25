import { Layout } from "antd";
import React from "react";
import { useLocation } from "react-router-dom";

interface PropsType {
  children: React.ReactNode;
}

export default function MainLayout({ children }: PropsType) {
  const { pathname } = useLocation();
  return (
    <Layout
      style={
        pathname === "/login" || pathname === "/signup"
          ? {}
          : { marginLeft: 80 }
      }
    >
      {children}
    </Layout>
  );
}
