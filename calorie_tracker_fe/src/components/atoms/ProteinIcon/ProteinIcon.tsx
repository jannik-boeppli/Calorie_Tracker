import React from "react";
import IconProps from "../../../models/IconProps";

export default function ProteinIcon({width, height}: IconProps) {
  return (
    <svg
      version="1.0"
      xmlns="http://www.w3.org/2000/svg"
      width={width}
      height={height}
      viewBox="0 0 50.000000 50.000000"
      preserveAspectRatio="xMidYMid meet"
    >
      <g
        transform="translate(0.000000,50.000000) scale(0.100000,-0.100000)"
        fill="#ff4d4f"
        stroke="none"
      >
        <path
          d="M102 445 c-49 -21 -62 -63 -62 -195 0 -189 21 -210 210 -210 189 0
210 21 210 210 0 190 -21 210 -212 210 -75 -1 -124 -6 -146 -15z m274 -62 c24
-16 44 -31 44 -34 0 -3 -11 -16 -25 -29 -25 -23 -25 -23 -46 -4 -19 16 -49 72
-49 89 0 13 37 2 76 -22z m-76 -6 c0 -7 11 -28 24 -47 13 -19 26 -44 30 -56 9
-29 -20 -62 -61 -70 -28 -6 -34 -2 -62 36 -17 24 -31 49 -31 56 0 24 42 1 71
-39 38 -52 37 -41 -1 20 -36 56 -36 62 -9 97 20 26 39 27 39 3z m-83 -134 c47
-77 48 -82 11 -120 -17 -18 -38 -33 -46 -33 -7 0 -34 14 -59 31 l-45 31 27 27
26 26 30 -35 c16 -19 30 -43 30 -55 1 -15 2 -16 6 -4 2 10 -10 39 -26 67 -17
27 -31 53 -31 58 0 14 30 54 40 54 4 0 21 -21 37 -47z"
        />
      </g>
    </svg>
  );
}
