import { AutoComplete, AutoCompleteProps, Input, Typography } from "antd";
import React, { useState } from "react";
import Food from "../../../models/Food";
import useWindowDimensions from "../../../utils/WindowDimensions";
import CalorieIcon from "../CalorieIcon/CalorieIcon";
import CarbIcon from "../CarbIcon/CarbIcon";
import FatIcon from "../FatIcon/FatIcon";
import ProteinIcon from "../ProteinIcon/ProteinIcon";

interface PropsType extends AutoCompleteProps {
  food: Food[];
}

export default function FoodSearchbar(props: PropsType) {
  const { Title } = Typography;
  const iconDimensions = { height: "1em", width: "1em" };
  const isMobile = useWindowDimensions().width < 850
  const convertFoodToOptions = (food: Food[]) =>
    food.map((item) => {
      return {
        value: item.id,
        label: (
          <div
            style={{
              display: "flex",
              justifyContent: "space-between",
            }}
          >
            <span>
              <Title level={5}>{item.name}</Title> {item.amount}g
            </span>
            <span>
              <div
                style={{
                  display: "flex",
                  justifyContent: "space-between",
                }}
              >
                <span>
                  <CalorieIcon size="1em" />
                  {item.calories}cal
                </span>
                <span>
                  <ProteinIcon {...iconDimensions} />
                  {item.protein}g
                </span>
              </div>
              <div
                style={{
                  display: "flex",
                  justifyContent: "space-between",
                }}
              >
                <span>
                  <FatIcon {...iconDimensions} />
                  {item.fat}g
                </span>
                <span>
                  <CarbIcon {...iconDimensions} />
                  {item.carbs}g
                </span>
              </div>
            </span>
          </div>
        ),
      };
    });
  const [options, setOptions] = useState(convertFoodToOptions(props.food));

  const handleSearch = (value: string) => {
    if (value !== "") {
      setOptions(convertFoodToOptions(props.food.filter((item) => item.name.toUpperCase().indexOf(value.toUpperCase()) !== -1)))
    }
    else {
      setOptions(convertFoodToOptions(props.food))
    }
  }

  return (
    <AutoComplete

      style={isMobile ? { width: "100%" } : { width: "33.3%" }}
      options={options}
      onSearch={handleSearch}
      {...props}
    >
      <Input.Search enterButton size="large" placeholder="Enter Food Name" />
    </AutoComplete>
  );
}
