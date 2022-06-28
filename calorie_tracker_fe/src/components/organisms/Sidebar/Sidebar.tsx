import {
  FlagFilled,
  HomeFilled,
  PlusSquareFilled,
  PoweroffOutlined,
  ProfileFilled,
} from "@ant-design/icons";
import { Layout, Menu } from "antd";
import React, { useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import { useAuth } from "../../../contexts/AuthenticationContext";
import "./Sidebar.css";

export default function Sidebar() {
  const [collapsed, setCollapsed] = useState(true);
  const { Sider } = Layout;
  interface SidebarItem {
    label: React.ReactNode;
    key: React.Key;
    onClickEffect: () => void;
    icon?: React.ReactNode;
  }

  const {logout} = useAuth();
  const navigate = useNavigate();
  const { pathname } = useLocation();

  function getItem(
    label: React.ReactNode,
    key: React.Key,
    icon?: React.ReactNode
  ): SidebarItem {
    return {
      key,
      icon,
      label,
    } as SidebarItem;
  }

  const menuItems: SidebarItem[] = [
    getItem("Home", "/", <HomeFilled />),
    getItem("Profile", "/profile", <ProfileFilled />),
    getItem("Goals", "/goals", <FlagFilled />),
    getItem("Add Food", "/food", <PlusSquareFilled />),
    getItem("Logout", "logout", <PoweroffOutlined />),
  ];

  return pathname === "/login" || pathname === "/signup" ? (
    <></>
  ) : (
    <Sider
      style={{
        zIndex: 1,
        overflow: "auto",
        height: "100vh",
        position: "fixed",
        left: 0,
        top: 0,
        bottom: 0,
      }}
      collapsible
      collapsed={collapsed}
      onCollapse={(value) => setCollapsed(value)}
    >
      <div className="logo" />
      <Menu
        onClick={(item) => {
          if (item.key === "logout") {
            logout();
            navigate("/login")
          } else {
            navigate(item.key);
          }
        }}
        theme="light"
        selectedKeys={[pathname]}
        mode="vertical"
        items={menuItems}
      />
    </Sider>
  );
}
