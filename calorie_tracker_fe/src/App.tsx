import React from "react";
import "./App.css";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import AuthPage from "./components/pages/AuthPage";

function App() {
  return (
    <div className="App">
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<AuthPage />}/>
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
