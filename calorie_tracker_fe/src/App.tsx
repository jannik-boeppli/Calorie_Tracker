import React from "react";
import "./App.less";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import LoginPage from "./components/pages/LoginPage/LoginPage";
import SignUpPage from "./components/pages/SignUpPage/SignUpPage";
import LandingPage from "./components/pages/LandingPage/LandingPage";
import Sidebar from "./components/organisms/Sidebar/Sidebar";
import { Layout } from "antd";
import ProfilePage from "./components/pages/ProfilePage/ProfilePage";
import MainLayout from "./components/atoms/MainLayout/MainLayout";
import NutritionGoalsPage from "./components/pages/NutritionGoalsPage/NutritionGoalsPage";

function App() {
  return (
    <Layout className="App">
      <BrowserRouter>
        <Sidebar />
        <MainLayout>
          <Routes>
            <Route path="/" element={<LandingPage />} />
            <Route path="/profile" element={<ProfilePage />} />
            <Route path="/goals" element={<NutritionGoalsPage />} />
            <Route path="/login" element={<LoginPage />} />
            <Route path="/signup" element={<SignUpPage />} />
          </Routes>
        </MainLayout>
      </BrowserRouter>
    </Layout>
  );
}

export default App;
