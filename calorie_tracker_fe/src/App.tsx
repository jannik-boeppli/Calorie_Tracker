import React from "react";
import "./App.less";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import LoginPage from "./components/pages/LoginPage/LoginPage";

function App() {
  return (
    <div className="App">
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<LoginPage />}/>
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
