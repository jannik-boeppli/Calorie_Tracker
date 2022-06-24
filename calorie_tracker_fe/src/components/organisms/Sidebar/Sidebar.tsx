import {
  FlagFilled,
  HomeFilled,
  LogoutOutlined,
  PlusSquareFilled,
  PoweroffOutlined,
  ProfileFilled,
} from "@ant-design/icons";
import { Layout, Menu } from "antd";
import React, { useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";
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

  const navigate = useNavigate();
  const {pathname} = useLocation()

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
    getItem("Logout", "/logout", <PoweroffOutlined />),
  ];

  return (
    <Sider
    style={{
      zIndex: 1,
      overflow: 'auto',
      height: '100vh',
      position: 'fixed',
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
        onClick={(item) => navigate(item.key)}
        theme="light"
        selectedKeys={[pathname]}
        mode="vertical"
        items={menuItems}
      />
    </Sider>
  );
}
