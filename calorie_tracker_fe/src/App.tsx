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
import AddFoodPage from "./components/pages/AddFoodPage/AddFoodPage";
import ProtectedRoute from "./components/atoms/ProtectedRoute/ProtectedRoute";
import { AuthenticationContextProvider } from "./contexts/AuthenticationContext";

function App() {
  return (
    <Layout className="App">
      <BrowserRouter>
        <AuthenticationContextProvider>
          <Sidebar />
          <MainLayout>
            <Routes>
              <Route path="/" element={<ProtectedRoute />}>
                <Route path="/" element={<LandingPage />} />
              </Route>
              <Route path="/profile" element={<ProtectedRoute />}>
                <Route path="/profile" element={<ProfilePage />} />
              </Route>
              <Route path="/goals" element={<ProtectedRoute />}>
                <Route path="/goals" element={<NutritionGoalsPage />} />
              </Route>
              <Route path="/food" element={<ProtectedRoute />}>
                <Route path="/food" element={<AddFoodPage />} />
              </Route>
              <Route path="/login" element={<LoginPage />} />
              <Route path="/signup" element={<SignUpPage />} />
            </Routes>
          </MainLayout>
        </AuthenticationContextProvider>
      </BrowserRouter>
    </Layout>
  );
}

export default App;
